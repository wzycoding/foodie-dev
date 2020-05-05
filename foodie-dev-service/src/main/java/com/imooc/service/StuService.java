package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {
    Stu getStuById(long id);

    void saveStu();

    void updateStu(long id);

    void deleteStuById(long id);
}
