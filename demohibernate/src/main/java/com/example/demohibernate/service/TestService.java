package com.example.demohibernate.service;

import com.example.demohibernate.dao.TestDao;
import com.example.demohibernate.entity.TTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public void add(TTest tTest){
        testDao.save(tTest);
    }

    public List<TTest> findAll(){
        List<TTest> tests = (List<TTest>)testDao.findAll();
        return tests;
    }
}
