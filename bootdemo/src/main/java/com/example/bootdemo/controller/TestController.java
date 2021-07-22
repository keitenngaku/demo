package com.example.bootdemo.controller;

import com.example.bootdemo.entity.Test;
import com.example.bootdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/findTest")
    public List<Test> findAll(){
        return testService.findAll();
    }
}
