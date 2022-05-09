package org.yky.alltest;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by lenovo on 2018/12/4.
 */
public class Main {

    private static AtomicInteger echoRetries = new AtomicInteger(1);


    public static void main(String[] args) throws Exception {
        int a = 1 << 2;
        int c = 1 << 1;
        int b = 1;
        System.out.println(a | b | c);
    }



}
