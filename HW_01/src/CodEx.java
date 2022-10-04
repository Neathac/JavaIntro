public class CodEx {
    public static void main(String[] args) throws Exception {
        try {
            for (int i = 1; i < 10; ++i) {
                System.out.println(" " + i + " * " + args[0] + " = " + (i*Integer.parseInt(args[0])));
            }
            System.out.println(10 + " * " + args[0] + " = " + (10*Integer.parseInt(args[0])));
        } catch (Exception e) {}
        
    }
}
