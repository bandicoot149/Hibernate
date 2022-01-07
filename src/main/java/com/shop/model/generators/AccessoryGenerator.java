package com.shop.model.generators;

import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.accessory.TypeAccessory;

import java.util.Random;


public class AccessoryGenerator {

    public Accessory generate() {
        Random random = new Random();
        Accessory accessory = new Accessory(random.nextInt(50) + 10);
        accessory.setTypeAccessory(TypeAccessory.values()[random.nextInt(TypeAccessory.values().length)]);
        return accessory;
    }
}
