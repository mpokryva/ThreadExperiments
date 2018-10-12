import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Counter {

    private int count;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException{
        final int NUM_OPS = 1000000;
        final int NUM_THREADS = 100;
        Counter counter = new Counter();
        ExecutorService es = Executors.newFixedThreadPool(NUM_THREADS);
        try {
            List<Increment> callables = new ArrayList<>();
            for (int i = 0; i < NUM_THREADS; i++) {
                callables.add(new Increment(counter, NUM_OPS));
            }
            List<Future<Integer>> futures = es.invokeAll(callables);
            int max = 0;
            for (Future<Integer> future : futures) {
                max = (future.get() > max) ? future.get() : max;
            }
            System.out.println("Expected: " + NUM_OPS * NUM_THREADS +
                    " | Actual: " + max);
            es.shutdown();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}