package javaIntro.grafm;

import java.util.ArrayList;

public class Paragraph {
    private ArrayList<String> vals;
    private int pos = 0;

    Paragraph() {
        vals = new ArrayList<String>();
    }

    public void insertNew(String s) {
        vals.add(s);
    }

    public int getSize() {
        return vals.size();
    }

    public String getWholeParagraph(int lineLen) {
        String result = ""; 
        while (pos < vals.size() - 1) {
            result += getLineOfLen(lineLen);
        }
        return result;
    }

    public String getLineOfLen(int len) {
        int currLen = 0;
        int wordNum = 0;
        ArrayList<String> wordList = new ArrayList<String>();
        boolean fillInNeeded = false;
        String result = "";
        // We still have space && we don't yet need to add extra spaces && we didn't reach the end of the paragraph
        while (len > currLen && !fillInNeeded && pos < vals.size()) {
            String temp = vals.get(pos);
            // We have space for an extra word 
            if (currLen + temp.length() <= len) {
                wordList.add(temp);
                // Length of output words + length of the new word + space between them
                currLen += temp.length();
                // Number of words in the output
                wordNum += 1;
                // Position in list of words in paragraph
                pos += 1;
            } 
            // We don't want trailing spaces
            else if(currLen + temp.stripTrailing().length() <= len) {
                wordList.add(temp.stripTrailing());
                // Length of output words + length of the new word + space between them
                currLen += temp.stripTrailing().length();
                // Number of words in the output
                wordNum += 1;
                // Position in list of words in paragraph
                pos += 1;
            } else {
                // We can't add another word because the length of line would overflow, 
                // but the line needs extra spaces to match the desired length
                fillInNeeded = true;
            }
        }

        if(fillInNeeded){
            // How many total extra spaces we need
            int difference = len - currLen;
            int index = 0;

            while(difference >= 0) {
                wordList.set(index, wordList.get(index) + " ");
                index += 1;
                difference -= 1;
                // Appending spaces to the last word is undesirable. It's meant to end the line
                index = index % (wordNum-1);
            }
        }
        for (String word : wordList) {
            result += word;
        }

        return result + "\n";

    }

}
