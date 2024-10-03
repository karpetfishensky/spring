package com.example.restaurantspring.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private String message;
    private VisitorResponse visitor;
    private RestaurantTableResponse table;
}
