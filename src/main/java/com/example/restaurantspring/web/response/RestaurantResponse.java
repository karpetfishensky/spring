package com.example.restaurantspring.web.response;

import com.example.restaurant.entity.embeddable.Address;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

@Data
public class RestaurantResponse {

    private Long id;

    private String name;

    @JsonUnwrapped
    private Address address;

    private String phoneNumber;
}
