package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.repository.ChefRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.ChefService;
import org.springframework.stereotype.Service;

@Service
public class ChefServiceImpl
        extends AbstractService<Chef, ChefRepository>
        implements ChefService {

    public ChefServiceImpl(ChefRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Шеф с id = %d не найден";
    }
}
