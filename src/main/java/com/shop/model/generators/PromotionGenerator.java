package com.shop.model.generators;

import com.shop.model.Promotion;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;

import java.util.Random;

public class PromotionGenerator {
    public static Promotion generate() {
        Random random = new Random();
        Promotion promotion = new Promotion();
        promotion.setBrand(Brand.values()[random.nextInt(Brand.values().length)]);
        promotion.setTypeBike(TypeBike.values()[random.nextInt(TypeBike.values().length)]);
        promotion.setPercent(random.nextInt(25) + 5);
        return promotion;
    }
}
