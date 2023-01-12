package javaIntro.grafm;

import java.util.*;

import javaIntro.grafm.ExpressionNode;

public class Expression {

    public enum Operators {
        ADDITION,
        SUBSTRACTION,
        MULTIPLICATION,
        DIVISION,
        UNDEFINED
    }

    public static String processExpression(String[] expressionParts) {

        String result = "ERROR";
        if(expressionParts.length > 0) {
            Operators op = getOperator(expressionParts[expressionParts.length - 1].trim());
            ExpressionNode rootNode = new ExpressionNode(expressionParts[expressionParts.length - 1].trim(), op);

            for(int i = expressionParts.length - 2; i >= 0; --i) {
                String t = expressionParts[i].replaceAll("\\s", "");
                if (t.trim().length() > 0) {
                    Operators operator = getOperator(t);
                    if(!rootNode.insertToEnd(t, operator)) return "ERROR";
                }
            }
            if (rootNode.isLeaf) return "ERROR";
            rootNode.evaluateExpression();
            result = rootNode.textValue;
        }

        return result;
    }

    private static Operators getOperator(String toCheck) {
        switch(toCheck.trim()) {
            case "+":
                return Operators.ADDITION;
            case "-":
                return Operators.SUBSTRACTION;
            case "*":
                return Operators.MULTIPLICATION;
            case "/":
                return Operators.DIVISION;
            default:
                return Operators.UNDEFINED;
        }
    }   
}
