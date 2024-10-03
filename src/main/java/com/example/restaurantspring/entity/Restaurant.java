package com.example.restaurantspring.entity;

import com.example.restaurant.entity.embeddable.Address;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurants")
@EqualsAndHashCode(callSuper = false)
public class Restaurant extends AbstractEntity {

    @Embedded
    @JsonUnwrapped
    private Address address;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Chef> chefs = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Administrator> administrators = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Menu> menus = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantTable> tables = new HashSet<>();
}
