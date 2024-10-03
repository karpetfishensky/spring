package com.example.restaurantspring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitors")
@EqualsAndHashCode(callSuper = false)
public class Visitor extends AbstractEntity{

    @Builder.ObtainVia(method = "getPhoneNumberFrom")
    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "reservation_name", nullable = false)
    private String reservationName;

    @Builder.Default
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantTable> tables = new HashSet<>();
}
