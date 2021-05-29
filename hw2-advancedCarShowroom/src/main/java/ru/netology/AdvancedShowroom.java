package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdvancedShowroom {
    private static final int WAIT_NEW_CAR_TIME = 100;
    private static final int SELL_TIME = 100;
    private static final long WAIT_TIMEOUT = 250000000;

    private static int cntBuyer = 0;
    private final Warehouse warehouse;

    private Lock lock;
    private Condition condition;

    public AdvancedShowroom() {
        warehouse = new Warehouse();
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
    }

    public AdvancedShowroom(int size) {
        warehouse = new Warehouse(size);
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
    }

    public AdvancedShowroom(int size, int initialCarCnt) {
        warehouse = new Warehouse(size, initialCarCnt);
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
    }

    public void receiveCar() {
        lock.lock();
        try {
            Thread.sleep(WAIT_NEW_CAR_TIME);
            warehouse.addCar();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void sellCar() {
        lock.lock();
        try {
            Thread.currentThread().setName("number " + ++cntBuyer);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " зашел в салон");
            while (warehouse.getCar().size() == 0) {
                System.out.println("К сожалению, новых машин в наличии нет");
                condition.awaitNanos(WAIT_TIMEOUT);
                System.out.println("Покупатель " + Thread.currentThread().getName() + " снова пришел в салон");
            }
            Thread.sleep(SELL_TIME);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новом авто модели " + warehouse.getCar().get(0));
            warehouse.getCar().remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
