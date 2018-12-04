package org.yky.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "firstdemo", type = "user", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public class User implements Serializable {

    private static final long serialVersionUID = 2120869894112984147L;
    @Id
    private int uid;
    private String name;
    private int sex;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String toString() {
        return "uid:" + uid + ",name:" + name + ",sex:" + sex;
    }
}