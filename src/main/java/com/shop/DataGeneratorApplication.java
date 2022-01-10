package com.shop;


import com.shop.dao.Dao;
import com.shop.model.Customer;
import com.shop.model.Promotion;
import com.shop.model.generators.*;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;

import java.util.HashSet;
import java.util.Set;

public class DataGeneratorApplication {
    public static void main(String[] args) {
        int bikesCount = 60;
        int accessoryCount = 20;
        int componentsCount = 20;
        int promotionCount = 2;
        int customerCount = 10;

        Set<Bike> bikes = new HashSet<>();
        Set<Promotion> promotions = new HashSet<>();
        Set<Customer> customers = new HashSet<>();
        for (int i = 0; i < bikesCount; i++) {
            bikes.add(BikeGenerator.generate());
        }
        for (int i = 0; i < accessoryCount; i++) {
            Dao.writeInDb(AccessoryGenerator.generate());
        }
        for (int i = 0; i < componentsCount; i++) {
            Dao.writeInDb(ComponentGenerator.generate());
        }
        for (int i = 0; i < promotionCount; i++) {
            promotions.add(PromotionGenerator.generate());
        }
        for (int i = 0; i < customerCount; i++) {
            customers.add(CustomerGenerator.generate());
        }
        Dao.writeInDbAll(bikes);
        Dao.writeInDbAll(customers);
        Dao.writeInDbAll(promotions);
    }
}
