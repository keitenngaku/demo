package com.example.demohibernate.service;

import com.example.demohibernate.dao.StudentDao;
import com.example.demohibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<Student> findAll() {
        List<Student> list = (List<Student>) studentDao.findAll();
        return list;
    }

    public Student findOne(int id) {
        Student student = studentDao.findById(id);
        return student;
    }

    public void save(Student student) {
        studentDao.save(student);
    }

    public void saveAll(List<Student> students) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        studentDao.saveAll(students);
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("运行时间：" + (endTime - startTime));

    }

    @Transactional(readOnly = true)
    public void saveBatch(List<Student> studentList) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
        Transaction tx = session.beginTransaction();
        int batchSize = 20000;
        int i = 0;
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);

        for (Student entity : studentList) {
            i++;
            session.save(entity);
            if (i % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("运行时间：" + (endTime - startTime));
    }

    @Transactional
    public void saveBatch2(List<Student> studentList) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        studentDao.batchInsert(studentList);
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("运行时间：" + (endTime - startTime));

    }
}
