package com.example.restaurantspring.mapper;

import com.example.restaurant.entity.Administrator;
import com.example.restaurant.entity.Chef;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.service.AdministratorService;
import com.example.restaurant.web.request.ChefRequest;
import com.example.restaurant.web.response.ChefResponse;
import com.example.restaurant.web.response.MenuResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AdministratorService.class, CuisineType.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChefMapper {

    @Mappings({
            @Mapping(source = "administratorId", target = "administrator"),
            @Mapping(target = "cuisineType", expression = "java(mapCuisineType(request.getCuisineType()))")
    })
    Chef toEntity(ChefRequest request);

    @Mappings({
            @Mapping(source = "restaurant.id", target = "restaurantId"),
            @Mapping(source = "restaurant.name", target = "restaurantName"),
            @Mapping(source = "administrator.id", target = "administratorId"),
            @Mapping(source = "administrator.fullName", target = "administratorName"),
    })
    ChefResponse toResponse(Chef chef);

    @AfterMapping
    default void mapRestaurantIdToEntity(ChefRequest request, @MappingTarget Chef chef,
                                         @Context AdministratorService administratorService) {
        if (request.getAdministratorId() != null) {
            Administrator administrator = administratorService.get(request.getAdministratorId());
            chef.setAdministrator(administrator);
        }
    }

    default CuisineType mapCuisineType(String cuisineType) {
        return CuisineType.valueOf(cuisineType);
    }
}
