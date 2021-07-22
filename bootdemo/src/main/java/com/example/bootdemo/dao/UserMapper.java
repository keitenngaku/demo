package com.example.bootdemo.dao;

import com.example.bootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    void addAll(List<User> user);

    void add(User user);
}
