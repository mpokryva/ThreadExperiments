/**
 * Created by mpokr on 10/12/2018.
 */
public class BlockingQueue {
    private int[] queue;
    private int first;
    private int last;
    private int size;

    public BlockingQueue(int capacity) {
        this.queue = new int[capacity];
        first = 0;
        last = -1;
        size = 0;
    }

    public synchronized void enqueue(int n) throws InterruptedException {
        while (size == queue.length) {
            this.wait();
        }
        last = (last + 1) % queue.length;
        queue[last] = n;
        size++;
        if (size == 1) {
            this.notifyAll();
        }
    }

    public synchronized int dequeue() throws InterruptedException {
        int dequeued = 0;
        while (size == 0) {
            this.wait();
        }
        first = (first + 1) % queue.length;
        queue[first] = -1; // Only for debugging purposes.
        size--;
        if (size == queue.length - 1) {
            this.notifyAll();
        }
        return dequeued;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread();
    }

}
