package org.yky.test.lambdaAndStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/7/10.
 */
public class Sttest1 {
    public static void main(String[] args) {
        List<Sttest2> lts = new ArrayList<Sttest2>();
        lts.add(new Sttest2(4));
        lts.add(new Sttest2(3));
        lts.add(new Sttest2(1));
        lts.add(new Sttest2(2));
//        lts.forEach(x -> System.out.println(x.b));
//        System.out.println(lts.stream().filter(x -> x.b < 4).mapToInt(x -> x.b).sum());
        lts.stream().filter(x -> x.b < 4).sorted((s1, s2) -> (s1.b - s2.b)).mapToInt(x -> x.b).forEach(System.out::println);
    }
}
