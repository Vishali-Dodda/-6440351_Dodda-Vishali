import java.util.List;
import java.util.stream.Collectors;

public class RecordExample {
    // Define the record
    public record Person(String name, int age) {}

    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 30);
        Person p3 = new Person("Charlie", 17);

        // Print instances
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // List of persons
        List<Person> people = List.of(p1, p2, p3);

        // Filter persons aged 18 and above
        List<Person> adults = people.stream()
                                    .filter(person -> person.age() >= 18)
                                    .collect(Collectors.toList());

        System.out.println("Adults: " + adults);
    }
}
