package com.example.demohibernate.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity//实体类的标志
@Table(name = "student")//对应的数据库表名
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长主键
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    // 数据库字段名 不区分大小写
    // 数据库名列为number 若有stu_number样式的字段，Hibernate 会按照驼峰命名规范帮我们转换成 stuNumber
    //@Column(name = "number")
    private String number;
    private String name;
    private int sex;
    private Date birthday;
}
