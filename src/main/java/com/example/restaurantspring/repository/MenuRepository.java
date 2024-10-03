package com.example.restaurantspring.repository;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseRepository<Menu>{

    List<Menu> findAllByCuisineType(CuisineType cuisineType);
}
