public class OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        int result3 = 20 / 2 + 3 * 4 - 1;

        System.out.println("Result of 10 + 5 * 2 = " + result1); // Multiplication before addition
        System.out.println("Result of (10 + 5) * 2 = " + result2); // Parentheses first
        System.out.println("Result of 20 / 2 + 3 * 4 - 1 = " + result3); // Division, multiplication, then addition and subtraction
    }
}
