package com.example.demo.TestFile;

import com.example.demo.bean.Courses;
import com.example.demo.bean.Student;
import com.example.demo.dao.MongoTestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Date: Created in 11:27 2019/8/27
 * @Modified By:
 */
@Component
public class TestMongo {
    @Autowired
   private MongoTestDao mongoTestDao;
    @Test
    public void test1(){

    }
}
