package io.github.pdrotmz.gastronome.restaurant.repository;

import io.github.pdrotmz.gastronome.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    @Query(value = "SELECT * FROM tb_restaurant r WHERE r.name = ?1", nativeQuery = true)
    Optional<Restaurant> findRestaurantByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_restaurant r WHERE r.id = :id", nativeQuery = true)
    void deleteRestaurantById(String id);
}
