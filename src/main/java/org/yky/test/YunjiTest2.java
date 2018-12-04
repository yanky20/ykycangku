package org.yky.test;

import dd.springboot.demo.dao.YkyRoleUserMapper;
import dd.springboot.demo.dao.YkyUserMapper;
import dd.springboot.demo.models.YkyRoleUser;
import dd.springboot.demo.models.YkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by lenovo on 2018/11/27.
 */
@Component
public class YunjiTest2 {

    @Autowired
    YkyUserMapper ym;

    @Autowired
    YkyRoleUserMapper yrm;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://127.0.0.1:3306/yky?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
    static final String username = "root";
    static final String password = "123456";
    static final String yky_user = "yky_user";
    static final String yky_role = "t_rg_goods_money_info";
    static final String yky_role_user = "t_rg_order_note";

    static final String uid = "uid";
    static final String name = "name";
    static final String sex = "sex";

    static int count = 10000; //循环次数
    static int modNum = 8; //模运算取性别

    //    @Transactional
    public void deepInsert() {
        int i = 0;
        long time1 = System.currentTimeMillis();
        while (i++ < count) {
//            YkyUser y = new YkyUser();
//            y.setName("小严" + i);
//            y.setSex(i % modNum);
//            ym.insert(y);
//            YkyRoleUser y = new YkyRoleUser();
//            y.setUid(i);
//            y.setRid(i % modNum + 1);
//            yrm.insert(y);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
//        throw new RuntimeException("事务回滚啦");
    }


}
