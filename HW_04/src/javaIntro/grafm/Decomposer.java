package javaIntro.grafm;
import javaIntro.grafm.Paragraph;

public class Decomposer {
    private Paragraph paragraph;
    String tempWord = "";

    Decomposer() {
        paragraph = new Paragraph();
    }

    public boolean insertNextLine(String line) {
        for(String var : line.split(" ")){
            paragraph.insertNew(var);
        }
        return false;
    }

    public void appendCurrentParagraph(String s) {
        boolean isBetweenWords = false;

        for(char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                isBetweenWords = true;
                appendCurrentWord(c);
            } else if(isBetweenWords) {
                isBetweenWords = false;
                insertNewWord();
                appendCurrentWord(c);
            } else {
                appendCurrentWord(c);
            }
        }
        insertNewWord();
    }

    public void insertNewWord() {
        if(tempWord != "") {
            paragraph.insertNew(tempWord);
        }
        tempWord = "";
    }

    public void appendCurrentWord(char c) {
        tempWord += c;
    }

    public String getParagraph(int lineLen) {
        String result = paragraph.getWholeParagraph(lineLen);
        paragraph = new Paragraph();
        return result + "\n";
    }
}
