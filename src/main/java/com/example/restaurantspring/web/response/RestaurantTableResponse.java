package com.example.restaurantspring.web.response;

import lombok.Data;

@Data
public class RestaurantTableResponse {

    private Long id;
    private Integer tableNumber;
    private Integer seatCount;
    private Integer reservationSeat;
    private Boolean reservation;
    private Long restaurantId;
    private String restaurantName;
}
