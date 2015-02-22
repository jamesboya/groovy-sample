package com.farukon;

import java.util.List;

public class SampleApp {
    public static void main(String[] args) {
        List<Car> cars = new CarBuilder()
                .car(car -> {
                    car.setName("MITSUBISHI TRITON");
                    car.setMaker("MITSUBISHI");
                })
                .car(car -> {
                    car.setName("SKODA FABIA ELEGANCE");
                    car.setMaker("SKODA");
                })
                .car(car -> {
                    car.setName("MINI COOPER");
                    car.setMaker("MINI");
                })
                .get();

        cars.forEach(System.out::println);
    }
}
