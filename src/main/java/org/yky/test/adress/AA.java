package org.yky.test.adress;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2018/9/29.
 */
public class AA {
    public Long a = new Long(1);
    public Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void aa() {
        System.out.println(map.get(1));
    }

    public static void main(String[] args){
       AA aa = new AA();
       aa.aa();
    }
}
