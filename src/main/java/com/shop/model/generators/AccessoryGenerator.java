package com.shop.model.generators;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.accessory.TypeAccessory;

import java.util.Random;

public class AccessoryGenerator {
    public static Accessory generate() {
        Random random = new Random();
        Accessory accessory = new Accessory();
        accessory.setTypeAccessory(TypeAccessory.values()[random.nextInt(TypeAccessory.values().length)]);
        accessory.setPrice(random.nextInt(50) + 10);
        return accessory;
    }
}
