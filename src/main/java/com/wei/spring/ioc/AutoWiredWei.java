package com.wei.spring.ioc;

import java.lang.annotation.*;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface AutoWiredWei {
}
