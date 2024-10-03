package com.example.restaurantspring.service;

import com.example.restaurant.entity.RestaurantTable;

import java.util.List;

public interface TableService extends Service<RestaurantTable> {

    List<RestaurantTable> getFreeTable(Long restaurantId);
    List<RestaurantTable> getFreeTable(Long restaurantId,Integer countSeat);
}
