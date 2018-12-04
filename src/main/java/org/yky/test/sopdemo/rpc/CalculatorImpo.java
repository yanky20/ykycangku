package org.yky.test.sopdemo.rpc;

/**
 * Created by lenovo on 2018/12/3.
 */
public class CalculatorImpo implements Calculator {
    @Override
    public String sum(int a, int b) {
        return null;
    }

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpo();
        System.out.println(calculator.sum(1, 2));
    }

}
