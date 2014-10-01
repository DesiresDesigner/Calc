package ru.compscicenter.java2014.calculator;

import java.util.ArrayList;

/**
 * Created by desiresdesigner on 01.10.14.
 */
public class myCalculator {//implements Calculator {
    private ArrayList<String> mathLiterals;
    private byte currentIndex = 0;
    private String expression;

    myCalculator(String expression){
        this.expression = expression;

        mathLiterals = new ArrayList<String>();
        mathLiterals.add("+");
        mathLiterals.add("-");
    }

    public double calculate() throws Exception {
        return count();
    }

    private double count() throws Exception {
        double res = 0;
        String currentLiteral = getNextLiteral();

        if (currentLiteral.equals("sin")){
            // TODO
        } else {
            res += Double.valueOf(currentLiteral);
        }

        while (currentIndex != expression.length()){
            String operation = getNextLiteral();
            if (operation.equals("+"))
                res += count();
            else if (operation.equals("-"))
                res -= count();
            else
                throw new Exception("Wrong operation");
        }

        return res;
    }

    private String getNextLiteral() {
        if (currentIndex >= expression.length())
            return null;

        String literal = String.valueOf(expression.charAt(currentIndex));
        if (mathLiterals.contains(literal)){
            ++currentIndex;
            return literal;
        }
        
        ++currentIndex;
        while (currentIndex < expression.length() && !mathLiterals.contains(String.valueOf(expression.charAt(currentIndex)))){
            literal += String.valueOf(expression.charAt(currentIndex));
            ++currentIndex;
        }

        return literal;
    }
}
