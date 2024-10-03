package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.exception.handler.ValidEnum;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboRequest {

    @ValidEnum(enumClass = CuisineType.class, message = "Неверный тип кухни")
    private String cuisineType = CuisineType.OTHER.name();

    @Min(value = 1, message = "Цена должна быть больше нуля")
    private Integer price;
}
