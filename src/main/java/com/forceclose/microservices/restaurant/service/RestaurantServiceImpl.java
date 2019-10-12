package com.forceclose.microservices.restaurant.service;

import com.forceclose.microservices.restaurant.repository.TableRepository;
import com.forceclose.microservices.restaurant.domain.Restaurant;
import com.forceclose.microservices.restaurant.domain.RestaurantTable;
import com.forceclose.microservices.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;

    @Override
    public Restaurant add(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant){
        restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public void delete(Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            restaurantRepository.delete(restaurant.get());
        }
    }

    @Override
    public Restaurant findById(Long restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()) return restaurant.get();
        return null;
    }

    @Override
    public List<Restaurant> findByName(String name){
        return restaurantRepository.findByName(name);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<RestaurantTable> findTablesByRestaurantId(Long restaurantId) {
        return tableRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public RestaurantTable addRestaurantTable(RestaurantTable restaurantTable) {
        return tableRepository.save(restaurantTable);
    }
}
