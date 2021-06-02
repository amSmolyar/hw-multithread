package ru.netology;

import java.util.concurrent.*;

public class MainBox {
    static final int PROCESS_LIFETIME = 40000;          // milliseconds (время работы)
    static final long LAUNCHER_INITIAL_DELAY = 1000;    // milliseconds
    static final long OPEN_PERIOD = 5000;               // milliseconds
    static final int TIME_TO_OPEN = 200;                // milliseconds
    static final int TIME_TO_CLOSE = 2000;              // milliseconds

    static int n_iteration = 1;

    static volatile boolean boxIsOpen = false;

    public static void main(String[] args) throws InterruptedException {
        // пул для потока, открывающего коробочку:
        ScheduledExecutorService executorServiceLauncher = Executors.newSingleThreadScheduledExecutor();
        // запуск потока, открывающего коробочку:
        Runnable launcher = new Launcher();
        ScheduledFuture<?> launcherFuture = executorServiceLauncher.scheduleAtFixedRate(launcher, LAUNCHER_INITIAL_DELAY, OPEN_PERIOD, TimeUnit.MILLISECONDS);
        // запуск потока, закрывающего коробочку:
        Thread stopThread = new Thread(new Stopper());
        stopThread.start();

        executorServiceLauncher.schedule(() -> launcherFuture.cancel(true), PROCESS_LIFETIME, TimeUnit.MILLISECONDS);

        // Основной поток ждет и закрывает вспомогательные:
        Thread.sleep(PROCESS_LIFETIME);
        executorServiceLauncher.shutdown();
        stopThread.interrupt();

    }

    static class Stopper implements Runnable {
        public void run() {
            try {
                for (int ii = 0; ii < n_iteration; ii++) {
                    Thread.yield();

                    while (!boxIsOpen) {
                    }

                    System.out.println("Коробочка закрывается..");
                    Thread.sleep(TIME_TO_CLOSE);
                    System.out.println("Коробочка закрыта");
                    boxIsOpen = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Launcher implements Runnable {
        public void run() {
            try {
                System.out.println("============== цикл " + n_iteration++ + " =============");
                System.out.println("Коробочка открывается..");
                Thread.sleep(TIME_TO_OPEN);
                System.out.println("Коробочка открыта");
                boxIsOpen = true;
                while (boxIsOpen) {
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
