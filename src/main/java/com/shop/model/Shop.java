package com.shop.model;

import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Shop {
    private double balance = 0;
    private List<Promotion> promotions;
    private List<Bike> bikes;
    private List<Accessory> accessories;
    private List<Component> components;

    public Shop(double balance, List<Promotion> promotions, List<Bike> bikes, List<Accessory> accessories, List<Component> components) {
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
}
