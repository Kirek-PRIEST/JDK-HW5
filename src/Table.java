import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private CountDownLatch eaten;
    private List<Philosopher> pList = new ArrayList<>();
    private List<Fork> fList = new ArrayList<>();

    public Table(int num) {
        eaten = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            fList.add(new Fork());
        }
        for (int i = 0; i < num; i++) {
            if (i == 0) {
                pList.add(new Philosopher(eaten, i, num - 1, this));
            } else {
                pList.add(new Philosopher(eaten, i, i - 1, this));
            }
        }
    }

    public synchronized boolean tryGetForks(int id, int leftID) {
        if (!fList.get(id).isUsed() && !fList.get(leftID).isUsed()) {
            System.out.println("Философ " + id + " взял левую вилку");
            fList.get(id).setUsed(true);
            System.out.println("Философ " + id + " взял правую вилку");
            fList.get(leftID).setUsed(true);
            return true;
        }
        return false;
    }

    public void putForks(int id, int leftID) {
        fList.get(id).setUsed(false);
        fList.get(leftID).setUsed(false);
    }

    private void philosopherStart() {
        for (Philosopher philosopher : pList) {
            philosopher.start();
        }
    }

    @Override
    public void run() {
        philosopherStart();
        try {
            eaten.await();
            System.out.println("Все поели");
        } catch (InterruptedException e) {
            System.err.println("Основной поток был прерван: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

    }
}
