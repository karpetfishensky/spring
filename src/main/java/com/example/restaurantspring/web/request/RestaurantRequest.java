package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.OwnerPhoneNumber;
import com.example.restaurant.entity.embeddable.Address;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest implements OwnerPhoneNumber {

    @NotBlank(message = "Имя ресторана не может быть пустым")
    @NotNull(message = "Имя ресторана не может быть null")
    @Size(max = 25, message = "Размер имени не должен превышать 25 символов")
    private String name;

    @Builder.ObtainVia(method = "getPhoneNumberFrom")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @Valid
    @JsonUnwrapped
    private Address address;
}
