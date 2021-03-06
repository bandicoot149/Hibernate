package com.shop.repositories;

import com.shop.model.good.Good;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GoodRepository extends JpaRepository<Good, UUID> {
    @Query("from Bike")
    public List<Bike> findAllBikes();
    @Query("select b from Bike b where b.status = :status")
    public List<Bike> findAllBikesByStatus(GoodStatus status);
    @Query("from Component")
    public List<Component> findAllComponents();
    @Query("select c.ctype, COUNT (c) from Component c where c.status = :status group by c.ctype")
    public List<Object[]> findAllComponentsGroupByBrand(GoodStatus status);
    @Query("from Accessory")
    public List<Accessory> findAllAccessories();

    public List<Good>findAllByStatus(GoodStatus status);
}
