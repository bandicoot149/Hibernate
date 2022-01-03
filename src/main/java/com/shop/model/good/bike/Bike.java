package com.shop.model.good.bike;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.shop.model.good.Good;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("Bike")
public class Bike extends Good {

    private Brand brand;
    private TypeBike typeBike;
    private int frameSize;

    public Bike (double price, Brand brand, TypeBike typeBike, int frameSize) {
        super(price);
        this.brand = brand;
        this.typeBike = typeBike;
        this.frameSize = frameSize;
    }


    @Override
    public String toString() {
        return "Bike{" +
                "brand=" + brand +
                ", typeBike='" + typeBike + '\'' +
                ", frameSize='" + frameSize + '\'' +
                '}';
    }
}


