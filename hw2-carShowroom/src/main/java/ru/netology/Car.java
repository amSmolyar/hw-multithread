package ru.netology;

public class Car {
    private CarModel model;

    public Car(CarModel model) {
        this.model = model;
    }

    public CarModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return this.model.toString();
    }
}
