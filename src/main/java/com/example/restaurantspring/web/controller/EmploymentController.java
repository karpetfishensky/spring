package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.mapper.ChefMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.EmploymentService;
import com.example.restaurant.web.request.ChefRequest;
import com.example.restaurant.web.response.ChefResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employment/chefs")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentService employmentService;
    private final ChefMapper chefMapper;

    @PostMapping
    public ResponseEntity<?> employChef(@RequestBody ChefRequest request) {
        Chef chef = chefMapper.toEntity(request);
        chef = employmentService.employChef(chef, request.getAdministratorId());
        if (chef == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Шеф-повар имеет недостаточно опыта"));
        }
        return ResponseEntity.ok(chefMapper.toResponse(chef));
    }
}
