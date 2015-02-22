package com.farukon;

public class Car {
    public String maker;
    public String name;

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car {" +
                "maker='" + maker + "\', " +
                "name='" + name + "\'" +
                "}";
    }
}
