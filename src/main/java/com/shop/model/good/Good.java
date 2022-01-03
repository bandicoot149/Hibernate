package com.shop.model.good;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;


@Entity
@Table(name = "Good")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("CASE WHEN typeBike IS NOT NULL THEN 'Bike'" +
        "WHEN typeAccessory IS NOT NULL THEN 'Accessory'" +
        "WHEN typeComponent IS NOT NULL THEN 'Component' END")
public abstract class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double price;
    private GoodStatus status = GoodStatus.IN_STOCK;

    public Good(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
