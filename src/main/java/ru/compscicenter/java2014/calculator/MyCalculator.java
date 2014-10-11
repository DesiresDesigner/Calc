package ru.compscicenter.java2014.calculator;

import java.util.ArrayList;

/**
 * Created by desiresdesigner on 01.10.14.
 */
public class MyCalculator {//implements Calculator {
    private ArrayList<Character> mathLiterals;
    private byte currentIndex = 0;
    private String expression;

    MyCalculator(String expression){
        this.expression = bringToCorrect(expression);

        mathLiterals = new ArrayList<Character>();
        mathLiterals.add('+');
        //mathLiterals.add("-");
        mathLiterals.add('*');
        mathLiterals.add('/');
        mathLiterals.add('(');
        mathLiterals.add(')');
        mathLiterals.add('^');
        mathLiterals.add('a');
        mathLiterals.add('c');
        mathLiterals.add('s');
    }

    public double calculate() throws Exception {
        return count(false);
    }

    private double count(boolean brackets) throws Exception {
        double res = getNumberFromLiteral(getNextLiteral());

        while (currentIndex != expression.length()){
            String operation = getNextLiteral();
            if (operation.equals("+"))
                res += count(false);
            /*else if (operation.equals("-")){
                res -= count(false);
            }*/
            else if (operation.equals("*")){
                res *= getNumberFromLiteral(getNextLiteral());
            } else if (operation.equals("/")){
                res /= getNumberFromLiteral(getNextLiteral());
            } else if (operation.equals(")")){
                if (!brackets)
                    --currentIndex;
                return res;
            }
            else
                throw new Exception("Wrong operation");
        }

        return res;
    }

    private String getNextLiteral() {
        if (currentIndex >= expression.length())
            return null;

        if (mathLiterals.contains(expression.charAt(currentIndex))){
            //System.out.println("Literal: " + literal);
            ++currentIndex;
            return String.valueOf(expression.charAt(currentIndex - 1));
        }

        if (expression.charAt(currentIndex) == '-') {
            ++currentIndex;
            return "-" + getNextLiteral();
        }

        String literal = String.valueOf(expression.charAt(currentIndex));
        ++currentIndex;
        while (currentIndex < expression.length() &&
                (literal.charAt(literal.length() - 1) == 'E'
                        || !mathLiterals.contains(expression.charAt(currentIndex)))){
            literal += String.valueOf(expression.charAt(currentIndex));
            ++currentIndex;
        }

        return literal;
    }

    private double getNumberFromLiteral(String literal) throws Exception {
        int sign = 1;
        double pow = 1;
        double tmpRes = 0;
        if (literal.charAt(0) == '-') {
            sign = -1;
            literal = literal.substring(1, literal.length());
        }
        if (literal.equals("(")){
            tmpRes = count(true);
        } else if (literal.equals("s")){
            tmpRes = Math.sin(count(true));
        } else if (literal.equals("c")){
            tmpRes = Math.cos(count(true));
        } else if (literal.equals("a")){
            tmpRes = Math.abs(count(true));
        } else {
            tmpRes = Double.valueOf(literal);
        }

        if (currentIndex < expression.length())
            if (getNextLiteral().equals("^"))
                pow = getNumberFromLiteral(getNextLiteral());
            else
                --currentIndex;
        tmpRes = sign * Math.pow(tmpRes, pow);
        return tmpRes;
    }

    private String bringToCorrect(String expression){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) != ' '){
                if (expression.charAt(i) == '-'
                        && builder.length() != 0
                        && (builder.charAt(builder.length()-1) != '+'
                        && builder.charAt(builder.length()-1) != '/'
                        && builder.charAt(builder.length()-1) != '*'
                        && builder.charAt(builder.length()-1) != '('
                        && builder.charAt(builder.length()-1) != 'E'
                )){
                    builder.append('+');
                }
                builder.append(expression.charAt(i));
                if (expression.charAt(i) == 's' || expression.charAt(i) == 'c' || expression.charAt(i) == 'a'){
                    do{
                        ++i;
                    } while (!(expression.charAt(i) == 'n' || expression.charAt(i) == 's'));
                }
            }
        }
        return builder.toString();
    }
}
