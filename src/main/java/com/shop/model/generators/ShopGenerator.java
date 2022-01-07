package com.shop.model.generators;

import com.shop.model.Promotion;
import com.shop.model.Shop;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShopGenerator {

    public Shop generate(int bikesCount, int accessoryCount, int componentsCount, int promotionCount) {
        BikeGenerator bikeGenerator = new BikeGenerator();
        AccessoryGenerator accessoryGenerator = new AccessoryGenerator();
        ComponentGenerator componentGenerator = new ComponentGenerator();
        PromotionGenerator promotionGenerator = new PromotionGenerator();
        Set<Bike> bikes = new HashSet<>();
        Set<Accessory> accessories = new HashSet<>();
        Set<Component> components = new HashSet<>();
        Set<Promotion> promotions = new HashSet<>();
        for (int i = 0; i < bikesCount; i++) {
            bikes.add(bikeGenerator.generate());
        }
        for (int i = 0; i < accessoryCount; i++) {
            accessories.add(accessoryGenerator.generate());
        }
        for (int i = 0; i < componentsCount; i++) {
            components.add(componentGenerator.generate());
        }
        for (int i = 0; i < promotionCount; i++) {
            promotions.add(promotionGenerator.generate());
        }
        return new Shop(0, promotions, bikes, accessories, components);
    }
}
