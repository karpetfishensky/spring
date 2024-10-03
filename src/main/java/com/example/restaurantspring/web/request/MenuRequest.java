package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.entity.enumeration.DishType;
import com.example.restaurant.exception.handler.ValidEnum;
import jakarta.validation.constraints.Min;
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
public class MenuRequest {

    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @ValidEnum(enumClass = DishType.class, message = "Неверный тип блюда")
    private String dishType = DishType.SIGNATURE.name();

    @NotNull(message = "Цена не может быть null")
    @Min(value = 1, message = "Цена должна быть больше нуля")
    private Integer price;

    @ValidEnum(enumClass = CuisineType.class, message = "Неверный тип кухни")
    private String cuisineType = CuisineType.OTHER.name();

    @NotNull(message = "ID ресторана не может быть null")
    private Long restaurantId;
}
