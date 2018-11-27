package org.yky.test.lambdaAndStream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by hp on 2018/7/9.
 */
public class Lbtest1 {

    public static void main(String[] args) {
//       List<Integer> list1 = new ArrayList<Integer>();
//       list1.add(1);
//       list1.add(2);
//       list1.add(3);
//       list1.forEach((i -> System.out.println(i)));
        //Lbtest2.lb(Object::notifyAll);
        Lbtest2.lb((x, y) -> System.out.println(x + y));
    }
}
