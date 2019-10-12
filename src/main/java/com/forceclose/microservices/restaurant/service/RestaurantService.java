package com.forceclose.microservices.restaurant.service;

import com.forceclose.microservices.restaurant.domain.Restaurant;
import com.forceclose.microservices.restaurant.domain.RestaurantTable;

import java.util.List;

public interface RestaurantService {

    Restaurant add(Restaurant restaurant);
    void update(Restaurant restaurant);
    void delete(Long id);
    Restaurant findById(Long restaurantId);
    List<Restaurant> findByName(String name);
    List<Restaurant> findAll();
    List<RestaurantTable> findTablesByRestaurantId(Long restaurantId);
    RestaurantTable addRestaurantTable(RestaurantTable restaurantTable);

}
