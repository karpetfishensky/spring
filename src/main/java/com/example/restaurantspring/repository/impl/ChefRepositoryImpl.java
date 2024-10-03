package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.repository.ChefRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ChefRepositoryImpl extends BaseRepositoryImpl<Chef> implements ChefRepository {

    public ChefRepositoryImpl() {
        super(Chef.class);
    }
}
