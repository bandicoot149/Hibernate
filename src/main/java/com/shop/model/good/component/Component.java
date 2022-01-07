package com.shop.model.good.component;

import com.shop.model.good.Good;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("Component")
public class Component extends Good {

    private TypeComponent typeComponent;


    public Component (double price) {
        super(price);
    }

    @Override
    public String toString() {
        return "Component{" + "typeComponent=" + typeComponent + '}';
    }
}
