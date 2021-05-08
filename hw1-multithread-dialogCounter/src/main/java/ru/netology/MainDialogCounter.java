package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDialogCounter {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // создание пула с фиксированным числом потоков (4):
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // создание списка задач типа Callable:
        List<MyCallable> taskList = new ArrayList<>();
        for (int ii = 0; ii < 4; ii++) {
            // параметр MyCallable - количество циклов выполнения задачи
            taskList.add(new MyCallable(ii + 2));
        }

        System.out.println("\n\n==================== invokeAll ====================\n\n");

        // отправка на выполнение в пул потоков всех задач из списка:
        List<Future<Integer>> futures = executor.invokeAll(taskList);

        // чтение результатов выполнения всех задач:
        for (Future<Integer> f : futures) {
            System.out.println(f.get());
        }

        System.out.println("\n\n==================== invokeAny ====================\n\n");

        // отправка на выполнение в пул потоков. Ожидание результата первой выполненной задачи из списка:
        Integer futureAny = executor.invokeAny(taskList);
        System.out.println("Результат первой выполненной задачи: " + futureAny);

        System.out.println("\n\n===================================================\n\n");

        // закрытие пула потоков:
        System.out.println("Завершение работы");
        executor.shutdown();
    }
}
