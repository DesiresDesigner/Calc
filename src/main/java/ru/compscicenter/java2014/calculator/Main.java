package ru.compscicenter.java2014.calculator;

/**
 * Created by desiresdesigner on 30.09.14.
 */
public class Main {
    static public void main(String args[]) throws Exception{
        myCalculator calc = new myCalculator("8+9-10");

        System.out.println(calc.calculate());
    }
}
