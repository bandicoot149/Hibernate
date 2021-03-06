package com.shop.model.generators;

import com.shop.model.good.component.Component;
import com.shop.model.good.component.TypeComponent;

import java.util.Random;

public class ComponentGenerator {


    public static Component generate() {
        Random random = new Random();
        Component component = new Component(random.nextInt(100) + 15);
        component.setCtype(TypeComponent.values()[random.nextInt(TypeComponent.values().length)]);
        return component;
    }
}
