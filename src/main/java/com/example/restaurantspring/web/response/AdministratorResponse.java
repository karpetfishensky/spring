package com.example.restaurantspring.web.response;

import com.example.restaurant.entity.embeddable.FullName;
import com.example.restaurant.entity.embeddable.Passport;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

@Data
public class AdministratorResponse {

    private Long id;

    private String phoneNumber;

    @JsonUnwrapped
    private FullName fullName;

    @JsonUnwrapped
    private Passport passport;

    private Long restaurantId;

    private String restaurantName;
}
