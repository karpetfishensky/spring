package com.example.restaurantspring.mapper;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.web.request.RestaurantTableRequest;
import com.example.restaurant.web.response.RestaurantTableResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RestaurantService.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableMapper {


    @Mappings({
            @Mapping(source = "restaurantId", target = "restaurant"),
    })
    RestaurantTable toEntity(RestaurantTableRequest request);

    @Mappings({
            @Mapping(source = "restaurant.id", target = "restaurantId"),
            @Mapping(source = "restaurant.name", target = "restaurantName")
    })
    RestaurantTableResponse toResponse(RestaurantTable restaurantTable);

    @AfterMapping
    default void mapRestaurantIdToEntity(RestaurantTableRequest request, @MappingTarget RestaurantTable restaurantTable,
                                         @Context RestaurantService restaurantService) {
        if (request.getRestaurantId() != null) {
            Restaurant restaurant = restaurantService.get(request.getRestaurantId());
            restaurantTable.setRestaurant(restaurant);
        }
    }
}
