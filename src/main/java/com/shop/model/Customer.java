package com.shop.model;

import com.shop.model.good.Good;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.accessory.TypeAccessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.TypeBike;
import com.shop.model.good.component.Component;
import com.shop.model.good.component.TypeComponent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    public String name;
    private double balance;
    @Transient
    private List<Good> сart = new ArrayList<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Good> purchased = new ArrayList<>();
//    Параметры для выбора продуктов
    private TypeBike type;
    private int min;
    private int max;
    private TypeAccessory accessory;
    private TypeComponent component;

    @Override
    public String toString() {
        return "Customer";
    }

    public void chooseBike(List<Bike> bikes) {
        for (Bike bike : bikes) {
            if ((bike.getBtype() == this.type) && (bike.getFrame() > this.min) && (bike.getFrame() < this.max)) {
                if (this.balance >= bike.getPrice()) {
                    addGoodToShoppingCart(bike);
                }
            }
        }
    }

    public void chooseAccessories(List<Accessory> accessories) {
        for (Accessory accessory : accessories) {
            if ((accessory.getAtype() == this.accessory)) {
                addGoodToShoppingCart(accessory);
            }
        }
    }

    public void chooseComponents(List<Component> components) {
        for (Component component : components) {
            if ((component.getCtype() == this.component)) {
                addGoodToShoppingCart(component);
            }
        }
    }

    private void addGoodToShoppingCart(Good goods) {
        сart.add(goods);
    }

    public List<Good> buySelectedGoods(List<Promotion> promotions) {
        List<Good> currentPurchased = new ArrayList<>();
        for (Good good : сart) {
            if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Bike.class) {
                good.setCustomer(this);
                currentPurchased.add(good);
                good.setStatus(GoodStatus.SOLD_OUT);
                balance -= good.getPrice();
                break; // покупаем велосипед только один раз
            }
        }

        if (!currentPurchased.isEmpty()) { /* Если покупатель не купил велосипед, то ему не нужны комплектующие и аксессуары*/
            for (Good good : сart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Component.class) {
                    good.setCustomer(this);
                    currentPurchased.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    break;
                }
            }

            for (Good good : сart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Accessory.class) {
                    good.setCustomer(this);
                    currentPurchased.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    break;
                }
            }
        }
        purchased.addAll(currentPurchased);
        return currentPurchased;
    }

    /*public double getAmountOfPurchase() {
        Double amount = 0.0;
        List<Bike> bikes = BikeDao.findAllSold();
        for (Good good : bikes) {
            if (good.getCustomer().getId() == this.getId()) {
                amount += good.getPrice();
            }
        }
        return amount;
    }*/
}
