package javaIntro.grafm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluator {
    Map<String, Double> variables; 
    private static final String operators = "-+/*";

    ExpressionEvaluator() {
        variables = new HashMap<String,Double>();
        variables.put("last", (double) 0);
    }

    public String newExpression(String infixExpr) {
        String postfix = "";
        postfix = (infixExpr.contains("=") ? convertToPostfix(infixExpr.substring(infixExpr.indexOf("=") + 1)) : convertToPostfix(infixExpr));

        for(String s : variables.keySet()) if(postfix.contains(s)) postfix = postfix.replaceAll(s, variables.get(s).toString());

        String[] splitPostfix = postfix.trim().split("\\s+");
        ArrayList<String> checkedStrings = new ArrayList<String>();
        for(String s : splitPostfix) {
                
                if (!operators.contains(s) && !checkedStrings.contains(s)) {
                        checkedStrings.add(s);
                        Double d;
                        if (s.trim().matches("[a-zA-Z]+")) {
                                d = 0.0;
                        }
                        else {
                                try {
                                        d = Double.parseDouble(s.trim());
                                } catch(NumberFormatException e) {
                                        d = 0.0;
                                }
                        }
                        postfix = postfix.replaceAll(s, d.toString());    
                }     
        }
        splitPostfix = postfix.trim().split("\\s+");

        if (splitPostfix.length == 1) {
                if(variables.containsKey(splitPostfix[0])) return variables.get(splitPostfix[0]).toString(); 
                else return splitPostfix[0];
        }
        String result = Expression.processExpression(splitPostfix);

        if (result != "ERROR") {
                if (infixExpr.contains("=")) variables.put(infixExpr.trim().split("\\s+")[0], Double.parseDouble(result));
                else variables.put("last", Double.parseDouble(result));
        } else  variables.put("last", (double) 0);

        return result;
    }

    public String convertToPostfix(String infixExpr) {
        char[] chars = infixExpr.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder(infixExpr.length());

        for (char c : chars) {
                if (isOperator(c)) {
                        out.append(' ');
                        while (!stack.isEmpty() && stack.peek() != '(') {
                                if (operatorGreaterOrEqual(stack.peek(), c)) out.append(stack.pop());
                                else break;
                        }
                        stack.push(c);
                        stack.push(' ');
                } else if (c == '(') stack.push(c);
                else if (c == ')') {
                        while (!stack.isEmpty() && stack.peek() != '(') out.append(stack.pop());
                        if (!stack.isEmpty()) stack.pop();
                } else if (Character.isWhitespace(c)) out.append(c);
                else out.append(c);
        }
        while (!stack.empty()) out.append(stack.pop());
        return out.toString();
    }

    private int getPrecedence(char operator) {
        int ret = 0;
        if (operator == '-' || operator == '+') {
                ret = 1;
        } else if (operator == '*' || operator == '/') {
                ret = 2;
        }
        return ret;
    }
    private boolean operatorGreaterOrEqual(char op1, char op2) {
            return getPrecedence(op1) >= getPrecedence(op2);
    }

    private boolean isOperator(char val) {
            return operators.indexOf(val) >= 0;
    }
}
