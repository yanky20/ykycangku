package org.yky.test.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hp on 2018/3/27.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface YkyAno {
    int id() default 1;

    String name() default "严恺阳";

}
