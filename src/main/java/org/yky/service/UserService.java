package org.yky.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dd.springboot.demo.models.YkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.yky.dao.User;
import org.yky.dao.YkyUserDao;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private YkyUserDao ykyUserDao;

    public YkyUser getUser(Integer uid) {
        return ykyUserDao.getById(uid);
    }

    public List<User> getList(){
        String sql = "select uid,name,sex   from yky_user";
        return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>(){

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User stu = new User();
                stu.setUid(rs.getInt("uid"));
                stu.setName(rs.getString("name"));
                stu.setSex(rs.getInt("sex"));
                return stu;
            }

        });
    }
}