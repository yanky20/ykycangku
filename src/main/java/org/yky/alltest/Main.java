package org.yky.alltest;


import com.google.common.base.Utf8;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lenovo on 2018/12/4.
 */
public class Main {

    private static AtomicInteger echoRetries = new AtomicInteger(1);


    public static void main(String[] args) throws Exception {
       int a = 1;
       switch (a) {
           case 1 : throw new RuntimeException("");
           default: throw new Exception("");
       }

    }

    private static void printNode(Node nd) {
        if (nd.next != null)
            do {
                nd = nd.next;
                System.out.println(nd.value);
            } while (nd.next != null);
    }

    private static Node resetNode(Node nd) {
        Node dddd = null;
        if (nd.next != null) {
            while (nd.next != null) {
                Node temp = dddd;
                dddd = nd.next;
                Node next = dddd.next;
                dddd.next = temp;
                nd.next = next;
            }
        }
        Node shaobing = new Node();
        shaobing.next = dddd;
        return shaobing;
    }

    public static class Node {
        public Node next;
        public int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

        Node() {
        }
    }

}
