package com.example.demohibernate.dao;

import com.example.demohibernate.dao.base.BaseRepository;
import com.example.demohibernate.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends BaseRepository<Student,Integer> {
    public Student findById(int id);
}
