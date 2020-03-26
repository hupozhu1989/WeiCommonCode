package com.wei.springboot.controller;

import com.wei.springboot.entity.UserEntity;
import com.wei.springboot.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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



}