package com.example.restaurantspring.entity.enumeration;

import lombok.Getter;

@Getter
public enum CuisineType {
    ITALIAN("Итальянская"),
    FRENCH("Французская"),
    JAPANESE("Японская"),
    GREEK("Греческая"),
    CHINESE("Китайская"),
    INDIAN("Индийская"),
    MEXICAN("Мексиканская"),
    RUSSIAN("Русская"),
    OTHER("Фирменная");

    private final String cuisine;

    CuisineType(String cuisine) {
        this.cuisine = cuisine;
    }

    public String toString() {
        return cuisine;
    }
}
