package com.example.bootdemo.dao;

import com.example.bootdemo.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> findAll();
}
