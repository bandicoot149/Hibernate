package com.shop.model;

import com.shop.model.good.bike.Bike;
import com.shop.model.good.bike.Brand;
import com.shop.model.good.bike.TypeBike;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
public class Promotion {
    @Id
    private UUID id;
    private Brand brand;
    private TypeBike typeBike;
    private int percent;

    public Promotion () {
        this.id = UUID.randomUUID();
    }

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
