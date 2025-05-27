public class TypeCastingExample {
    public static void main(String[] args) {
        double myDouble = 9.78;
        int castedInt = (int) myDouble;  // Explicit casting from double to int
        System.out.println("Double value: " + myDouble);
        System.out.println("Casted to int: " + castedInt);

        int myInt = 25;
        double castedDouble = myInt;  // Implicit casting from int to double
        System.out.println("Int value: " + myInt);
        System.out.println("Casted to double: " + castedDouble);
    }
}
