package ru.netology;

public class MyThread implements Runnable {
    private int sleepTime;

    public MyThread() {
        this.sleepTime = 2500;
    }

    public MyThread(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            int cnt = 0;
            while (!Thread.currentThread().isInterrupted()) {
                cnt++;
                System.out.println("Это поток " + Thread.currentThread().getName() + ", и его сообщение под номером " + cnt + ".");
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + Thread.currentThread().getName() + " был прерван.");
        } finally {
            System.out.println("Поток " + Thread.currentThread().getName() + " завершен.");
        }
    }
}
