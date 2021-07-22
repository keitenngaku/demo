package com.example.demohibernate.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.demohibernate.entity.Student;
import com.example.demohibernate.service.StudentService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/save")
    public void save() {

        Student student = new Student();
        student.setNumber("000001");
        student.setName("张三");
        student.setSex(1);
        student.setBirthday(new Date());
        studentService.save(student);
    }

    @RequestMapping("/saveAll")
    public void saveAll(int count) {

        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Student student = new Student();
            student.setNumber("000001");
            student.setName("张三");
            student.setSex(1);
            student.setBirthday(new Date());
            studentList.add(student);
        }
        studentService.saveAll(studentList);
    }
    @RequestMapping("/saveBatch")
    public void saveBatch(int count, Session session) {

        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Student student = new Student();
            student.setNumber("000001");
            student.setName("张三");
            student.setSex(0);
            student.setBirthday(new Date());
            studentList.add(student);
        }
        studentService.saveBatch(studentList);
    }
    @RequestMapping("/saveBatch2")
    public void saveBatch2(int count) {

        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Student student = new Student();
            student.setNumber("000001");
            student.setName("张三");
            student.setSex(0);
            student.setBirthday(new Date());
            studentList.add(student);
        }
        studentService.saveBatch2(studentList);
    }
    @RequestMapping("/findOne")
    public Student findOne(int id) {
        Student student = studentService.findOne(id);
        return student;
    }

    @RequestMapping("/findAll")
    public List<Student> findAll() {
        List<Student> students = studentService.findAll();
        return students;
    }


    @RequestMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }


}
