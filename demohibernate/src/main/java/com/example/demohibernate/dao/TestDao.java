package com.example.demohibernate.dao;

import com.example.demohibernate.entity.TTest;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<TTest,Integer> {
}
