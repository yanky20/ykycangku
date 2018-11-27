package org.yky.util.exception;

/**
 * Created by hp on 2018/7/30.
 */
public class CommomException extends RuntimeException{

    public String a = "异常处理";
    public CommomException(String a) {
        super();
        setA(a);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
