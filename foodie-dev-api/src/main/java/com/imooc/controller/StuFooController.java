package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 学生类测试Controller
 */
// 文档中不会显示这个controller的api
@ApiIgnore
@RestController
public class StuFooController {

    @Autowired
    StuService stuService;

    @GetMapping("/getStu")
    public Stu getStu(long id) {
        return stuService.getStuById(id);
    }

    @PostMapping("/saveStu")
    public String saveStu() {
        stuService.saveStu();
        return "OK";
    }

    @PostMapping("/updateStu")
    public String updateStu(long id) {
        stuService.updateStu(id);
        return "OK";
    }

    @PostMapping("/deleteStu")
    public String deleteStu(long id) {
        stuService.deleteStuById(id);
        return "OK";
    }
}
