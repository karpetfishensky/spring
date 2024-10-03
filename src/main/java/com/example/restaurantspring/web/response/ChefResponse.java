package com.example.restaurantspring.web.response;

import com.example.restaurant.entity.embeddable.FullName;
import com.example.restaurant.entity.embeddable.Passport;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

@Data
public class ChefResponse {

    private Long id;

    @JsonUnwrapped
    private Passport passport;

    @JsonUnwrapped
    private FullName fullName;

    private Integer experience;

    private String cuisineType;

    private Long restaurantId;

    private String restaurantName;

    private Long administratorId;

    @JsonUnwrapped
    private FullName administratorName;
}
