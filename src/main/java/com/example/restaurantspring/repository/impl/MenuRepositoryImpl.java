package com.example.restaurantspring.repository.impl;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.repository.MenuRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepositoryImpl extends BaseRepositoryImpl<Menu> implements MenuRepository {

    public MenuRepositoryImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findAllByCuisineType(CuisineType cuisineType) {
        String query = "FROM Menu m WHERE m.cuisineType = :cuisineType";
        Session session = getCurrentSession();
        return session.createQuery(query, Menu.class)
                .setParameter("cuisineType", cuisineType)
                .getResultList();
    }
}
