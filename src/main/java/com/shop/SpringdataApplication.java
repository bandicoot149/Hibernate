package com.shop;

import com.shop.dao.*;
import com.shop.model.Customer;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;

import java.util.List;

public class SpringdataApplication {


    public static void main(String[] args) {
        List<Customer> customers = CustomerDao.findAll();
        List<Bike> bikes = BikeDao.findAll();
        List<Accessory> accessories = AccessoryDao.findAll();
        List<Component> components = ComponentDao.findAll();
        for (Customer customer : customers) {
            customer.chooseBike(bikes);
            customer.chooseAccessories(accessories);
            customer.chooseComponents(components);
        }
        for (Customer customer : customers) {
            customer.buySelectedGoods();
        }
        for (Customer customer : customers) {
            customer.getAmountOfPurchase();
        }
    }

}
