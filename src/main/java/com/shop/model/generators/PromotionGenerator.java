package com.shop.model.generators;

import com.shop.model.Promotion;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;

import java.util.Random;

public class PromotionGenerator {


    public static Promotion generate() {
        Random random = new Random();
        return Promotion.builder()
                .brand(Brand.values()[random.nextInt(Brand.values().length)])
                .type(TypeBike.values()[random.nextInt(TypeBike.values().length)])
                .percent(random.nextInt(25) + 5)
                .build();
    }
}
