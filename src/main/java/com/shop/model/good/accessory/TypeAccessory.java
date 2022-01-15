package com.shop.model.good.accessory;

public enum TypeAccessory {
    FOOTREST("Подножка"),
    BELL("Звонок"),
    BAG("Багажник"),
    FLASK("Фляжка");
    public final String name;

    private TypeAccessory(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
