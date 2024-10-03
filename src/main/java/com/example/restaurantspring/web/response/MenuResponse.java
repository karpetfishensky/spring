package com.example.restaurantspring.web.response;

import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.entity.enumeration.DishType;
import lombok.Data;

@Data
public class MenuResponse {

    private Long id;
    private String name;
    private DishType dishType = DishType.SIGNATURE;
    private Integer price;
    private CuisineType cuisineType = CuisineType.OTHER;
    private Long restaurantId;
    private String restaurantName;
}
