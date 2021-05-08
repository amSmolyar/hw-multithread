package ru.netology;

import java.util.concurrent.*;

public class MainArrayCalculator {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        double arraySum;    // сумма всех элементов массива

        // создание массива double размером SIZE:
        final int SIZE = 200000000;
        double[] array = new double[SIZE];
        for (int ii = 0; ii < SIZE; ii++)
            array[ii] = Math.random();

        Calculator calc = new Calculator(array, 0, array.length);

        System.out.println("Доступное число потоков: " + Runtime.getRuntime().availableProcessors());

        System.out.println("\n\n==================== многопоточное решение ====================\n\n");

        // создание пула потоков ForkJoinPool и добавление задачи:
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.nanoTime();
        pool.invoke(calc);

        // получение результата:
        arraySum = calc.join();
        long finishTime = System.nanoTime();
        System.out.println("Сумма элементов массива: " + arraySum);
        System.out.println("Среднее арифметическое элементов массива: " + arraySum / SIZE);
        System.out.println("Время, затраченное на решение задачи: " + (finishTime - startTime));

        System.out.println("использовалось потоков: " + pool.getPoolSize());
        pool.shutdown();

        System.out.println("\n\n==================== однопоточное решение ====================\n\n");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        startTime = System.nanoTime();

        Future<Double> future = executor.submit(
                () -> {
                    double sum = 0;
                    for (int ii = 0; ii < SIZE; ii++)
                        sum += array[ii];
                    return sum;
                });

        arraySum = future.get();
        finishTime = System.nanoTime();

        executor.shutdown();

        System.out.println("Сумма элементов массива: " + arraySum);
        System.out.println("Среднее арифметическое элементов массива: " + arraySum / SIZE);
        System.out.println("Время, затраченное на решение задачи: " + (finishTime - startTime));

    }
}
