package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Courses;
import com.example.demo.bean.MongoTest;
import com.example.demo.bean.Student;
import com.example.demo.service.MongoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Date: Created in 9:41 2019/8/26
 * @Modified By:
 */
@RestController
public class MongoTestController {
    @Autowired
    private MongoTestService mongoTestService;

    @GetMapping(value = "/test1")
    public void saveTest() {
        MongoTest mongoTest = new MongoTest();
        mongoTest.setAge("13");
        mongoTest.setId(1);
        mongoTest.setName("cc");
        mongoTestService.saveTest(mongoTest);

    }

    @GetMapping(value = "/test2")
    public MongoTest findTestByName() {
        MongoTest mt = mongoTestService.findTestByName("cc");
        return mt;
    }

    @GetMapping(value = "/test3")
    public void updateTest() {
        MongoTest mongoTest = new MongoTest();
        mongoTest.setId(1);
        mongoTest.setName("cc2");
        mongoTest.setAge("20");
        mongoTestService.updateTest(mongoTest);
    }

    @GetMapping(value = "/test4")
    public void deleteTest() {
        mongoTestService.deleteTest(1);
    }

    /**
     * @Author: liucl
     * @Description: 保存镶嵌对象 并且配置去除_class
     * @Date: 15:06 2019/8/27
     */
    @GetMapping(value = "/test5")
    public void test5() {
        Student student = new Student();
        student.setId(1);
        student.setName("li");
        student.setSex("男");
        student.setRemark("备注1");
        List<Courses> coursesList = new ArrayList<>();
        Courses courses1 = new Courses();
        courses1.setCode("77");
        courses1.setId(1);
        courses1.setName("数学");
        coursesList.add(courses1);

        Courses courses2 = new Courses();
        courses2.setCode("88");
        courses2.setId(2);
        courses2.setName("语文");
        coursesList.add(courses2);
        student.setCoursesList(coursesList);
        mongoTestService.saveStudent(student);
    }

    /**
     * @Author: liucl
     * @Description: 修改
     * @Date: 15:28 2019/8/27
     */
    @GetMapping(value = "/test6")
    public String test6() {
        boolean result = mongoTestService.updatebyId(1, "张辽", "第一次修改");
        return "修改数据的结果：" + result;
    }

    /**
     * @Author: liucl
     * @Description: 更新内嵌对象
     * @Date: 16:09 2019/8/27
     */
    @GetMapping(value = "/test7")
    public String test7() {
        boolean result = mongoTestService.updateById2(1, 2, "数学", "99.5");
        Double random = Math.random();
        return "修改内嵌数据的随机数 " + random + " 结果" + result;
    }

    @GetMapping(value = "/test8")
    public String test8() {
        Courses courses = new Courses();
        courses.setId(3);
        courses.setName("语文");
        courses.setCode("40");
        boolean result = mongoTestService.addToSet(courses, 1);
        Double random = Math.random();
        return "修改内嵌数据的随机数 " + random + " 结果" + result;
    }

    /**
     * @Author: liucl
     * @Description: 新增数据 使用insert
     * @Date: 8:46 2019/8/28
     */
    @GetMapping(value = "/test9")
    public String test9() {
        Student student = new Student();
        student.setId(5);
        student.setName("诸葛亮");
        student.setSex("男");
        student.setRemark("蜀国军师");
        List<Courses> coursesList = new ArrayList<>();

        Courses courses1 = new Courses();
        courses1.setCode("100");
        courses1.setName("数学");
        courses1.setId(1);
        coursesList.add(courses1);

        Courses courses2 = new Courses();
        courses2.setId(2);
        courses2.setName("英语");
        courses2.setCode("100");
        coursesList.add(courses2);
        student.setCoursesList(coursesList);
        Student student1 = mongoTestService.insertStudent(student);
        String js = JSONObject.toJSONString(student1);
        return js;
    }

    /**
     * @Author: liucl
     * @Description: BasicDBObject 方法查询
     * @Date: 9:58 2019/8/28
     */
    @GetMapping(value = "/test10")
    public String getBasicDBObject() {
        Student student = mongoTestService.getBasicDBObject(1);
        String js = JSONObject.toJSONString(student);
        return js;
    }
    /**
     * @Author: liucl
     * @Description: BasicDBObjectList 的查询使用
     * @Date: 10:23 2019/8/28
     */
    @GetMapping(value = "/test11")
    public String getBasicDBObjectList() {
        List<Student> students = mongoTestService.getBasicDBObjectList(1, "男");
        String result = JSONArray.toJSONString(students);
        return result;
    }
    /**
     * @Author: liucl
     * @Description: QueryBuilder 的 使用
     * @Date: 11:20 2019/8/28
     */
    @GetMapping(value = "/test12")
    public String getQueryBuilder(){
        List<Student> students = mongoTestService.getQueryBuilder(1, "男");
        System.out.println("=====QueryBuilder 的 使用");
        String result = JSONArray.toJSONString(students);
        return result;
    }
    /**
     * @Author: liucl
     * @Description: 分组求和
     * @Date: 10:10 2019/8/29
     */
    @GetMapping(value = "/test13")
    public String  getAggregation(){
        String result =  mongoTestService.getAggregation();
        return  result;
    }
    /**
     * @Author: liucl
     * @Description: 分组求和
     * @Date: 10:10 2019/8/29
     */
    @GetMapping(value = "/test14")
    public String  getAggregation2(){
        String result =  mongoTestService.getAggregation2();
        return  result;
    }
}
