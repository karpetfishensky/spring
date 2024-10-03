package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Administrator;
import com.example.restaurant.repository.AdministratorRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl
        extends AbstractService<Administrator, AdministratorRepository>
        implements AdministratorService {

    public AdministratorServiceImpl(AdministratorRepository repository) {
        super(repository);
    }


    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Администратор с id = %d не найден";
    }
}
