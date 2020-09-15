package com.wei.springboot.service;

import com.wei.springboot.dao.TStudentMapper;
import com.wei.springboot.entity.TStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/9/12
 */
@Service
public class StudentTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(StudentTransactionService.class);
    @Autowired
    private TStudentMapper tStudentMapper;

    //第一种
    public void saveAB1(TStudent a, TStudent b) {
        this.saveA1(a);
        this.saveB1(b);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveA1(TStudent a){
        tStudentMapper.insertSelective(a);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveB1(TStudent b){
        tStudentMapper.insertSelective(b);
    }

    //第二种
    @Transactional(rollbackFor=Exception.class)
    public void saveAB2(TStudent a, TStudent b) {
        tStudentMapper.insertSelective(a);
        this.saveB2(b);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public void saveB2(TStudent b){
        tStudentMapper.insertSelective(b);
    }

    //第三种
    @Transactional
    public void saveAB3(TStudent a, TStudent b) {
        tStudentMapper.insertSelective(a);
        ((StudentTransactionService) AopContext.currentProxy()).saveB2(b);
    }
    /*@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveB3(TStudent b){
        tStudentMapper.insertSelective(b);
    }*/
}
