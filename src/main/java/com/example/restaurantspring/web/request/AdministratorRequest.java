package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.entity.OwnerPhoneNumber;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.entity.embeddable.FullName;
import com.example.restaurant.entity.embeddable.Passport;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorRequest implements OwnerPhoneNumber {

    @Builder.ObtainVia(method = "getPhoneNumberFrom")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @Valid
    @JsonUnwrapped
    private FullName fullName;

    @Valid
    @JsonUnwrapped
    private Passport passport;

    @NotNull(message = "ID ресторана не может быть null")
    private Long restaurantId;

}
