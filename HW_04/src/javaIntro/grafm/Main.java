package javaIntro.grafm;
import java.io.*;
import javaIntro.grafm.Decomposer;

public class Main {
    public static void main(String[] args) throws Exception {
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Decomposer decomposer = new Decomposer();
        int lineLen = Integer.parseInt(in.readLine());
        String s;
        while ((s = in.readLine()) != null) {
          if(s.isEmpty() || s.isBlank()) {
            // It had to be a new line otherwise. End paragraph and print it
            System.out.print(decomposer.getParagraph(lineLen));
          }
          else {
            // Get rid of newLine characters and replace with spaces for consistency
            // We read 1 line. There can therefore only be 1 end of line
            decomposer.appendCurrentParagraph(s + " ");
          }
        }
        System.out.print(decomposer.getParagraph(lineLen));
      } catch (NumberFormatException ioe) {
         System.out.println("Error");
      }
    }
}
