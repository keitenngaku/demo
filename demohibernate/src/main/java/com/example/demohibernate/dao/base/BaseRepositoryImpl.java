package com.example.demohibernate.dao.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Iterator;

@Slf4j
@SuppressWarnings("SpringJavaConstructorAutowiringInspection")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private static final int BATCH_SIZE = 20000;
    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> Iterable<S> batchInsert(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            entityManager.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                log.info("Excuse Batch Operation,Size:"+BATCH_SIZE);
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }

    @Override
    @Transactional
    public <S extends T> Iterable<S> batchUpdate(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            entityManager.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }
}
