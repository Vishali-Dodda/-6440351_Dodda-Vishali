public class MethodOverloading {

    // Method with two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Method with two doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Method with three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println("Sum of two integers: " + add(5, 10));
        System.out.println("Sum of two doubles: " + add(3.5, 2.5));
        System.out.println("Sum of three integers: " + add(1, 2, 3));
    }
}
