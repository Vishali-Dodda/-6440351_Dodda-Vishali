import java.lang.reflect.Method;

public class ReflectionExample {

    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName("ReflectionExample");

            // Create an instance of the class
            Object obj = clazz.getDeclaredConstructor().newInstance();

            // Get all declared methods
            Method[] methods = clazz.getDeclaredMethods();

            // Print method names and their parameter types
            for (Method method : methods) {
                System.out.print("Method: " + method.getName() + " | Parameters: ");
                Class<?>[] params = method.getParameterTypes();
                for (Class<?> param : params) {
                    System.out.print(param.getSimpleName() + " ");
                }
                System.out.println();
            }

            // Dynamically invoke greet method
            Method greetMethod = clazz.getDeclaredMethod("greet", String.class);
            greetMethod.invoke(obj, "World");

            // Dynamically invoke add method and print result
            Method addMethod = clazz.getDeclaredMethod("add", int.class, int.class);
            Object result = addMethod.invoke(obj, 5, 7);
            System.out.println("Result of add(5, 7): " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
