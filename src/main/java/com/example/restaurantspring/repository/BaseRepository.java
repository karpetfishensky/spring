package com.example.restaurantspring.repository;

import com.example.restaurant.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends AbstractEntity> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T entity);

    T update(Long id, T entity);

    void delete(T entity);

    boolean existsById(Long id);

    void deleteById(Long id);
}
