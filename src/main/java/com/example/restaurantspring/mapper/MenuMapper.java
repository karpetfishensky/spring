package com.example.restaurantspring.mapper;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.entity.enumeration.DishType;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.web.request.MenuRequest;
import com.example.restaurant.web.response.MenuResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RestaurantService.class, DishType.class, CuisineType.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {

    @Mappings({
            @Mapping(source = "restaurantId", target = "restaurant"),
            @Mapping(target = "dishType", expression = "java(mapDishType(request.getDishType()))"),
            @Mapping(target = "cuisineType", expression = "java(mapCuisineType(request.getCuisineType()))")
    })
    Menu toEntity(MenuRequest request);

    @Mappings({
            @Mapping(source = "restaurant.id", target = "restaurantId"),
            @Mapping(source = "restaurant.name", target = "restaurantName")
    })
    MenuResponse toResponse(Menu menu);

    @AfterMapping
    default void mapRestaurantIdToEntity(MenuRequest request, @MappingTarget Menu menu,
                                         @Context RestaurantService restaurantService) {
        if (request.getRestaurantId() != null) {
            Restaurant restaurant = restaurantService.get(request.getRestaurantId());
            menu.setRestaurant(restaurant);
        }
    }

    default DishType mapDishType(String dishType) {
        return DishType.valueOf(dishType);
    }

    default CuisineType mapCuisineType(String cuisineType) {
        return CuisineType.valueOf(cuisineType);
    }
}
