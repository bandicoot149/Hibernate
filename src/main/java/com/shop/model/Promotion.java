package com.shop.model;

import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Promotion")
public class Promotion {
    @Id
    @GeneratedValue
    private UUID id;
    private Brand brand;
    private TypeBike typeBike;
    private int percent;

    @Override
    public String toString() {
        return "Promotion{" +
                "brand=" + brand +
                ", typeBike='" + typeBike + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }

    public boolean checkCompliance(Bike bike)
    {
        return bike.getBrand() == brand && bike.getTypeBike() == typeBike;
    }
}
