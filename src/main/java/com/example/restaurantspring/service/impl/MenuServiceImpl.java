package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.entity.enumeration.DishType;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.ChefService;
import com.example.restaurant.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl
        extends AbstractService<Menu, MenuRepository>
        implements MenuService {

    private final ChefService chefService;

    public MenuServiceImpl(MenuRepository repository, ChefService chefService) {
        super(repository);
        this.chefService = chefService;
    }

    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Меню с id = %d не найдено";
    }

    @Override
    public Menu updateMenuByChefId(Long id, Menu menuRequest, Long chefId) {
        Chef chef = chefService.get(chefId);
        Menu menu = get(id);
        if (!menu.getCuisineType().equals(menuRequest.getCuisineType())) {
            return null;
        }
        if (!chef.getCuisineType().equals(menu.getCuisineType())) {
            return null;
        }
        return update(id, menuRequest);
    }


    @Override
    public List<Menu> getCombo(CuisineType cuisineType, Integer sum, boolean children) {
        List<Menu> allMenu = repository.findAllByCuisineType(cuisineType);

        List<Menu> mainDishes = getMenuByDishType(allMenu, DishType.MAIN_COURSE);
        List<Menu> salads = getMenuByDishType(allMenu, DishType.SALAD);
        List<Menu> desserts = getMenuByDishType(allMenu, DishType.DESSERT);
        List<Menu> beverages = children
                ? getMenuByDishType(allMenu, DishType.SOFT_DRINK)
                : getMenuByDishType(allMenu, DishType.BEVERAGE);

        List<List<Menu>> allCombos = new ArrayList<>();

        for (Menu main : mainDishes) {
            for (Menu salad : salads) {
                for (Menu dessert : desserts) {
                    for (Menu beverage : beverages) {
                        int totalPrice = main.getPrice() + salad.getPrice() + dessert.getPrice() + beverage.getPrice();
                        if (totalPrice <= sum) {
                            allCombos.add(List.of(main, salad, dessert, beverage));
                        }
                    }
                }
            }
        }

        if (!allCombos.isEmpty()) {
            return allCombos.get(0);
        } else {
            return List.of();
        }
    }

    private List<Menu> getMenuByDishType(List<Menu> menus, DishType dishType) {
        return menus.stream()
                .filter(menu -> menu.getDishType() == dishType)
                .toList();
    }



}
