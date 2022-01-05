package com.shop.repositories;

import com.shop.model.good.Good;
import com.shop.model.good.bike.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<Bike, UUID>  {
    Bike findAllById(UUID id);
}
