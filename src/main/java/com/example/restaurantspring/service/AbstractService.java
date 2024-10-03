package com.example.restaurantspring.service;

import com.example.restaurant.entity.AbstractEntity;
import com.example.restaurant.repository.BaseRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity, R extends BaseRepository<T>> implements Service<T> {

    protected final R repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T get(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format(getEntityNotFoundExceptionFormatString(), id)));
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public T update(Long id, T t) {
        t.setId(id);
        return save(t);
    }

    @Override
    public void delete(Long id) {
        if (exists(id)) {
            repository.deleteById(id);
        }
    }

    public abstract String getEntityNotFoundExceptionFormatString();

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}
