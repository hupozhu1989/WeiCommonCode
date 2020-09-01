package com.wei.springboot;

import com.wei.springboot.entity.TStudent;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/25 0025
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCache {

    //ehcache单元测试
    @Test
    public void demo01(){
        //1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        //2. 获取缓存对象
        Cache cache = cacheManager.getCache("students");
        
        //3. 创建元素
        Element simpleElementPut = new Element("key1", "value1");
        //4. 将元素添加到缓存
        cache.put(simpleElementPut);
        //5. 获取缓存
        Element simpleElementGet = cache.get("key1");
        System.out.println(simpleElementGet);
        System.out.println(simpleElementGet.getObjectValue());
        
        Integer id = 1001;
        TStudent student = new TStudent();
        student.setId(id);
        student.setName("张三");
        student.setAge("20");
        student.setGender(0);

        Element stuElementPut = new Element("stu_"+id, student);
        cache.put(stuElementPut);
        Element stuElementGet = cache.get("stu_"+id);
        System.out.println(stuElementGet);
        System.out.println(stuElementGet.getObjectValue());

        //6. 删除元素
        //cache.remove("key1");
        System.out.println(cache.getSize());
        
        // 7. 刷新缓存
        cache.flush();
        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }





}
