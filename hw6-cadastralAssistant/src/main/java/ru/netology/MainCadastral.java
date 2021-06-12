package ru.netology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainCadastral {
    public static final double MIN_SQR_METER_PRICE = 100.5;
    public static final double MAX_SQR_METER_PRICE = 150.6;

    public static void main(String[] args) {
        List<Deal> dealList = new ArrayList<>();
        dealList.add(new Deal(12.4, 105.1, 1100000));

        checkHonesty(dealList, MIN_SQR_METER_PRICE, MAX_SQR_METER_PRICE)
                .forEach(System.out::println);
    }

    public static List<Deal> checkHonesty(List<Deal> deals, double minSqrMeterPrice, double maxSqrMeterPrice) {
        return deals.stream()
                .map(x -> ((x.getLength() * x.getWidth() * minSqrMeterPrice <= x.getPrice()) && (x.getLength() * x.getWidth() * maxSqrMeterPrice >= x.getPrice())) ? x.setHonesty(true) : x.setHonesty(false))
                .collect(Collectors.toList());
    }

    
}
