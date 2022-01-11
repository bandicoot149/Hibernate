package com.shop;

import com.shop.model.Customer;
import com.shop.model.Promotion;
import com.shop.model.generators.*;
import com.shop.model.good.Good;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;
import com.shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DataGeneratorApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(DataGeneratorApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private GoodRepository goodRepository;

    @Override
    public void run(String... args) throws Exception {
        int bikesCount = 60;
        int accessoryCount = 20;
        int componentsCount = 20;
        int promotionCount = 2;
        int customerCount = 10;

        List<Good> goods = new ArrayList<>();
        List<Promotion> promotions = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();


        for (int i = 0; i < bikesCount; i++) {
            goods.add(BikeGenerator.generate());
        }
        for (int i = 0; i < accessoryCount; i++) {
            goods.add(AccessoryGenerator.generate());
        }
        for (int i = 0; i < componentsCount; i++) {
            goods.add(ComponentGenerator.generate());
        }
        for (int i = 0; i < promotionCount; i++) {
            promotions.add(PromotionGenerator.generate());
        }
        for (int i = 0; i < customerCount; i++) {
            customers.add(CustomerGenerator.generate());
        }

        goodRepository.saveAll(goods);
        promotionRepository.saveAll(promotions);
        customerRepository.saveAll(customers);

        /*Dao.writeInDbAll(bikes);
        Dao.writeInDbAll(customers);
        Dao.writeInDbAll(promotions);*/
    }
}
