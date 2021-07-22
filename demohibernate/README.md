批量处理文件时：

```
@Autowired
EntityManagerFactory entityManagerFactory;
```

```
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
```

这里采用的是hibernate的session来处理，需要注意的点

1、在配置文件中配置batch_size

2、设置sessionFactory

​	获取session的2种方式：
​		1)getCurrentSession()会自动关闭session，但是要配置current_session_context_class，并且需要使用事务
​		2)openSession()这种方式需要手动关闭session

3、注意用getCurrentSession()时要开启一个只读的事务。

