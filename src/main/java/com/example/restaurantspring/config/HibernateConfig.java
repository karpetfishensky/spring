package com.example.restaurantspring.config;

import com.example.restaurant.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Restaurant.class);
        configuration.addAnnotatedClass(RestaurantTable.class);
        configuration.addAnnotatedClass(Chef.class);
        configuration.addAnnotatedClass(Menu.class);
        configuration.addAnnotatedClass(Administrator.class);
        configuration.addAnnotatedClass(Visitor.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

}
