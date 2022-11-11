package javaIntro.grafm;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            int[] inputs = new int[args.length];
            for (int i = 0; i < args.length; ++i) {
                inputs[i] = Integer.parseInt(args[i]);
            }
            quickSort(inputs, 0, args.length - 1);
            for (int i = 0; i < args.length; ++i) {
                System.out.println(inputs[i]);
            }
        } catch(Exception e) {
            System.out.println("Input error");
        }
    }

    static void quickSort(int[] valsToSort, int bottom, int top) {
        if ( bottom < top) {
            
            int pivot = valsToSort[top];
            int index = bottom - 1;
            for (int i = bottom; i < top; ++i) {
                if(valsToSort[i] <= pivot) {
                    index++;
                    int temp = valsToSort[index];
                    valsToSort[index] = valsToSort[i];
                    valsToSort[i] = temp;
                }
            }
            index++;
            int temp = valsToSort[index];
            valsToSort[index] = valsToSort[top];
            valsToSort[top] = temp;

            quickSort(valsToSort, bottom, index-1);
            quickSort(valsToSort, index+1, top);
        }
    }
}
