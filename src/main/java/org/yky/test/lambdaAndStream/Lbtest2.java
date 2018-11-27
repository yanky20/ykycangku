package org.yky.test.lambdaAndStream;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by hp on 2018/7/9.
 */
public class Lbtest2 {
    public static int b = 1;
    public static int c = 2;

    public static void lb(BiConsumer<Integer, Integer> action) {
        System.out.println("lb+1");
        action.accept(b,c);
    }
}
