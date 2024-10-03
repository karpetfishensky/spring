package com.example.restaurantspring.service;

import com.example.restaurant.entity.AbstractEntity;
import java.util.List;

public interface Service<T extends AbstractEntity> {

    List<T> getAll();
    T get(Long id);
    T save(T t);
    T update(Long id,T t);
    void delete(Long id);
    boolean exists(Long id);
}
