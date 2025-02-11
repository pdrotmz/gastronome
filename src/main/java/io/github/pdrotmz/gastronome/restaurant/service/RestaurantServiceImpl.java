package io.github.pdrotmz.gastronome.restaurant.service;

import io.github.pdrotmz.gastronome.restaurant.model.Restaurant;
import io.github.pdrotmz.gastronome.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findRestaurantById(String id) {
        if(restaurantRepository.findById(id) == null ||
                restaurantRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Restaurant not found!");
        }
        return restaurantRepository.findById(id);
    }

    @Override
    public Optional<Restaurant> findRestaurantByName(String name) {
        if(restaurantRepository.findRestaurantByName(name) == null ||
                restaurantRepository.findRestaurantByName(name).isEmpty()) {
            throw new EntityNotFoundException("Restaurant not found!");
        }
        return restaurantRepository.findRestaurantByName(name);
    }

    @Override
    public Optional<Restaurant> updateRestaurantById(String id, Restaurant restaurant) {
        return restaurantRepository.findById(id).map(existingRestaurant -> {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setAddress(restaurant.getAddress());

            return restaurantRepository.save(existingRestaurant);
        })
                .map(Optional::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Optional<Restaurant> updateRestaurantByName(String name, Restaurant restaurant) {
        return restaurantRepository.findRestaurantByName(name).map(existingRestaurant -> {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setAddress(restaurant.getAddress());

            return restaurantRepository.save(existingRestaurant);
        })
                .map(Optional::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteRestaurantById(String id) {
        restaurantRepository.deleteRestaurantById(id);
    }
}
