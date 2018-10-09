class Decrement implements Runnable {

    private Counter counter;

    public Decrement(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.decrement();
        System.out.println(counter.getCount());
    }
}