package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class MainTax {
    public static void main(String[] args) throws InterruptedException {
        Proceed proceed = new Proceed();

        // массивы целых положительных чисел:
        int[] proceedsFrom1 = arrayGen(150, 10, 1000);
        int[] proceedsFrom2 = arrayGen(200, 10, 1000);
        int[] proceedsFrom3 = arrayGen(250, 10, 1000);

        Thread thread1 = new Thread(null, () -> proceed.calc(proceedsFrom1), "Выручка по 1-му магазину");
        Thread thread2 = new Thread(null, () -> proceed.calc(proceedsFrom2), "Выручка по 2-му магазину");
        Thread thread3 = new Thread(null, () -> proceed.calc(proceedsFrom3), "Выручка по 3-му магазину");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("\n\nОбщая выручка: " + proceed.getCurrentSum());
        
    }

    static class Proceed {
        AtomicLong allProceed = new AtomicLong(0);
        public void calc(int[] array) {
            for (int ii = 0; ii < array.length; ii++) {
                allProceed.addAndGet(array[ii]);
            }
        }

        public long getCurrentSum() {
            return allProceed.get();
        }
    }

    public static int[] arrayGen(int size, int minPrice, int maxPrice) {
        Random random = new Random();
        int[] array = new int[size];
        for (int ii = 0; ii < size; ii++) {
            array[ii] = minPrice + random.nextInt(maxPrice - minPrice);
        }
        return array;
    }
}
