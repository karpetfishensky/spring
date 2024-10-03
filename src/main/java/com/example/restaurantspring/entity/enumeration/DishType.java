package com.example.restaurantspring.entity.enumeration;

import lombok.Getter;

@Getter
public enum DishType {

    APPETIZER("Закуска"),
    MAIN_COURSE("Основное блюдо"),
    DESSERT("Десерт"),
    BEVERAGE("Напиток"),
    SOFT_DRINK("Безалкогольный напиток"),
    SALAD("Салат"),
    SIGNATURE("Фирменное"),
    SOUP("Суп");

    private final String type;

    DishType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
