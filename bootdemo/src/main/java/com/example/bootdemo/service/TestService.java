package com.example.bootdemo.service;

import com.example.bootdemo.dao.TestMapper;
import com.example.bootdemo.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public List<Test> findAll(){
        return testMapper.findAll();

    }

}
