package com.shop.model;

import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Data
@SuperBuilder
@Entity
@Table
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue
    private UUID id;
    private Brand brand;
    private TypeBike type;
    private int percent;


    @Override
    public String toString() {
        return "Promotion{" +
                "brand=" + brand +
                ", typeBike='" + type + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }

    public boolean checkCompliance(Bike bike)
    {
        return bike.getBrand() == brand && bike.getBtype() == type;
    }
}
