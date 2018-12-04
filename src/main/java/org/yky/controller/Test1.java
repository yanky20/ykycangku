package org.yky.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dd.springboot.demo.dao.YkyUserMapper;
import dd.springboot.demo.models.YkyUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.yky.dao.User;
import org.yky.service.UserService;
import org.yky.test.YunjiTest2;
import org.yky.test.elasticsearch.ElasticsearchTest;
import org.yky.util.exception.CommomException;

import java.util.*;

/**
 * Created by hp on 2018/2/27.
 */
@RestController
@RequestMapping("/test1")
@Component
@ConfigurationProperties(prefix = "yky")
public class Test1 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String aaa;

    private int i = 0;

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    @Autowired
    Test2 conn;

    @Autowired
    YunjiTest2 yunjiTest2;

    @Autowired
    YkyUserMapper ym;

    @Autowired
    private ElasticsearchTest elasticsearchTest;

    @Autowired
    private UserService userService;

    @RequestMapping("/aaa")
    String test1() {
        logger.info("This is a debug message");
        return conn.getTest();
    }

    @RequestMapping("/essave")
    void testes() {
        User user = new User();
        user.setUid(i++);
        user.setName("小明");
        user.setSex(1);
        elasticsearchTest.save(user);
    }

    @RequestMapping("/esquery")
    String esquery() {
        String queryString = "明";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<User> searchResult = elasticsearchTest.search(builder);
        List<Object> userlist = new ArrayList<Object>();
        Iterator<User> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            userlist.add(iterator.next());
            System.out.println((iterator.next()).getName());
        }
        return (new JSONArray(userlist)).toJSONString();
    }

    @RequestMapping("/exception")
    String testex() {
        logger.info("This is a debug message");
        throw new CommomException("俨俨俨");
    }

    @RequestMapping("/aop")
    public String testaop() {
        logger.info("This is a aop");
        return "成功了";
    }

    @RequestMapping("/ccc")
    public String testc() {
        YkyUser y = new YkyUser();
        y.setName("小严");
        y.setSex(1);
        ym.insert(y);
        Map<String, Object> amap = new HashMap<String, Object>();
        amap.put("data", y);
        JSONObject json = new JSONObject(amap);
        return json.toJSONString();
    }

    @RequestMapping("/ddd")
    public void testd() {
//        YkyUser y = new YkyUser();
//        y.setName("小严");
//        y.setSex(1);
//        ym.insert(y);
        yunjiTest2.deepInsert();
    }

    @RequestMapping("/bbb")
    public List<User> test2() {
        return userService.getList();
    }

}
