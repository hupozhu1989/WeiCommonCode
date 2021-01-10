package com.wei.spring.aop.service.impl;

import com.wei.spring.aop.service.CalService;
import org.springframework.stereotype.Service;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2021/1/3
 */
@Service
public class CalServiceImpl implements CalService {

    @Override
    public int div(int x, int y) {
        int result = x/y;
        System.out.println("======>CalServiceImpl被调用了，计算结果："+result);
        return result;
    }
}
