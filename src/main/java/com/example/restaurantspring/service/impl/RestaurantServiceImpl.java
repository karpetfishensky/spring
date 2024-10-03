package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl
        extends AbstractService<Restaurant, RestaurantRepository>
        implements RestaurantService {

    public RestaurantServiceImpl(RestaurantRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Ресторан с id = %d не найден";
    }
}
