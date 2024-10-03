package com.example.restaurantspring.entity;

import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.entity.enumeration.DishType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Menu extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "dish_type")
    private DishType dishType = DishType.SIGNATURE;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type")
    private CuisineType cuisineType = CuisineType.OTHER;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
