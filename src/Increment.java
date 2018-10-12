import java.util.concurrent.Callable;

class Increment implements Callable<Integer> {

    private Counter counter;
    private int incrementAmount;

    public Increment(Counter counter, int incrementAmount) {
        this.counter = counter;
        this.incrementAmount = incrementAmount;
    }

    @Override
    public Integer call() {
        for (int i = 0; i < incrementAmount; i++) {
            counter.increment();
        }
        return counter.getCount();
    }
}