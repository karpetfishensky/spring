package com.example.restaurantspring.entity;

import com.example.restaurant.entity.embeddable.FullName;
import com.example.restaurant.entity.embeddable.Passport;
import com.example.restaurant.entity.enumeration.CuisineType;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chefs")
@EqualsAndHashCode(callSuper = false)
public class Chef extends AbstractEntity {

    @Embedded
    private Passport passport;

    @Embedded
    private FullName fullName;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "cuisineType")
    private CuisineType cuisineType = CuisineType.OTHER;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;
}
