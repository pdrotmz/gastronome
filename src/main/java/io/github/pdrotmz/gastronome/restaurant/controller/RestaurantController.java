package io.github.pdrotmz.gastronome.restaurant.controller;

import io.github.pdrotmz.gastronome.restaurant.model.Restaurant;
import io.github.pdrotmz.gastronome.restaurant.service.RestaurantServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/register")
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAllRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/find-by/id/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable String id) {
        Optional<Restaurant> restaurantId = restaurantService.findRestaurantById(id);
        return restaurantId
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-by/name/{name}")
    public ResponseEntity<Restaurant> findRestaurantByName(@PathVariable String name) {
        Optional<Restaurant> restaurantName = restaurantService.findRestaurantByName(name);
        return restaurantName
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable String id, @Valid @RequestBody Restaurant restaurant) {
        Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurantById(id, restaurant);
        return updatedRestaurant
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/name/{name}")
    public ResponseEntity<Restaurant> updateRestaurantByName(@PathVariable String name, @Valid @RequestBody Restaurant restaurant) {
        Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurantByName(name, restaurant);
        return updatedRestaurant
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable String id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}
