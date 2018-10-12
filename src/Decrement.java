import java.util.concurrent.Callable;

class Decrement implements Callable<Integer> {

    private Counter counter;
    private int decrementAmount;

    public Decrement(Counter counter, int decrementAmount) {
        this.counter = counter;
        this.decrementAmount = decrementAmount;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < decrementAmount; i++) {
            counter.decrement();
        }
        return counter.getCount();
    }
}