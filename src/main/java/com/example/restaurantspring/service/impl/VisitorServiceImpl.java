package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Visitor;
import com.example.restaurant.repository.VisitorRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.VisitorService;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl
        extends AbstractService<Visitor, VisitorRepository>
        implements VisitorService {

    public VisitorServiceImpl(VisitorRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Посетитель с id = %d не найден";
    }
}
