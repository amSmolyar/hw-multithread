package ru.netology;

public class Supplier implements Runnable {
    Showroom showroom;

    public Supplier(Showroom showroom) {
        this.showroom = showroom;
    }

    @Override
    public void run() {
        showroom.receiveCar();
    }
}
