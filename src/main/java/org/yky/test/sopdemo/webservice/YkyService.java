package org.yky.test.sopdemo.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by hp on 2018/10/28.
 */
@WebService
public class YkyService {

    public void yky1(String yky1) {
        System.out.println("Yky1在此" + yky1);
        System.out.println(yky2("我是yky2"));
    }

    public String yky2(String yky2) {
        System.out.println("Yky2在此");
        return yky2;
    }

    public static void main(String[] args){
        Endpoint.publish("http://127.0.0.1:8090/Service/YkyService",
                new YkyService());
        System.out.println("发布成功!");
    }

}
