package com.example.restaurantspring.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.web.response.MenuResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuService extends Service<Menu> {

    Menu updateMenuByChefId(Long id, Menu menu, Long chefId);

    List<Menu> getCombo(CuisineType cuisineType, Integer sum, boolean children);
}
