package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.embeddable.FullName;
import com.example.restaurant.entity.embeddable.Passport;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.exception.handler.ValidEnum;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChefRequest {

    @Valid
    @JsonUnwrapped
    private Passport passport;

    @Valid
    @JsonUnwrapped
    private FullName fullName;

    @NotNull(message = "Опыт не может быть null")
    @Min(value = 0, message = "Опыт должен быть не менее 0")
    private Integer experience;

    @Builder.Default
    @ValidEnum(enumClass = CuisineType.class, message = "Неверный тип кухни")
    private String cuisineType = CuisineType.OTHER.name();

    @NotNull(message = "Администратор не может быть null")
    private Long administratorId;

}
