package com.shop.model.good;

import com.shop.model.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Good {
    @Id
    @GeneratedValue
    private UUID id;
    private double price;
    private GoodStatus status = GoodStatus.IN_STOCK;
    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

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
