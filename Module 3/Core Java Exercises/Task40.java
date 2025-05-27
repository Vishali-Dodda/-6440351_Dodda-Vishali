public class VirtualThreadsExample {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 100_000;

        // Measure time for virtual threads
        long startVirtual = System.currentTimeMillis();
        Thread[] virtualThreads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            virtualThreads[i] = Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread " + Thread.currentThread().threadId());
            });
        }
        for (Thread t : virtualThreads) {
            t.join();
        }
        long endVirtual = System.currentTimeMillis();
        System.out.println("Virtual threads time: " + (endVirtual - startVirtual) + " ms");

        // Measure time for traditional threads
        long startTraditional = System.currentTimeMillis();
        Thread[] traditionalThreads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            traditionalThreads[i] = new Thread(() -> {
                System.out.println("Hello from traditional thread " + Thread.currentThread().getId());
            });
            traditionalThreads[i].start();
        }
        for (Thread t : traditionalThreads) {
            t.join();
        }
        long endTraditional = System.currentTimeMillis();
        System.out.println("Traditional threads time: " + (endTraditional - startTraditional) + " ms");
    }
}
