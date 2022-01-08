package com.shop;

import com.shop.dao.*;
import com.shop.model.Customer;
import com.shop.model.Shop;
import com.shop.model.generators.CustomerGenerator;
import com.shop.model.generators.ShopGenerator;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.bike.Bike;

import java.util.List;
import java.util.Set;

public class SpringdataApplication {


    public static void main(String[] args) {
        ShopGenerator shopGenerator = new ShopGenerator();
        Shop shop = shopGenerator.generate(30,5,5,2);
        Dao.writeInDbAll(shop.getComponents());
        Dao.writeInDbAll(shop.getBikes());
        Dao.writeInDbAll(shop.getAccessories());
        Dao.writeInDbAll(shop.getPromotions());
        Dao.writeInDbAll(Set.of(CustomerGenerator.generate(), CustomerGenerator.generate(), CustomerGenerator.generate(), CustomerGenerator.generate(), CustomerGenerator.generate()));
        Set<Customer> customers = CustomerDao.findAll();
        for (Customer customer : customers) {
            customer.chooseBike(shop.getBikes());
        }
        for (Customer customer : customers) {
            customer.buySelectedGoods();
        }
        List<Bike> bikes = BikeDao.findByStatus(GoodStatus.SOLD_OUT);
    }

}
