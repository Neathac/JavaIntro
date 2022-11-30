package javaIntro.grafm;

public class StatTracker {
    public int emptyComment = 0;
    public int nologinShell = 0;
    public int uidUsers = 0;
    public int users = 0;

    private int loginPos = 0;
    private int passwordPos = 1;
    private int uidPos = 2;
    private int gidPos = 3;
    private int commentPos = 4;
    private int homedirPos = 5;
    private int shellPos = 6;

    public void parseEntry(String[] entry) {
        users += 1;
        if (entry[shellPos].contains("/sbin/nologin")) {
            nologinShell += 1;    
        }
        if (Integer.parseInt(entry[uidPos].strip()) == 0) {
            uidUsers += 1;    
        }
        if (entry[commentPos].length() == 0) {
            emptyComment += 1;    
        }
    }
}
