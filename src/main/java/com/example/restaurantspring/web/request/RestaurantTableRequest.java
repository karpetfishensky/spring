package com.example.restaurantspring.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableRequest {

    @NotNull(message = "Номер стола не может быть null")
    private Integer tableNumber;

    @Min(value = 1, message = "Количество мест должно быть больше 0")
    private Integer seatCount = 1;

    @NotNull(message = "ID ресторана не может быть null")
    private Long restaurantId;
}
