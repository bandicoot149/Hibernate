package com.shop.model.generators;

import com.shop.model.Customer;
import com.shop.model.good.accessory.TypeAccessory;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.TypeComponent;

import java.util.Random;

public class CustomerGenerator {


    public static Customer generate() {
        Random random = new Random();
        Customer customer = new Customer();
        customer.setBalance(random.nextInt(200) + 100);
        customer.setNeededTypeBike(TypeBike.values()[random.nextInt(TypeBike.values().length)]);
        customer.setNeededMinFrameSizeBike(random.nextInt(15) + 8);
        customer.setNeededMaxFrameSizeBike(customer.getNeededMinFrameSizeBike() + 5);
        customer.setNeededAccessory(TypeAccessory.values()[random.nextInt(TypeAccessory.values().length)]);
        customer.setNeededComponent(TypeComponent.values()[random.nextInt(TypeComponent.values().length)]);
        return customer;
    }
}
