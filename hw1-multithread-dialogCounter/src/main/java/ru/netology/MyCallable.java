package ru.netology;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private int messageCnt;
    private final int cycleCnt;

    public MyCallable() {
        this.messageCnt = 0;
        this.cycleCnt = 5;
    }

    public MyCallable(int cycleCnt) {
        this.messageCnt = 0;
        this.cycleCnt = cycleCnt;
    }

    @Override
    public Integer call() throws Exception {
        try {
            int ii = 0;
            while (!Thread.currentThread().isInterrupted() && ii < cycleCnt) {
                ii++;
                messageCnt++;
                System.out.println("Это поток " + Thread.currentThread().getName() + ", и его сообщение под номером " + messageCnt + ".");
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + Thread.currentThread().getName() + " был прерван.");
        } finally {
            System.out.println("Поток " + Thread.currentThread().getName() + " завершен.");
            return messageCnt;
        }
    }
}
