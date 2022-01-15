package com.shop.model;

import com.shop.model.customer.Customer;
import com.shop.model.good.Good;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.component.Component;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
public class Shop {

    private double balance = 0;
    private List<Promotion> promotions = new ArrayList<>();
    private List<Bike> bikes = new ArrayList<>();
    private List<Accessory> accessories = new ArrayList<>();
    private List<Component> components = new ArrayList<>();

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

    public HashMap<Customer, Double> getTopBuyersList (List<Good> soldGood) {
        HashMap<Customer, Double> buyersList = new HashMap<Customer, Double>();
        for (Good good : soldGood) {
            if (!buyersList.containsKey(good.getCustomer())) {
                buyersList.put(good.getCustomer(), good.getCustomer().getSumPurchase());
            }
        }

        return buyersList.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i2.getValue().compareTo(
                        i1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (z1, z2) -> z1, LinkedHashMap::new));
    }

    public void makeStatistics () {

    }

    public Brand getBestBikeBrand(List<Bike> bikes) {
        HashMap<Brand, Integer> brandList = new HashMap<Brand, Integer>();
        for (Bike bike : bikes) {
            if (!brandList.containsKey(bike.getBrand())) {
                brandList.put(bike.getBrand(), 1);
            } else {
                brandList.computeIfPresent(bike.getBrand(), (k, v) -> v + 1);
            }
        }
        Optional<Map.Entry<Brand, Integer>> maxEntry = brandList.entrySet().stream()
                .max((Map.Entry<Brand, Integer> e1, Map.Entry<Brand, Integer> e2) -> e1.getValue()
                        .compareTo(e2.getValue()));
        return  maxEntry.get().getKey();
    }

    public void getBestTypeComponent (List<Component> components) {

    }

    @Override
    public String toString() {
        return "Shop";
    }
}
