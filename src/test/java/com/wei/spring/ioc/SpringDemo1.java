package com.wei.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDemo1 {
	/*
		更多资料  https://www.cnblogs.com/xiaoxi/p/5846416.html
	 */
	//传统方式的调用
	@Test
	public void demo1(){
		UserDaoImpl userDAO = new UserDaoImpl();
		userDAO.setName("孙悟空");
		userDAO.save();
		//打印结果:
		//UserDaoImpl执行了...孙悟空
	}

	//spring-BeanFactory
	@Test
	public void demo2(){
		//ClassPathResource
		Resource resource = new ClassPathResource("applicationContext.xml");
		//FileSystemResource
		//Resource resource = new FileSystemResource(new File("src/test/resources/applicationContext.xml"));
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		UserDao userDAO = (UserDao)beanFactory.getBean("userDao");
		userDAO.save();
		//打印结果:
		//UserDaoImpl执行了...猪八戒
	}

	//Spring-ApplicationContext
	@Test
	public void demo3(){
		//ClassPathXmlApplicationContext
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//FileSystemXmlApplicationContext
		//ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/test/resources/applicationContext.xml");
		UserDao userDAO = (UserDao)applicationContext.getBean("userDao");
		userDAO.save();
		//打印结果:
		//UserDaoImpl执行了...猪八戒
	}


}
