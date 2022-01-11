package com.shop.model;

import com.shop.model.good.Good;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import com.shop.repositories.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@NoArgsConstructor
public class Shop {

    private double balance = 0;
    private List<Promotion> promotions;
    private List<Bike> bikes;
    private List<Accessory> accessories;
    private List<Component> components;

    public Shop(double balance, List<Promotion> promotions, List<Bike> bikes, List<Accessory> accessories, List<Component> components) {
        this.balance = balance;
        this.promotions = promotions;
        this.bikes = bikes;
        this.accessories = accessories;
        this.components = components;
    }

    public List<Good> simulateWork(List<Customer> customers) {
        List<Good> soldGood = new ArrayList<>();
        for (Customer customer : customers) {
            customer.chooseBike(bikes);
            customer.chooseComponents(components);
            customer.chooseAccessories(accessories);
        }
        for (Customer customer : customers) {
            soldGood.addAll(customer.buySelectedGoods(promotions));
        }
        return soldGood;
    }

    public void makeStatistics () {

    }

    @Override
    public String toString() {
        return "Shop";
    }
}
