package com.forceclose.microservices.restaurant.config;

import com.forceclose.microservices.restaurant.domain.Restaurant;
import com.forceclose.microservices.restaurant.domain.RestaurantTable;
import com.forceclose.microservices.restaurant.repository.RestaurantRepository;
import com.forceclose.microservices.restaurant.repository.TableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InitialLoadDataConfig implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public void run(String... args) {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Pepe Restaurant");
        restaurant1 = restaurantRepository.save(restaurant1);
        log.info("Restaurant 1 : {}", restaurant1);

        RestaurantTable restaurantTable1 = new RestaurantTable();
        restaurantTable1.setName("Mesa 1");
        restaurantTable1.setCapacity(5);
        restaurantTable1.setRestaurant(restaurant1);
        tableRepository.save(restaurantTable1);

        RestaurantTable restaurantTable2 = new RestaurantTable();
        restaurantTable2.setName("Mesa 2");
        restaurantTable2.setCapacity(10);
        restaurantTable2.setRestaurant(restaurant1);
        tableRepository.save(restaurantTable2);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Paco Restaurant");
        restaurant2 = restaurantRepository.save(restaurant2);
        log.info("Restaurant 2 : {}", restaurant2);

        RestaurantTable restaurantTable3 = new RestaurantTable();
        restaurantTable3.setName("Mesa 1");
        restaurantTable3.setCapacity(5);
        restaurantTable3.setRestaurant(restaurant2);
        tableRepository.save(restaurantTable3);

        RestaurantTable restaurantTable4 = new RestaurantTable();
        restaurantTable4.setName("Mesa 2");
        restaurantTable4.setCapacity(10);
        restaurantTable4.setRestaurant(restaurant2);
        tableRepository.save(restaurantTable4);

    }
}
