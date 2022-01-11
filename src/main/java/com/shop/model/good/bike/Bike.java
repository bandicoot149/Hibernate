package com.shop.model.good.bike;

import com.shop.model.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shop.model.good.Good;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("Bike")
public class Bike extends Good {

    private Brand brand;
    private TypeBike btype;
    private int frame;


    public Bike (double price) {
        super(price);
    }

    public Bike checkPromotions(List<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            if (promotion.checkCompliance(this)) {
                this.setPrice(this.getPrice() * (100 - promotion.getPercent()));
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "brand=" + brand +
                ", typeBike='" + btype + '\'' +
                ", frameSize='" + frame + '\'' +
                '}';
    }
}


