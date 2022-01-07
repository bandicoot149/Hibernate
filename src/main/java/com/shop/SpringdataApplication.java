package com.shop;

import com.shop.dao.*;
import com.shop.model.Shop;
import com.shop.model.generators.ShopGenerator;

public class SpringdataApplication {


    public static void main(String[] args) {
        ShopGenerator shopGenerator = new ShopGenerator();
        BikeDao bikeDao = new BikeDao();
        ComponentDao componentDao = new ComponentDao();
        AccessoryDao accessoryDao = new AccessoryDao();
        PromotionDao promotionDao = new PromotionDao();
        ShopDao shopDao = new ShopDao();
        Shop shop = shopGenerator.generate(5,5,5,2);
        componentDao.writeInDB(shop.getComponents());
        bikeDao.writeInDB(shop.getBikes());
        accessoryDao.writeInDB(shop.getAccessories());
        promotionDao.writeInDB(shop.getPromotions());
        shopDao.writeInDB(shop);
        Shop foundShop = shopDao.readFromDB();
    }

}
