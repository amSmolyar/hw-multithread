package ru.netology;

import java.util.concurrent.*;

public class MainCarShowroom {
    static final int MAX_CAR_CNT_IN_SHOWROOM = 10;
    static final int START_CAR_CNT_IN_SHOWROOM = 0;
    static final int BUYER_CNT = 10;

    public static void main(String[] args) {
        // пускай в салоне будет изначально START_CAR_CNT_IN_SHOWROOM машин:
        final Showroom showroom = new Showroom(MAX_CAR_CNT_IN_SHOWROOM, START_CAR_CNT_IN_SHOWROOM);

        // поток-поставщик:
        ExecutorService executorSupplier = Executors.newSingleThreadExecutor();
        for (int ii = 0; ii < BUYER_CNT; ii++) {
            executorSupplier.submit(() -> showroom.receiveCar());
        }

        //executorServiceSupplier.schedule(() -> supplierFuture.cancel(true), PROCESS_LIVETIME, TimeUnit.MILLISECONDS);

        // пул потоков покупателей:
        ExecutorService executor = Executors.newFixedThreadPool(BUYER_CNT);
        for (int ii = 0; ii < BUYER_CNT; ii++) {
            executor.submit(showroom::sellCar);
        }

        executor.shutdown();
        executorSupplier.shutdown();
    }

}
