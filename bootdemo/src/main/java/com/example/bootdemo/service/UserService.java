package com.example.bootdemo.service;

import com.example.bootdemo.dao.UserMapper;
import com.example.bootdemo.entity.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    public List<User> findAll(){
        List<User> userList = userMapper.findAll();
        return userList;
    }

    public void addAll(List<User> user){
        userMapper.addAll(user);
    }

    @Transactional
    public void addBatch(List<User> list){
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        userMapper = session.getMapper(UserMapper.class);
        for (int i = 0; i < list.size(); i++) {
            userMapper.add(list.get(i));
            if(i%2000==999){//每1000条提交一次防止内存溢出
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.clearCache();
    }

}
