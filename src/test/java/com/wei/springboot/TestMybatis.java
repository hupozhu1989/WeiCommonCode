package com.wei.springboot;

import com.wei.springboot.config.AppConfig;
import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/8/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatis {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    //https://www.cnblogs.com/lcngu/p/5470695.html
    @Test
    public void demo1(){
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获限mapper接口实例
        TStudentMapper studentMapper = session.getMapper(TStudentMapper.class);
        //构造查询条件user对象
        TStudent student = studentMapper.selectByPrimaryKey(1);
        System.out.println(student);
        //关闭session
        session.close();
    }

    @Test
    public void demo2(){
		/*
			jdk动态代理产生mapper的代理对象   mybatis的动态代理
		 */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        TStudentMapper mapper = applicationContext.getBean(TStudentMapper.class);
        TStudent student = mapper.selectByPrimaryKey(1);
        System.out.println(student);
    }

}
