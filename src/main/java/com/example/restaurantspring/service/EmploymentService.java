package com.example.restaurantspring.service;

import com.example.restaurant.entity.Chef;

public interface EmploymentService {

    Chef employChef(Chef chef, Long administratorId);
}
