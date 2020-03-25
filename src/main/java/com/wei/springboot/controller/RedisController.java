package com.wei.springboot.controller;

import com.wei.springboot.entity.UserEntity;
import com.wei.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: springbootdemo
 * @Date: 2019/1/25 15:03
 * @Author: Mr.Zheng
 * @Description:
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static final int EXPIRE_TIME = 60;   // redis中存储的过期时间60s

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/string/set")
    public boolean stringSet(@RequestParam("key") String key, @RequestParam("value")String value){
        return redisUtil.set(key,value);
    }

    @RequestMapping("/string/get")
    public Object stringGet(@RequestParam("key") String key){
        return redisUtil.get(key);
    }

    @RequestMapping("/expire")
    public boolean expire(@RequestParam("key") String key){
        return redisUtil.expire(key,EXPIRE_TIME);
    }

    @RequestMapping("/hash/set")
    public Boolean haseSet(){
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Long.valueOf(1001));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());

        HashOperations<String, Object, Object> hashMap = redisTemplate.opsForHash();
        hashMap.put(userEntity.getId().toString(),"name",userEntity.getName());
        hashMap.put(userEntity.getId().toString(),"age",userEntity.getAge());
        hashMap.put(userEntity.getId().toString(),"createTime",userEntity.getCreateTime());

        return redisTemplate.expire(userEntity.getId().toString(),EXPIRE_TIME, TimeUnit.SECONDS);
    }

}