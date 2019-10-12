package com.forceclose.microservices.restaurant.controller;

import com.forceclose.microservices.restaurant.service.RestaurantService;
import com.forceclose.microservices.restaurant.domain.Restaurant;
import com.forceclose.microservices.restaurant.domain.RestaurantTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findByName(@RequestParam(value = "name", required = false) String name)
            throws Exception {
        Collection<Restaurant> restaurants = null;
        if(StringUtils.isEmpty(name)) {
            restaurants = restaurantService.findAll();
        } else{
            restaurants = restaurantService.findByName(name);
        }

        return restaurants != null && restaurants.size() > 0 ? new ResponseEntity(restaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable("id") Long id) throws Exception {
        log.info(String.format("restaurant-service findById() invoked:{} for {} ",
                restaurantService.getClass().getName(), id));
        Restaurant restaurant = restaurantService.findById(id);

        return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) throws Exception {
        restaurant = restaurantService.add(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tables")
    public ResponseEntity<List<RestaurantTable>> findTablesByRestaurant(@PathVariable(value = "id") Long restaurantId) {
        Collection<RestaurantTable> restaurantTables = restaurantService.findTablesByRestaurantId(restaurantId);
        return restaurantTables != null && restaurantTables.size() > 0 ? new ResponseEntity(restaurantTables, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/tables")
    public ResponseEntity<RestaurantTable> addRestaurantTable(@PathVariable(value = "id") Long restaurantId, @Valid @RequestBody RestaurantTable restaurantTable) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        if (restaurant == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        restaurantTable.setRestaurant(restaurant);
        restaurantTable = restaurantService.addRestaurantTable(restaurantTable);
        return new ResponseEntity<>(restaurantTable, HttpStatus.CREATED);
    }
}
