package com.example.demo.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Courses;
import com.example.demo.bean.MongoTest;
import com.example.demo.bean.Student;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;


/**
 * @author: liucl
 * @Description:
 * @Date: Created in 9:04 2019/8/26
 * @Modified By:
 */
@Repository
public class MongoTestDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Title: 创建对象
     * @Date: 9:08 2019/8/26
     */
    public void saveTest(MongoTest test) {
        mongoTemplate.save(test);

    }

    /**
     * @Author: liucl
     * @Description: 根据用户查询对象
     * @Date: 9:11 2019/8/26
     */
    public MongoTest findTestByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        MongoTest mt = mongoTemplate.findOne(query, MongoTest.class);
        return mt;
    }

    /**
     * @Author: liucl
     * @Description: 根据条件id更新集合
     * @Date: 9:26 2019/8/26
     */
    public void updateTest(MongoTest test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        mongoTemplate.updateFirst(query, update, MongoTest.class);
    }

    /**
     * @Author: liucl
     * @Description: 删除
     * @Date: 9:31 2019/8/26
     */
    public void deleteTest(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoTest.class);

    }

    public Student saveStudent(Student student) {
        System.out.println("测试开始了");
        return mongoTemplate.save(student);

    }

    /**
     * 高级查询测试
     */
    public void findTest() {
        //组合查询
        mongoTemplate.find(new Query(Criteria.where("").is("").and("").is("").and("").gt("").and("").lt("")), MongoTest.class);
        // 组合查询中的or 用法
        mongoTemplate.findOne(new Query(new Criteria().orOperator(Criteria.where("age").is(""), Criteria.where("id").is(""))), MongoTest.class);

    }

    /**
     * @Author: liucl
     * @Description: 更新对象中的属性
     * @Date: 15:25 2019/8/27
     */
    public boolean updatebyId(Integer id, String name, String remark) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = Update.update("name", name).set("remark", remark);
        UpdateResult student = mongoTemplate.updateFirst(query, update, Student.class);
        return student.wasAcknowledged();
    }

    /**
     * @Author: liucl
     * @Description: 更新内嵌对象属性 updateFirst 的使用
     * @Date: 16:50 2019/8/27
     */
    public boolean updateById2(Integer id, Integer id_2, String name, String code) {
        Query query = new Query(Criteria.where("id").is(id).and("courses._id").is(id_2));
        Update update = Update.update("courses.$.name", name).set("courses.$.code", code);
        UpdateResult student = mongoTemplate.updateFirst(query, update, Student.class);
        return student.wasAcknowledged();
    }

    /**
     * @Author: liucl
     * @Description: 给集合中的数组 增加一个对象
     * @Date: 16:54 2019/8/27
     */
    public boolean addToSet(Courses courses, Integer id) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update();
        update.addToSet("courses", courses);
        UpdateResult result = mongoTemplate.upsert(query, update, Student.class);
        // mongoTemplate.insert()
        return result.wasAcknowledged();
    }

    /**
     * @Author: liucl
     * @Description: insert 插入数据
     * @Date: 9:31 2019/8/28
     */
    public Student insertStudent(Student student) {
        Student result = mongoTemplate.insert(student);
        return result;
    }

    /**
     * @Author: liucl
     * @Description: 用basicDBObject 的方法实现 查询
     * @Date: 9:33 2019/8/28
     */
    public Student getBasicDBObject(Integer id) {
        DBObject obj = new BasicDBObject();
        obj.put("id", id);
        Query query = new BasicQuery(String.valueOf(obj));
        Student student = mongoTemplate.findOne(query, Student.class);
        return student;
    }

    /**
     * @Author: liucl
     * @Description: getBasicDBObjectList 查询
     * @Date: 10:54 2019/8/28
     */
    public List<Student> getBasicDBObjectList(Integer id, String sex) {
        BasicDBList list = new BasicDBList();
        list.add(new BasicDBObject("id", id));
        list.add(new BasicDBObject("sex", sex));
        DBObject obj = new BasicDBObject();
        obj.put("$or", list);
        //obj.put("$and",list);
        obj.put(String.valueOf(obj), list);
        Query query = new BasicQuery(String.valueOf(obj));
        List<Student> students = mongoTemplate.find(query, Student.class);
        return students;
    }

    /**
     * @Author: liucl
     * @Description: queryBuilder
     * @Date: 10:58 2019/8/28
     */
    public List<Student> getQueryBuilder(Integer id, String sex) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.or(new BasicDBObject("id", id), new BasicDBObject("sex", sex));
        Query query = new BasicQuery(String.valueOf(queryBuilder.get()));
        query.with(new Sort(Sort.Direction.ASC, "id"));
        //查询的总条数
        //long count =  mongoTemplate.count(query,Student.class);

        List<Student> students = mongoTemplate.find(query, Student.class);
        //mongoTemplate.aggregate()
        return students;

    }

    /**
     * @Author: liucl
     * @Description: 分组求和
     * @Date: 10:05 2019/8/29
     */
    public String getAggregation() {
        Aggregation g = Aggregation.newAggregation(Aggregation.group("sex").count().as("sex"));
        AggregationResults results = mongoTemplate.aggregate(g, "student", BasicDBObject.class);
     //   String ss =  JSONObject.toJSONString(results.getMappedResults());
        for (Iterator<BasicDBObject> iterator=results.iterator();iterator.hasNext();){
            Object obj=iterator.next();
            System.out.println(JSON.toJSONString(obj));
        }
        return "迭代完成";
    }
    /**
     * @Author: liucl
     * @Description:
     * @Date: 17:10 2019/8/29
     */
    public String getAggregation2(){
        Aggregation aggregation=Aggregation.newAggregation(Aggregation.unwind("courses"),
                Aggregation.match(Criteria.where("courses.name").is("英语").and("courses.code").gte("10").lte("100")),
                Aggregation.group("courses.name").count().as("符合条件分数的数量"));
              AggregationResults  results=  mongoTemplate.aggregate(aggregation,"student",BasicDBObject.class);
                for (Iterator<BasicDBObject> iterator=results.iterator();iterator.hasNext();){
                    Object obj = iterator.next();
                    System.out.println(JSON.toJSONString(obj));
                }
                return "迭代完成";
    }
}
