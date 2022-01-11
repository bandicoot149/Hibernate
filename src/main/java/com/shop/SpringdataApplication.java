package com.shop;

import com.shop.model.Customer;
import com.shop.model.Shop;
import com.shop.model.generators.AccessoryGenerator;
import com.shop.model.generators.CustomerGenerator;
import com.shop.model.good.Good;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringdataApplication  implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringdataApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private GoodRepository goodRepository;

    @Override
    public void run(String... args) throws Exception {
        Shop shop = new Shop(100, promotionRepository.findAll(),
                goodRepository.findAllBikes(),
                goodRepository.findAllAccessories(),
                goodRepository.findAllComponents());
        List<Good> soldGood = shop.simulateWork(customerRepository.findAll());
        goodRepository.saveAll(soldGood);
        HashMap<Customer, Double> buyersList = shop.getTopBuyersList(soldGood); //goodRepository.findAllByStatus(GoodStatus.SOLD_OUT)

    }



}
