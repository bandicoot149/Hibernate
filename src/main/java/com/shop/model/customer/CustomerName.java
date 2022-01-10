package com.shop.model.customer;

public enum CustomerName {
    MIKHAIL("Михаил"),
    RAYNUR("Райнур"),
    LIZA("Лиза"),
    POLINA("Полина"),
    YAROSLAV("Ярослав"),
    YURI("Юрий"),
    ROMAN("Роман"),
    ALEXEY("Алесей");
    public final String name;

    private CustomerName(String name) {
        this.name = name;
    }
}
