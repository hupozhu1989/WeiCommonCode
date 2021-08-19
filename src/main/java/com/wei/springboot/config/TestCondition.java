package com.wei.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能模块描述
 *
 * @Author Forward
 * @Date 2021/8/19 17:47
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "test.condition", name = "open", havingValue = "true", matchIfMissing = false)
public class TestCondition {
    /**
     * value：获取对应property名称的值，与name不可同时使用
     * prefix：配置属性名称的前缀
     * name：配置属性完整名称或部分名称，可与prefix组合使用，组成完整的配置属性名称，与value不可同时使用
     * havingValue：可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
     * matchIfMissing：缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效
     */

    @PostConstruct
    public void postConstruct(){
        log.info("加载类TestCondition");
    }
}
