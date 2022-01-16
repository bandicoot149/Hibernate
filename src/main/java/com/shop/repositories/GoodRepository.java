package com.shop.repositories;

import com.shop.model.good.Good;
import com.shop.model.good.GoodStatus;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoodRepository extends JpaRepository<Good, UUID> {
    @Query("from Bike")
    public List<Bike> findAllBikes();
    @Query("select g from Bike g where g.status = :status")
    public List<Bike> findAllBikesByStatus(GoodStatus status);
    @Query("select g from Component g where g.status = :status")
    public List<Component> findAllComponentsByStatus(GoodStatus status);
    @Query("select g from Accessory g where g.status = :status")
    public List<Accessory> findAllAccessoriesByStatus(GoodStatus status);
    @Query("from Component")
    public List<Component> findAllComponents();
    @Query("select c.ctype, COUNT (c) from Component c where c.status = :status group by c.ctype")
    public List<Object[]> findAllComponentsGroupByBrand(GoodStatus status);
    @Query("from Accessory")
    public List<Accessory> findAllAccessories();
    @Query("select g from Bike g where g.customer.id = :id")
    public List<Bike> findAllBikesByCustomerId(UUID id);
    @Query("select g from Component g where g.customer.id = :id")
    public List<Component> findAllComponentsByCustomerId(UUID id);
    @Query("select g from Accessory g where g.customer.id = :id")
    public List<Accessory> findAllAccessoriesByCustomerId(UUID id);

    public List<Good>findAllByStatus(GoodStatus status);
}
