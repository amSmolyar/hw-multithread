package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warehouse {
    private List<Car> productList;

    private Random randomInt = new Random();


    public Warehouse() {
        this.productList = new ArrayList<>();
    }

    public Warehouse(int size) {
        this.productList = new ArrayList<>(size);
    }

    public Warehouse(int size, int initialCarCnt) {
        this.productList = new ArrayList<>(size);
        // заполнить салон машинами в количестве initialCarCnt (берутся случайные модели из CarModel):
        for (int ii = 0; ii < initialCarCnt; ii++)
            this.productList.add(new Car(CarModel.values()[randomInt.nextInt(CarModel.values().length)]));
    }

    public List<Car> getCar() {
        return productList;
    }

    public void addCar() {
        CarModel model = CarModel.values()[randomInt.nextInt(CarModel.values().length)];
        this.productList.add(new Car(model));
        System.out.println("В салон поступила новая машина модели " + model.toString());
    }
}
