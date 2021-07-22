package com.example.demohibernate.controller;

import com.example.demohibernate.entity.TTest;
import com.example.demohibernate.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("test")
    public void add(){
        TTest tTest = new TTest();
        tTest.setName("小明");
        tTest.setMemo("ok");
        testService.add(tTest);
    }

    @RequestMapping("findTest")
    public List<TTest> find(){

        return testService.findAll();
    }
}
