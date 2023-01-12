package javaIntro.grafm;
import java.io.*;

import javaIntro.grafm.LifeSimulator;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] line = reader.readLine().split(" ");
            LifeSimulator sim = new LifeSimulator(Integer.parseInt(line[0]));
            int numOfSteps = Integer.parseInt(line[1]);

            for (int i = 0; i < sim.ROW_NUM; ++i) {
                sim.defineRow(i, reader.readLine());
            }
            sim.performNSteps(numOfSteps);

            sim.printOutBoard();
            reader.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
