package com.shop.model.good;

public enum GoodStatus {
    IN_STOCK("В наличии"),
    SOLD_OUT("Продано");
    public final String name;

    private GoodStatus(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
