package com.shop;

import com.shop.model.Customer;
import com.shop.model.Promotion;
import com.shop.model.generators.AccessoryGenerator;
import com.shop.model.generators.PromotionGenerator;
import com.shop.model.good.Good;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import com.shop.model.good.component.TypeComponent;
import com.shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringdataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataApplication.class, args);
    }

    @Autowired
    GoodRepository goodRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BikeRepository bikeRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    AccessoryRepository accessoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Bike bike = new Bike(10, Brand.CUBE, TypeBike.BMX, 10);
        Promotion promotion = PromotionGenerator.generate();
        Accessory accessory = AccessoryGenerator.generate();
        Component component = new Component(2, TypeComponent.FORK);
        Customer customer = new Customer("Михаил", 100);
        goodRepository.saveAll(List.of(component, bike));
        customer.setPurchasedGoods(List.of(component, bike));
        customerRepository.save(customer);
        promotionRepository.save(promotion);
        accessoryRepository.save(accessory);
        Component foundComponent = componentRepository.findAllById(component.getId());
        Bike foundBike = bikeRepository.findAllById(bike.getId());
        Accessory foundAccessory = accessoryRepository.findAllById(accessory.getId());
        Promotion foundPromotion = promotionRepository.findAllById(promotion.getId());
        Customer foundCustomer = customerRepository.findAllById(customer.getId());
        System.out.println("Hello");
    }
}
