package javaIntro.grafm;

import javaIntro.grafm.Expression.Operators;

public class ExpressionNode {
    public boolean isLeaf;
    public Double numericalValue;
    public String textValue;
    public Operators nodeType;

    private ExpressionNode leftSon;
    private ExpressionNode rightSon;

    public ExpressionNode(String val, Operators valType) {
        nodeType = valType;
        if (valType == Operators.UNDEFINED) {
            numericalValue = 0.0;
            isLeaf = true;
            try {
                numericalValue = Double.parseDouble(val);
                textValue = val;
            } catch (Error e) {
                textValue = "ERROR";
            }
        }
    }

    public boolean insertToEnd(String val, Operators valType) {
        boolean result = false;
        if(!isLeaf) {
            if(rightSon != null) {
                if(!rightSon.insertToEnd(val, valType)) {
                    if(leftSon != null) {
                        if(!leftSon.insertToEnd(val, valType)) {
                            return false;
                        }
                    } else {
                        leftSon = new ExpressionNode(val, valType);
                    }
                }
            } else {
                rightSon = new ExpressionNode(val, valType);
            }
            result = true;
        }
        return result;
    }

    public void evaluateExpression() {
        if (isLeaf) {
            return;
        }
        if (rightSon == null || leftSon == null) {
            textValue = "ERROR";
            return;
        }
        rightSon.evaluateExpression();
        if(rightSon.textValue == "ERROR") {
            textValue = "ERROR";
            return;
        } else if (rightSon.textValue == "ERROR") {
            textValue = "ERROR";
            return;
        }
        leftSon.evaluateExpression();
        if(leftSon.textValue == "ERROR") {
            textValue = "ERROR";
            return;
        } else if (leftSon.textValue == "ERROR") {
            textValue = "ERROR";
            return;
        }

        switch(nodeType) {
            case ADDITION:
                numericalValue = leftSon.numericalValue + rightSon.numericalValue;
                break;

            case SUBSTRACTION:
                numericalValue = leftSon.numericalValue - rightSon.numericalValue ;
                break;

            case MULTIPLICATION:
                numericalValue = leftSon.numericalValue * rightSon.numericalValue;
                break;

            case DIVISION:
                if(rightSon.numericalValue == 0) {
                    textValue = "ERROR";
                    return;
                }
                numericalValue = leftSon.numericalValue / rightSon.numericalValue;
                break;

            default:
                break;
        }
        textValue = Double.toString(numericalValue);
    }
}
