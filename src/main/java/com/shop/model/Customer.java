package com.shop.model;

import com.shop.dao.AccessoryDao;
import com.shop.dao.BikeDao;
import com.shop.dao.CustomerDao;
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
    private List<Good> shoppingCart = new ArrayList<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Good> purchasedGoods = new ArrayList<>();
    private TypeBike neededTypeBike;
    private int neededMinFrameSizeBike;
    private int neededMaxFrameSizeBike;
    private TypeAccessory neededAccessory;
    private TypeComponent neededComponent;

    @Override
    public String toString() {
        return "Customer";
    }

    public void chooseBike(List<Bike> bikes) {
        for (Bike bike : bikes) {
            if ((bike.getTypeBike() == this.neededTypeBike) && (bike.getFrameSize() > this.neededMinFrameSizeBike) && (bike.getFrameSize() < this.neededMaxFrameSizeBike)) {
                if (this.balance >= bike.getPrice()) {
                    addGoodToShoppingCart(bike);
                }
            }
        }
    }

    public void chooseAccessories(List<Accessory> accessories) {
        for (Accessory accessory : accessories) {
            if ((accessory.getTypeAccessory() == neededAccessory)) {
                addGoodToShoppingCart(accessory);
            }
        }
    }

    public void chooseComponents(List<Component> components) {
        for (Component component : components) {
            if ((component.getTypeComponent() == neededComponent)) {
                addGoodToShoppingCart(component);
            }
        }
    }

    private void addGoodToShoppingCart(Good goods) {
        shoppingCart.add(goods);
    }

    public void buySelectedGoods() {
        for (Good good : shoppingCart) {
            if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Bike.class) {
                good.setCustomer(this);
                purchasedGoods.add(good);
                good.setStatus(GoodStatus.SOLD_OUT);
                balance -= good.getPrice();
                break; // покупаем велосипед только один раз
            }
        }

        if (!purchasedGoods.isEmpty()) { /* Если покупатель не купил велосипед, то ему не нужны комплектующие и аксессуары*/
            for (Good good : shoppingCart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Component.class) {
                    good.setCustomer(this);
                    purchasedGoods.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    break;
                }
            }

            for (Good good : shoppingCart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Accessory.class) {
                    good.setCustomer(this);
                    purchasedGoods.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    break;
                }
            }
            CustomerDao.update(this);
        }
    }

    public double getAmountOfPurchase() {
        Double amount = 0.0;
        List<Bike> bikes = BikeDao.findAllSold();
        for (Good good : bikes) {
            if (good.getCustomer().getId() == this.getId()) {
                amount += good.getPrice();
            }
        }
        return amount;
    }
}
