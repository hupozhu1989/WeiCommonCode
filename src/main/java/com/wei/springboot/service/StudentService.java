package com.wei.springboot.service;

import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
