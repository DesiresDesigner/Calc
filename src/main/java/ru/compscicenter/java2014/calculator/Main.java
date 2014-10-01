package ru.compscicenter.java2014.calculator;

/**
 * Created by desiresdesigner on 30.09.14.
 */
public class Main {
    static public void main(String args[]) throws Exception{
        myCalculator calc = new myCalculator("18 + 2*2*2 - 12/2");

        System.out.println(calc.calculate());
    }
}
