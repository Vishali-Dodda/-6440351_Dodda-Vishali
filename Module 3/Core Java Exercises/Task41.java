import java.util.concurrent.*;
import java.util.*;

public class ExecutorCallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Callable<String>> tasks = new ArrayList<>();
        // Create 5 tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            tasks.add(() -> {
                Thread.sleep(1000);  // Simulate work
                return "Result from task " + taskId;
            });
        }

        // Submit all tasks and get Futures
        List<Future<String>> futures = executor.invokeAll(tasks);

        // Retrieve and print results
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}
