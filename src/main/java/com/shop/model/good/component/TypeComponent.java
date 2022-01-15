package com.shop.model.good.component;

public enum TypeComponent {
    HANDLEBAR("Руль"),
    FORK("Вилка"),
    CHAIN("Цепь"),
    WHEEL("Колесо"),
    SADDLE("Седло");
    public final String name;

    private TypeComponent(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
