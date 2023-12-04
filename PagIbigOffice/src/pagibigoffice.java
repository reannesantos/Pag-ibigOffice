import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PagIbigOffice {

    private final BlockingQueue<Integer> queue;
    private final Object resetLock = new Object();

    public PagIbigOffice() {
        queue = new LinkedBlockingQueue<>();
    }

    public void addToQueue() {
        int nextNumber = queue.size() + 1;
        queue.add(nextNumber);
    }

    public void resetQueue(int newNumber) {
        synchronized (resetLock) {
            queue.clear();
            queue.add(newNumber);
        }
    }

    public int getCurrentQueueNumber() {
        return queue.peek();
    }

    public static void main(String[] args) {
        PagIbigOffice office = new PagIbigOffice();

        office.addToQueue();
        System.out.println("Current queue number: " + office.getCurrentQueueNumber());

        office.addToQueue();
        System.out.println("Current queue number: " + office.getCurrentQueueNumber());

        office.resetQueue(10);
        System.out.println("Queue has been reset. Current queue number: " + office.getCurrentQueueNumber());
    }
}