package ru.netology;

public class Deal {
    private double length;
    private double width;
    private double price;
    private boolean honesty;

    public Deal(double length, double width, double price) {
        this.length = length;
        this.width = width;
        this.price = price;
    }

    public void setHonesty(boolean honesty) {
        this.honesty = honesty;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHonesty() {
        return honesty;
    }

    @Override
    public String toString() {
        return "\n\nСделка{" +
                "\n   длина участка: " + length +
                "\n   ширина участка: " + width +
                "\n   стоимость сделки: " + price +
                "\n   сделка " + (honesty ? "честная" : "не честная") +
                "\n}";
    }

}
