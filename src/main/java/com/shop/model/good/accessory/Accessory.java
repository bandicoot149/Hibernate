package com.shop.model.good.accessory;

import com.shop.model.good.Good;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("Accessory")
public class Accessory extends Good {

    private TypeAccessory typeAccessory;

    public Accessory (double price) {
        super(price);
    }


    @Override
    public String toString() {
        return "Accessory{" + "typeAccessory=" + typeAccessory + '}';
    }
}
