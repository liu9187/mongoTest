package com.example.demo.service;

import com.example.demo.bean.Courses;
import com.example.demo.bean.MongoTest;
import com.example.demo.bean.Student;

import java.util.List;

/**
 * @Title:
 * @Date: Created in 9:33 2019/8/26
 * @Modified By:
 */
public interface MongoTestService {
    void saveTest(MongoTest test);

    MongoTest findTestByName(String name);

    void updateTest(MongoTest test);

    void deleteTest(Integer id);

    Student saveStudent(Student student);

    boolean updatebyId(Integer id, String name, String remark);

    boolean updateById2(Integer id, Integer id_2, String name, String code);

    boolean addToSet(Courses courses, Integer id);

    Student insertStudent(Student student);

    Student getBasicDBObject(Integer id);

    List<Student> getBasicDBObjectList(Integer id, String sex);

    List<Student> getQueryBuilder(Integer id, String sex);

    String getAggregation();

    String getAggregation2();
}
