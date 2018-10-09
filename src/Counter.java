public class Counter {

    private int count;

    public synchronized void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        final int NUM_THREADS = 1000;
        Counter test = new Counter();
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(new Increment(test));
            t.start();
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(new Decrement(test));
            t.start();
        }
    }
}