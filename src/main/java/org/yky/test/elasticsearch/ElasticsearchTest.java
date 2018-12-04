package org.yky.test.elasticsearch;

import dd.springboot.demo.models.YkyUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.yky.dao.User;

/**
 * Created by lenovo on 2018/11/22.
 */
@Component
public interface ElasticsearchTest extends ElasticsearchRepository<User, Integer> {

    User queryEmployeeByUid(Integer uid);

}
