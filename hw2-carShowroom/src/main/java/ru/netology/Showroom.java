package ru.netology;

public class Showroom {
    private static final int WAIT_NEW_CAR_TIME = 1000;
    private static final int SELL_TIME = 100;
    private static final int WAIT_TIMEOUT = 2500;

    private static int cntBuyer = 0;
    private final Warehouse warehouse;

    public Showroom() {
        warehouse = new Warehouse();
    }

    public Showroom(int size) {
        warehouse = new Warehouse(size);
    }

    public Showroom(int size, int initialCarCnt) {
        warehouse = new Warehouse(size,initialCarCnt);
    }

    public void receiveCar() {
        synchronized (warehouse) {
            try {
                Thread.sleep(WAIT_NEW_CAR_TIME);
                warehouse.addCar();
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sellCar() {
        synchronized (warehouse) {
            try {
                Thread.currentThread().setName("number " + ++cntBuyer);
                System.out.println("Покупатель " + Thread.currentThread().getName() + " зашел в салон");
                while(warehouse.getCar().size() == 0) {
                    System.out.println("К сожалению, новых машин в наличии нет");
                    warehouse.wait(WAIT_TIMEOUT);
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " снова пришел в салон");
                }
                Thread.sleep(SELL_TIME);
                System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новом авто модели " + warehouse.getCar().get(0));
                warehouse.getCar().remove(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
