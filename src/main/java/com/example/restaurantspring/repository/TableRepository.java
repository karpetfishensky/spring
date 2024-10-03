package com.example.restaurantspring.repository;

import com.example.restaurant.entity.RestaurantTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends BaseRepository<RestaurantTable> {

    List<RestaurantTable> findAllByRestaurantId(Long id);
}
