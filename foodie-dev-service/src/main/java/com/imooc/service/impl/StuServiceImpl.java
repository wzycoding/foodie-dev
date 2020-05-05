package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试学生service
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuMapper stuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStuById(long id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStu() {
        Stu stu = new Stu();
        stu.setName("wzy");
        stu.setAge(13);
        stuMapper.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStu(long id) {
        Stu stu = new Stu();
        stu.setName("wzy1");
        stu.setAge(20);
        stu.setId(id);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStuById(long id) {
        stuMapper.deleteByPrimaryKey(id);
    }


    public void saveParent() {
        Stu stu = new Stu();
        stu.setAge(10);
        stu.setName("parent");
    }

    public void saveChild() {
        saveChild1();
        int temp = 1 / 0;
        saveChild2();
    }

    public void saveChild1() {
        Stu stu = new Stu();
        stu.setAge(10);
        stu.setName("child1");
    }

    public void saveChild2() {
        Stu stu = new Stu();
        stu.setAge(10);
        stu.setName("child2");
    }
}
