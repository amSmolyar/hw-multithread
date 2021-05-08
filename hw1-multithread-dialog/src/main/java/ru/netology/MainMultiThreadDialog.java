package ru.netology;

public class MainMultiThreadDialog {
    public static void main(String[] args) throws InterruptedException {

        // создание группы потоков, которой можно управлять совместно:
        ThreadGroup threadGroup = new ThreadGroup("myThreadGroup");

        // создание 1-го потока:
        Runnable myRunnable1 = new MyThread();
        Thread thread1 = new Thread(threadGroup, myRunnable1);
        thread1.setName("First");

        // создание 2-го потока:
        Runnable myRunnable2 = new MyThread(3000);
        Thread thread2 = new Thread(threadGroup, myRunnable2);
        thread2.setName("Second");

        // создание 3-го потока:
        Runnable myRunnable3 = new MyThread(4000);
        Thread thread3 = new Thread(threadGroup, myRunnable3);
        thread3.setName("Third");

        // создание 4-го потока:
        Runnable myRunnable4 = new MyThread(5000);
        Thread thread4 = new Thread(threadGroup, myRunnable4);
        thread4.setName("Fourth");

        // запуск потоков:
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // ожидание основного потока (15 сек):
        Thread.sleep(15000);

        // завершение всех потоков:
        threadGroup.interrupt();
    }

}
