package com.example.restaurantspring.web.response;

import lombok.Data;

@Data
public class VisitorResponse {

    private Long id;
    private String phoneNumber;
    private String reservationName;
}
