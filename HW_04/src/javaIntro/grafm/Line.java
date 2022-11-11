package javaIntro.grafm;
import java.util.ArrayList;
import java.util.StringJoiner;

import javaIntro.grafm.Word;

public class Line {
    private ArrayList<Word> vals;
    private int lineLen;
    private int currentLineLen;

    Line(int lineLen) {
        this.currentLineLen = 0;
        this.lineLen = lineLen;
        this.vals = new ArrayList<Word>();
    }

    public void insertNewWord(Word word) {
        // We don't want to insert empty words
        if (word.getTextLength() > 0) {
            // Line shouldn't start with whitespaces
            if (vals.size() == 0) {
                word.removeBlankSpace();
                vals.add(word);
            } else {
                vals.add(word);
            }
            currentLineLen += word.wordLen(); 
        }
    }

    public boolean doesWordFit(Word word) {
        if ((currentLineLen + word.wordLen()) <= lineLen) {
            return true;
        }
        return false;
    }

    public String getParagraphEnd() {
        StringJoiner result = new StringJoiner("");
        for (int i = 0; i < vals.size(); ++i) {
            result.add(vals.get(i).getText());
        }
        return result.toString();
    }

    public int getLineLength() {
        return currentLineLen;
    }

    public String getParagraphLine() {
        if (currentLineLen >= lineLen || vals.size() == 0 || vals.size() == 1) {
            return getParagraphEnd();
        } 
        int difference = lineLen - currentLineLen;
        int pos = 1;
        while (difference > 0) {
            vals.get(pos).addLeadingSpaces(1);
            pos += 1;
            difference -= 1;
            if(pos == vals.size()) {
                pos = 1;
            }
        } 
        return getParagraphEnd();
    }
}
