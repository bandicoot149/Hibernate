package com.shop.repositories;

import com.shop.model.good.Good;
import com.shop.model.good.bike.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoodRepository extends JpaRepository<Good, UUID> {
    /*List<Good> findByType(String type);*/
}
