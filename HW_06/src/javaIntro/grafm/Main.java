package javaIntro.grafm;
import java.io.*;
import javaIntro.grafm.Expression;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            Expression expression = new Expression();

            while ((line = in.readLine()) != null) {

                if (!line.isBlank()) {

                   System.out.println(expression.processExpression(line.trim().split("\\s+"))); 

                }

            }

        } catch(Exception e) {

            System.out.println("Input error");

        }

    }

}
