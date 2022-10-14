package main.grafm;

import main.grafm.DynamicArray;

public class Main {
    public static void main(String[] args) throws Exception {
        DynamicArray dynArray = new DynamicArray();

        for (int i = 0; i < args.length; ++i) {
            dynArray.add(args[i]);
        }

        for (int i = 0; i < dynArray.getElementCount(); ++i) {
            System.out.println(dynArray.get(i));
        }
    }
}
