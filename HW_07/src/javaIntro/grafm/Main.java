package javaIntro.grafm;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            StatTracker tracker = new StatTracker();
            String line = "";
            while((line = reader.readLine())!= null) {
                tracker.parseEntry(line.split(":"));
            }

            System.out.println("Users: " + Integer.toString(tracker.users));
            System.out.println("Users with UID 0: " + Integer.toString(tracker.uidUsers));
            System.out.println("Users with /sbin/nologin shell: " + Integer.toString(tracker.nologinShell));
            System.out.println("Users with empty comment field: " + Integer.toString(tracker.emptyComment));
            reader.close();
        } catch(Exception e) {
            System.out.println("Input error");
        }
    }
}
