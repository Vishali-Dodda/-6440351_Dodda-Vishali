import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String name;

        System.out.println("Enter student names (type 'exit' to stop):");
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            studentNames.add(name);
        }

        System.out.println("\nStudent Names:");
        for (String studentName : studentNames) {
            System.out.println(studentName);
        }

        scanner.close();
    }
}
