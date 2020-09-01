package com.wei.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weizhenchao
 * @create 2020-08-05-下午 3:14
 */
@Service
//@CacheConfig是一个类级别的注解，允许共享cacheNames、keyGenerator、cacheManager 和 cacheResolver。
//@CacheConfig(cacheNames = "students")
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private TStudentMapper tStudentMapper;

    //@Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，
    //而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
    //两个主要的属性：key 指的是缓存的标识，同时可以用 # 来引用参数；value 指的是 ehcache.xml 中的缓存策略空间。
    @Cacheable(key = "'student_'+#id",value = "students")
    public TStudent queryStudentById(Integer id){
        return tStudentMapper.selectByPrimaryKey(id);
    }

    public PageInfo<TStudent> queryStudentPage(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<TStudent> studentList = tStudentMapper.selectAllStudents();
        PageInfo<TStudent> pageInfo = new PageInfo<>(studentList);
        return pageInfo;
    }

}
