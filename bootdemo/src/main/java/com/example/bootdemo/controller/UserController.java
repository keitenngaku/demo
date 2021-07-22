package com.example.bootdemo.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.bootdemo.entity.User;
import com.example.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("getUser")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    @RequestMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    @RequestMapping("/addAll")
    public void addAll(int count) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            int no = count + i;
            User user = new User();
            user.setUserId(String.valueOf(no));
            user.setUserName(String.valueOf(no));
            user.setAddress("丰台区" + no + "号");
            user.setBirthday(new Date());
            user.setEmail(no + "@qq.com");
            userList.add(user);
        }
        userService.addAll(userList);
    }

    @RequestMapping("/addBatch")
    public void addBatch(int count) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            int no = count + i;
            User user = new User();
            user.setUserId(String.valueOf(no));
            user.setUserName(String.valueOf(no));
            user.setAddress("丰台区" + no + "号");
            user.setBirthday(new Date());
            user.setEmail(no + "@qq.com");
            userList.add(user);
        }
        userService.addBatch(userList);
    }
}
