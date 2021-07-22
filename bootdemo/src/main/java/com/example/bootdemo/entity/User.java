package com.example.bootdemo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String userId;
    private String userName;
    private String address;
    private Date birthday;
    private String email;
}
