package org.yky.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dd.springboot.demo.models.YkyUser;
import dd.springboot.demo.vo.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yky.common.exception.BizErrorType;
import org.yky.common.exception.BizException;
import org.yky.dao.User;
import org.yky.dao.YkyUserDao;
import org.yky.util.handler.OtherEndPointExceptionHandler;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private YkyUserDao ykyUserDao;

    @Autowired
    private OtherEndPointExceptionHandler otherEndPointExceptionHandler;

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

    public ResponseEntity runExecption() {
        throw new BizException(BizErrorType.ERROR_FAST_NOTIFICATION);
    }

}