package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Administrator;
import com.example.restaurant.entity.Chef;
import com.example.restaurant.service.AdministratorService;
import com.example.restaurant.service.ChefService;
import com.example.restaurant.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService {

    private final AdministratorService administratorService;
    private final ChefService chefService;

    @Value("${employment.min-experience}")
    private Integer minExperience;

    @Override
    public Chef employChef(Chef chef, Long administratorId) {
        Administrator administrator = administratorService.get(administratorId);
        if (chef.getExperience() <= minExperience) {
            return null;
        }
        chef.setAdministrator(administrator);
        chef.setRestaurant(administrator.getRestaurant());
        return chefService.save(chef);
    }
}
