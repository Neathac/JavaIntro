public class CodEx {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 10; ++i) {
            System.out.println(getIndentedResult(i, 2) + " * " + args[0] +
            " = " + getIndentedResult(i*Integer.parseInt(args[0]), 1+args[0].length()));
        }
    }

    public static String getIndentedResult(int toIndent, int maxLength) {
        String result = "";
        double order = maxLength - Integer.toString(toIndent).length();
        while (order > 0) {
            order -= 1;
            result += " ";
        }
        result += Integer.toString(toIndent);
        return result;
    }
}
