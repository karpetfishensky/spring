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
public class BookingRequest {

    @NotNull(message = "ID столика не может быть null")
    private Long tableId;

    @NotNull(message = "ID посетителя не может быть null")
    private Long visitorId;

    @NotNull(message = "Количество мест не может быть null")
    @Min(value = 1, message = "Количество мест должно быть не менее 1")
    private Integer countSeat;
}
