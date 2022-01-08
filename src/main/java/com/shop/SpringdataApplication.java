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
        List<Customer> customers = CustomerDao.findAll();
        List<Bike> bikes = BikeDao.findAll();
        for (Customer customer : customers) {
            customer.chooseBike(bikes);
        }
        for (Customer customer : customers) {
            customer.buySelectedGoods();
        }
        List<Bike> soldBikes = BikeDao.findByStatus(GoodStatus.SOLD_OUT);
    }

}
