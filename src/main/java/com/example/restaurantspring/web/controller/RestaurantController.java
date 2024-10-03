package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.mapper.RestaurantMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.web.request.RestaurantRequest;
import com.example.restaurant.web.response.RestaurantResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getAll().stream()
                .map(restaurantMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantsById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.get(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Ресторан с id = " + id + " не найден."));
        }
        return ResponseEntity.ok(restaurantMapper.toResponse(restaurant));
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody RestaurantRequest request) {
        Restaurant restaurant = restaurantService.save(restaurantMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantMapper.toResponse(restaurant));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id,
                                              @Valid @RequestBody RestaurantRequest request) {
        if (restaurantService.exists(id)) {
            Restaurant restaurant = restaurantService.update(id, restaurantMapper.toEntity(request));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurantMapper.toResponse(restaurant));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Ресторан с id = " + id + " не найден."));
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        if (restaurantService.exists(id)) {
            restaurantService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Ресторан с id = " + id + " не найден."));
        }
    }
}
