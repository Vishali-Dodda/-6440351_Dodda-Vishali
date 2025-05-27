public class PatternMatchingSwitch {

    public static void checkObjectType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Object is an Integer: " + i);
            case String s -> System.out.println("Object is a String: " + s);
            case Double d -> System.out.println("Object is a Double: " + d);
            case null -> System.out.println("Object is null");
            default -> System.out.println("Unknown type: " + obj.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        checkObjectType(42);
        checkObjectType("Hello");
        checkObjectType(3.14);
        checkObjectType(true);
        checkObjectType(null);
    }
}
