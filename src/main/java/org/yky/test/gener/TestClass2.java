package org.yky.test.gener;

/**
 * Created by hp on 2018/3/26.
 */
public class TestClass2<T> implements IFdemo<T> {

    private T t;

//    @Override
//    public void writeName(T T, String name) {
//        T.setName(name);
//    }

    @Override
    public void write(T tt) {
        t = tt;
    }

    @Override
    public T get() {
        return t;
    }

    public T get1(T ttt) {
        return ttt;
    }

    public static void main(String[] args){
        TestClass2<String> a = new TestClass2<>();
        System.out.println(a.get1("aaa"));
    }
}
