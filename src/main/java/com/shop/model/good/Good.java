package com.shop.model.good;

import com.shop.model.Customer;
import com.shop.model.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    private UUID id;
    private double price;
    private GoodStatus status = GoodStatus.IN_STOCK;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Good(double price) {
        this.id = UUID.randomUUID();
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
