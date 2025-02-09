import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    int id;
    int leftID;
    CountDownLatch eaten;
    int countEat = 0;
    Table table;

    public Philosopher(CountDownLatch eaten, int id, int leftID, Table table) {
        this.id = id;
        this.eaten = eaten;
        this.leftID = leftID;
        this.table = table;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет");
        sleep(1000);
    }

    private void eat() throws InterruptedException {
        if (table.tryGetForks(id, leftID)) {
            System.out.println("Философ " + id + " начал есть");
            sleep(1000);
            System.out.println("Философ " + id + " поел");
            table.putForks(id, leftID);
            countEat++;
        }

    }


    @Override
    public void run() {
        while (countEat < 3) {
            try {
                eat();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (eaten.getCount() != 0) {
                try {
                    think();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        eaten.countDown();
    }
}

