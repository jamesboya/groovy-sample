package com.farukon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CarBuilder {
    private List<Car> carList;

    public CarBuilder car(Consumer<Car> cl) {
        Car car = new Car();
        cl.accept(car);

        if (carList == null) {
            carList = new ArrayList<>();
        }
        carList.add(car);

        return this;
    }

    public List<Car> get() {
        return carList;
    }
}

