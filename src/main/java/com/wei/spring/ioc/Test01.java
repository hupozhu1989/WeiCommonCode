package com.wei.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {

    //把set方法注释打开,注释@AutoWiredWei
    @Test
    public void method01() throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        //创建对象
        UserService userService = new UserService();
        System.out.println(userService);
        //获取所有属性
        Field field = clazz.getDeclaredField("userService");
        field.setAccessible(true);
        //只有通过方法才能设置具体的属性值
        String name = field.getName();
        //拼接方法的名称
        name = name.substring(0,1).toUpperCase()+name.substring(1);
        String setMethodName = "set"+name;
        //通过方法注入属性的对象
        Method method = clazz.getMethod(setMethodName, UserService.class);
        //反射
        method.invoke(userController,userService);
        System.out.println(userController.getUserService());
    }

    //把set方法注释掉,把@AutoWiredWei注解打开
    @Test
    public void method02(){
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        
        //获取所有的属性值
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            String name = field.getName();
            AutoWiredWei annotation = field.getAnnotation(AutoWiredWei.class);
            if (annotation != null){
                field.setAccessible(true);
                //获取属性类型
                Class<?> type = field.getType();
                try {
                    Object o = type.newInstance();
                    field.set(userController,o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(userController.getUserService());
        });
    }


}
