package com.example;

enum FooEnum {
    FOO("foo"),
    BAR("bar");

    private final String s;

    FooEnum(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
