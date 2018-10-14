import java.util.concurrent.locks.Lock;

/**
 * Created by mpokr on 10/13/2018.
 */
public class ReentrantLock {

    private int lockCount;
    private Thread lockingThread;
    private final Object lock;

    public ReentrantLock() {
        lock = new Object();
    }


    public void lock() throws InterruptedException {
        synchronized (lock) {
            while (lockCount > 0 && lockingThread != Thread.currentThread()) {
                this.wait();
            }
            lockingThread = Thread.currentThread();
            lockCount++;
        }
    }

    public void unlock() {
        synchronized (lock) {
            if (lockingThread == Thread.currentThread()) {
                lockCount--;
            }
            if (lockCount == 0 && lockingThread != null) {
                lockingThread = null;
                this.notify();
            }
        }
    }


}
