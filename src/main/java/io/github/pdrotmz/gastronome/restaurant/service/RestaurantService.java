package io.github.pdrotmz.gastronome.restaurant.service;

import io.github.pdrotmz.gastronome.restaurant.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> findAllRestaurant();
    Optional<Restaurant> findRestaurantById(String id);
    Optional<Restaurant> findRestaurantByName(String name);
    Optional<Restaurant> updateRestaurantById(String id, Restaurant restaurant);
    Optional<Restaurant> updateRestaurantByName(String name, Restaurant restaurant);
    void deleteRestaurantById(String id);
}
