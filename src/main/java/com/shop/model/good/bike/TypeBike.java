package com.shop.model.good.bike;

public enum TypeBike {
    CHILD("Детский"),
    WOMEN("Женский"),
    MOUNTAIN("Горный"),
    BMX("BMX"),
    TRACK("Трековый");
    public final String name;

    private TypeBike(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
