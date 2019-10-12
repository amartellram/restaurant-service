package com.forceclose.microservices.restaurant.repository;

import com.forceclose.microservices.restaurant.domain.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByRestaurantId(Long restaurantId);
}
