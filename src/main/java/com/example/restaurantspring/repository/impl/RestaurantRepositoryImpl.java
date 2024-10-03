package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl extends BaseRepositoryImpl<Restaurant> implements RestaurantRepository {

    public RestaurantRepositoryImpl() {
        super(Restaurant.class);
    }
}
