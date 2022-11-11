package javaIntro.grafm;

import java.io.*;

import javaIntro.grafm.Line;
import javaIntro.grafm.Word;

public class Main {

    public static void main(String[] args) throws Exception {

      try {
        InputStreamReader in = new InputStreamReader(System.in);

        String sLineLen = "";
        int code;
        while (!Character.isWhitespace(code = in.read())) {
          sLineLen += (char) code;
        }

        int lineLen = Integer.parseInt(sLineLen);
        Line line = new Line(lineLen);
        Word word = new Word();
        
        boolean isBetweenWords = true;
        boolean wasLastLineEmpty = false;

        while ((code = in.read()) != -1) {

          char c = (char) code;
          if (Character.isWhitespace(c)) {
            // ####################################################
            // Taking care of paragraph ends and newlines
            // ####################################################
            if (!String.valueOf(c).matches(".")) {
              // This is a whitespace line
              if (wasLastLineEmpty) {
                // Last line was whitespace too. Check if we should print a paragraph end
                if (line.getLineLength() > 0) {
                  System.out.println(line.getParagraphEnd());
                  System.out.println();
                  line = new Line(lineLen);
                }
              } 
              // ####################################################
              // New Line at the end of a word
              // ####################################################
              else {
                if (line.doesWordFit(word)) {
                  line.insertNewWord(word);
                } else {
                  System.out.println(line.getParagraphLine());
                  line = new Line(lineLen);
                  line.insertNewWord(word);
                }
              }
              word = new Word(' ');
              isBetweenWords = true;
              wasLastLineEmpty = true;
            }
            // ####################################################
            // Trailing whitespaces equal a new word
            // ####################################################
            else if (isBetweenWords) {
              word.appendWord(c);
            } else {
              if (line.doesWordFit(word)) {
                line.insertNewWord(word);
              } else {
                System.out.println(line.getParagraphLine());
                line = new Line(lineLen);
                line.insertNewWord(word);
              }
              word = new Word(' ');
            }
          } else {
            // Some non-whitespace character encountered. Just remember we're mid-paragraph
            wasLastLineEmpty = false;
            isBetweenWords = false;
            word.appendWord(c);
          }
        }
        System.out.println(line.getParagraphEnd());
      } catch (NumberFormatException ioe) {
         System.out.println("Error");
      }
    }
}

