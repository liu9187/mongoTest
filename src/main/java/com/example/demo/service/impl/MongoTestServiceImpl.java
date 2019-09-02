package com.example.demo.service.impl;

import com.example.demo.bean.Courses;
import com.example.demo.bean.MongoTest;
import com.example.demo.bean.Student;
import com.example.demo.dao.MongoTestDao;
import com.example.demo.service.MongoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title:
 * @Date: Created in 9:35 2019/8/26
 * @Modified By:
 */
@Service
public class MongoTestServiceImpl implements MongoTestService {
    @Autowired
    private MongoTestDao mongoTestDao;

    @Override
    public void saveTest(MongoTest test) {
        mongoTestDao.saveTest(test);
    }

    @Override
    public MongoTest findTestByName(String name) {
        return mongoTestDao.findTestByName(name);
    }

    @Override
    public void updateTest(MongoTest test) {
        mongoTestDao.updateTest(test);
    }

    @Override
    public void deleteTest(Integer id) {
        mongoTestDao.deleteTest(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return mongoTestDao.saveStudent(student);
    }

    @Override
    public boolean updatebyId(Integer id, String name, String remark) {
        return mongoTestDao.updatebyId(id,name,remark);
    }

    @Override
    public boolean updateById2(Integer id, Integer id_2, String name, String code) {
        return mongoTestDao.updateById2(id,id_2,name,code);
    }

    @Override
    public boolean addToSet(Courses courses, Integer id) {
        return mongoTestDao.addToSet(courses,id);
    }

    @Override
    public Student insertStudent(Student student) {
        return mongoTestDao.insertStudent(student);
    }

    @Override
    public Student getBasicDBObject(Integer id) {
        return mongoTestDao.getBasicDBObject(id);
    }

    @Override
    public List<Student> getBasicDBObjectList(Integer id, String sex) {
        return mongoTestDao.getBasicDBObjectList(id,sex);
    }

    @Override
    public List<Student> getQueryBuilder(Integer id, String sex) {
        return mongoTestDao.getQueryBuilder(id,sex);
    }

    @Override
    public String getAggregation() {
        return mongoTestDao.getAggregation();
    }

    @Override
    public String getAggregation2() {
        return mongoTestDao.getAggregation2();
    }
}
