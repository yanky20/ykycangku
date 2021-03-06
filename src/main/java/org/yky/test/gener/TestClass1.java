package org.yky.test.gener;

import org.yky.test.TestClass4;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 关于重写形成内部类的研究
 */
public class TestClass1 {

    static int aa = 1;
    static int bb = 2;
    protected int aac = 1;
    protected int bbc = 2;

    public void abc() {
        System.out.println("haha");
    }

    //情况一  其他引用类型   此种写法本质是定义了了一个继承ArrayList的匿名内部类
    public static List<String> a = new ArrayList<String>() {{
        add("a");
        add("b");
    }};

    //情况二  重写方法
    public static TestClass1 b = new TestClass1() {
        public void abc() {
            System.out.println("haha1");
        }
    };

    //情况三  重写属性(匿名内部类无意义，属性取值对象的定义，比如此处c取值父类的值，无法实现多态)
    public static TestClass1 c = new TestClass1() {
        public int aa = 11;
        public int bb = 22;
    };

    //情况四 其他代码块(同时合并情况 二三)
    public static TestClass1 d = new TestClass1() {
        {
            System.out.println("qingkuang3");
        }

        public int aa = 111;
        public int bb = 222;

        public void abc() {
            System.out.println("haha2");
            System.out.println(this.aa + this.bb);
        }
    };

    public static void main(String[] args) {
//        b.abc();
//        System.out.println(a);
//        System.out.println(c.aa + c.bb);
//        System.out.println(d.aa + d.bb);
//        d.abc();
//        String[] t1 = new String[4];
//        t1[0] = "小明";
//        t1[1] = "小明";
//        t1[2] = "小刚";
//        t1[3] = "小红";
//        Set<String> t = new LinkedHashSet(Arrays.asList(t1));
////        t.add("小明");
////        t.add("小明");
////        t.add("小刚");
//        String spice = "!@#";
//        System.out.println(removeRep(new String[] {"a","bb","a","小明","小刚","小明"}, spice));
        StringBuffer b = new StringBuffer();
        System.out.println(b.toString());
    }

    private static String removeRep(String[] rrsa, String spilce) {
        Set<String> slist = new LinkedHashSet(Arrays.asList(rrsa));
        StringBuffer sbf = new StringBuffer();
        for (String rsa : slist) {
            sbf.append(rsa);
            sbf.append(spilce);
        }
        int lastL = spilce.length();
        if (spilce.equals(sbf.substring(sbf.length() - lastL)))
            return sbf.substring(0, sbf.length() - lastL);
        else return sbf.toString();
    }

}
