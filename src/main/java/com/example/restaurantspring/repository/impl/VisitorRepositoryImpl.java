package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.Visitor;
import com.example.restaurant.repository.VisitorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VisitorRepositoryImpl extends BaseRepositoryImpl<Visitor> implements VisitorRepository {

    public VisitorRepositoryImpl() {
        super(Visitor.class);
    }
}
