package com.shop.model.generators;

import com.shop.model.Customer;
import com.shop.model.customer.CustomerName;
import com.shop.model.good.accessory.TypeAccessory;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.TypeComponent;

import java.util.Random;

public class CustomerGenerator {


    public static Customer generate() {
        Random random = new Random();
        Customer customer = new Customer();
        customer.setBalance(random.nextInt(240) + 200);
        customer.setType(TypeBike.values()[random.nextInt(TypeBike.values().length)]);
        customer.setMin(random.nextInt(15) + 8);
        customer.setMax(customer.getMin() + 5);
        customer.setAccessory(TypeAccessory.values()[random.nextInt(TypeAccessory.values().length)]);
        customer.setComponent(TypeComponent.values()[random.nextInt(TypeComponent.values().length)]);
        customer.setName(CustomerName.values()[random.nextInt(CustomerName.values().length)].name);
        return customer;
    }
}

