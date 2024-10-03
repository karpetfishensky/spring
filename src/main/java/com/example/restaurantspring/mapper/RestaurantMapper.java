package com.example.restaurantspring.mapper;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.web.request.RestaurantRequest;
import com.example.restaurant.web.response.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    Restaurant toEntity(RestaurantRequest request);

    RestaurantResponse toResponse(Restaurant restaurant);
}

