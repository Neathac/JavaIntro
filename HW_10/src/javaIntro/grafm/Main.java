package javaIntro.grafm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        String line = "";
            try {
                while((line = reader.readLine())!= null) if(line.trim().length() > 0) {
                    String result = evaluator.newExpression(line);
                    if (result.contains("ERROR")) System.out.println(result); 
                    else {
                        System.out.printf("%.5f", Double.parseDouble(result));
                        System.out.println();
                    }
                    
                }
            } catch (IOException e) {
                System.out.println("ERROR");
            }
    }
}
