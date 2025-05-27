import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("Diana");

        // Sort using lambda expression
        Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));

        System.out.println("Sorted list:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
