class Increment implements Runnable {

    private Counter counter;

    public Increment(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
        System.out.println(counter.getCount());
    }
}