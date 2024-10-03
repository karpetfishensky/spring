package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.repository.TableRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableRepositoryImpl extends BaseRepositoryImpl<RestaurantTable> implements TableRepository {

    public TableRepositoryImpl() {
        super(RestaurantTable.class);
    }

    @Override
    public List<RestaurantTable> findAllByRestaurantId(Long id) {
        String query = "FROM RestaurantTable t WHERE t.restaurant.id = :restaurantId"; // Используем имя сущности
        Session session = getCurrentSession(); // Получаем текущую сессию
        return session.createQuery(query, RestaurantTable.class)
                .setParameter("restaurantId", id)
                .getResultList(); // Возвращаем список столов по ресторану
    }
}
