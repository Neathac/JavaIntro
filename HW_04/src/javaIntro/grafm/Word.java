package javaIntro.grafm;

public class Word {
    private StringBuilder s;
    private int textLen;

    Word(char c) {
        this.s = new StringBuilder("");
        this.s.append(c);
        textLen = 0;
    }

    Word() {
        this.s = new StringBuilder("");
        textLen = 0;
    }

    public int wordLen() {
        return s.length();
    }

    public void appendWord(char c) {
        this.s.append(c);
        if(!Character.isWhitespace(c)) {
            textLen += 1;
        }
    }

    public String getText() {
        return this.s.toString();
    }

    public int getTextLength() {
        return this.textLen;
    }

    public void addLeadingSpaces(int num) {
        for (int i = 0; i < num; ++i) {
            this.s.insert(0, ' ');
        }
    }

    public void removeBlankSpace() {
        int j = 0;
        for(int i = 0; i < this.s.length(); i++) {
          if (!Character.isWhitespace(s.charAt(i))) {
             s.setCharAt(j++, s.charAt(i));
          }
        }
        s.delete(j, s.length());
      }
}
