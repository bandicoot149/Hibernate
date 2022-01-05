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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    public String name;
    private double balance;
    @OneToMany()
    private List<Good> shoppingCart = new ArrayList<>();
    @OneToMany()
    private List<Good> purchasedGoods = new ArrayList<>();
    private TypeBike neededTypeBike;
    private int neededMinFrameSizeBike;
    private int neededMaxFrameSizeBike;
    private TypeAccessory neededAccessory;
    private TypeComponent neededComponent;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", balance='" + balance + '\'' +
                /*", neededTypeBike='" + neededTypeBike + '\'' +
                ", neededMinFrameSizeBike='" + neededMinFrameSizeBike + '\'' +
                ", neededMaxFrameSizeBike='" + neededMaxFrameSizeBike + '\'' +
                ", neededAccessory='" + neededAccessory + '\'' +
                ", neededComponent='" + neededComponent + '\'' +
                ", shoppingCart='" + shoppingCart.size() + '\'' + // TODO: продумать вывод корзины и покупок?
                ", purchasedGoods='" + purchasedGoods.size() + '\'' +*/

                '}';
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
                purchasedGoods.add(good);
                good.setStatus(GoodStatus.SOLD_OUT);
                balance -= good.getPrice();
                System.out.println(name + " купил велосипед " + ((Bike) good).getBrand());
                break; // покупаем велосипед только один раз
            }
        }

        if (!purchasedGoods.isEmpty()) { /* Если покупатель не купил велосипед, то ему не нужны комплектующие и аксессуары*/
            for (Good good : shoppingCart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Component.class) {
                    purchasedGoods.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    System.out.println(name + " купил компонент " + ((Component) good).getTypeComponent());
                    break;
                }
            }

            for (Good good : shoppingCart) {
                if (balance > good.getPrice() && good.getStatus() == GoodStatus.IN_STOCK && good.getClass() == Accessory.class) {
                    purchasedGoods.add(good);
                    good.setStatus(GoodStatus.SOLD_OUT);
                    balance -= good.getPrice();
                    System.out.println(name + " купил аксессуар " + ((Accessory) good).getTypeAccessory());
                    break;
                }
            }
        }
    }
}
