package com.example.demohibernate.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity//实体类的标志
@Table(name = "t_test")//对应的数据库表名
public class TTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长主键
    private int id;
    private String name;
    private String memo;
}
