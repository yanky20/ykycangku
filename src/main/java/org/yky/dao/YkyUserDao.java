package org.yky.dao;

import dd.springboot.demo.dao.YkyUserMapper;
import dd.springboot.demo.models.YkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2018/11/22.
 */
@Repository
public class YkyUserDao {

    @Autowired
    YkyUserMapper ykyUserMapper;

    public YkyUser getById(Integer uid) {
        return ykyUserMapper.selectByPrimaryKey(uid);
    }
}
