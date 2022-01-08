package com.shop.model;

import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
public class Shop {


    private double balance = 0;
    private Set<Promotion> promotions;
    private Set<Bike> bikes;
    private Set<Accessory> accessories;
    private Set<Component> components;

    public Shop(double balance, Set<Promotion> promotions, Set<Bike> bikes, Set<Accessory> accessories, Set<Component> components) {
        this.balance = balance;
        this.promotions = promotions;
        this.bikes = bikes;
        this.accessories = accessories;
        this.components = components;
        activatePromotions();
    }

    /* Допущение - магазин единажды применяет этот метод */
    private void activatePromotions() {
        for (Bike bike : bikes) {
            for (Promotion promotion : promotions) {
                if(promotion.checkCompliance(bike)) {
                    bike.setPrice(bike.getPrice()*promotion.getPercent()/100);
                }
            }
        }
    }

    public void makeStatistics () {
        Map<TypeBike, Integer> bikeSales = new HashMap<TypeBike, Integer>();
        for (TypeBike type : TypeBike.values()) {
            bikeSales.put(type, 0);
        }
        for (Bike bike : bikes) {
            if (bike.getStatus() == GoodStatus.SOLD_OUT) {
                bikeSales.put(bike.getTypeBike(), bikeSales.get(bike.getTypeBike()) + 1);
            }
        }
        for (TypeBike type : TypeBike.values()) {
            System.out.println(type + " " + bikeSales.get(type));
        }
    }

    @Override
    public String toString() {
        return "Shop";
    }
}
