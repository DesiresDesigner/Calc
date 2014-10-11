package ru.compscicenter.java2014.calculator;

/**
 * Created by desiresdesigner on 30.09.14.
 */
public class Main {
    static public void main(String args[]) throws Exception{
        MyCalculator calc = new MyCalculator(" -abs(1E+1^2 + 20/ 2)");

        System.out.println(calc.calculate());

        //System.out.println(Double.valueOf("1E-1"));


    }
}
