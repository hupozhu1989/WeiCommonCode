package com.wei.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weizhenchao
 * @create 2020-08-05-下午 3:14
 */
@Service
public class StudentService {

    @Autowired
    private TStudentMapper tStudentMapper;

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
