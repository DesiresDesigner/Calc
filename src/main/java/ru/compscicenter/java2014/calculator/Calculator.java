package ru.compscicenter.java2014.calculator;

/**
 * Created by desiresdesigner on 30.09.14.
 */

interface Calculator {
    byte currentIndex = 0;
    String expression = "";

    double calculate();
    double count();
    byte getNextLiteral();

}
