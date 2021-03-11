package com.wei.springboot;

import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    /*
        ClassPathMapperScanner.java -> processBeanDefinitions()
        MapperFactoryBean.java extends FactoryBean<T>
     */
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

}
