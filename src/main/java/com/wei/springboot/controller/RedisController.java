package com.wei.springboot.controller;

import com.wei.springboot.entity.UserEntity;
import com.wei.springboot.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: springbootdemo
 * @Date: 2019/1/25 15:03
 * @Author: Mr.Zheng
 * @Description:
 */
@Api("redis")
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    public static final String REDIS_LOCK_KEY = "lockhhf";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;

    @ApiOperation("存储string")
    @GetMapping("/string/set")
    public boolean stringSet(@RequestParam("key") String key, @RequestParam("value")String value, @RequestParam("time") long time){
        return redisUtil.set(key,value,time);
    }

    @ApiOperation("存储ojb")
    @GetMapping("/string/set/obj")
    public boolean stringSetObj(){
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Long.valueOf(1001));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());
        return redisUtil.set(userEntity.getId().toString(),userEntity);
    }

    @ApiOperation("通过key获取value")
    @GetMapping("/string/get")
    public Object stringGet(@RequestParam("key") String key){
        return redisUtil.get(key);
    }

    @ApiOperation("通过key设置过期时间")
    @GetMapping("/expire")
    public boolean expire(@RequestParam("key") String key,@RequestParam("time") long time){
        return redisUtil.expire(key,time);
    }

    @ApiOperation("存储hash")
    @PostMapping("/hash/set")
    public Boolean hashSet(@RequestBody UserEntity userEntity){
        String key = "USER:"+userEntity.getId();
        redisUtil.hset(key,"id",userEntity.getId());
        redisUtil.hset(key,"name",userEntity.getName());
        redisUtil.hset(key,"age",userEntity.getAge());
        return redisUtil.hset(key, "createTime", userEntity.getCreateTime());
    }

    @ApiOperation("通过key获取hash")
    @GetMapping("/hash/get")
    public Object hashGet(@RequestParam("key") String key,@RequestParam("item") String item){
        return redisUtil.hget(key, item);
    }

    @GetMapping("/v1/buy_goods")
    public String buy_Goods_v1() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {
            //setIfAbsent() == setnx 就是如果不存在就新建，同时加上过期时间保证原子性
            Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 10L, TimeUnit.SECONDS);
            stringRedisTemplate.expire(REDIS_LOCK_KEY, 10L, TimeUnit.SECONDS);
            if (!lockFlag) {
                return "抢锁失败，┭┮﹏┭┮";
            } else {
                String result = stringRedisTemplate.opsForValue().get("goods:001");
                int goodsNumber = result == null ? 0 : Integer.parseInt(result);

                if (goodsNumber > 0) {
                    int realNumber = goodsNumber - 1;
                    stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                    System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort);
                    return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
                } else {
                    System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
                }
                return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
            }
        } finally {
            Jedis jedis = RedisUtil.getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1]" + "then "
                    + "return redis.call('del', KEYS[1])" + "else " + "  return 0 " + "end";
            try {
                Object result = jedis.eval(script, Collections.singletonList(REDIS_LOCK_KEY), Collections.singletonList(value));
                if ("1".equals(result.toString())) {
                    System.out.println("------del REDIS_LOCK_KEY success");
                } else {
                    System.out.println("------del REDIS_LOCK_KEY error");
                }
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        }
    }

    @GetMapping("/v2/buy_goods")
    public String buy_Goods_v2(){
        String value = UUID.randomUUID().toString()+Thread.currentThread().getName();
        RLock redissonLock = redisson.getLock(REDIS_LOCK_KEY);
        redissonLock.lock();
        try{
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0){
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001",realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件"+"\t 服务器端口: "+serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件"+"\t 服务器端口: "+serverPort;
            }else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临"+"\t 服务器端口: "+serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临"+"\t 服务器端口: "+serverPort;

        }finally {
            redissonLock.unlock();
        }
    }

}