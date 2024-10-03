package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.mapper.TableMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.TableService;
import com.example.restaurant.web.request.RestaurantTableRequest;
import com.example.restaurant.web.response.RestaurantTableResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService restaurantTableService;
    private final TableMapper restaurantTableMapper;

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponse>> getRestaurantTables() {
        return ResponseEntity.ok(restaurantTableService.getAll().stream()
                .map(restaurantTableMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantTableById(@PathVariable Long id) {
        RestaurantTable restaurantTable = restaurantTableService.get(id);
        if (restaurantTable == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Стол с id = " + id + " не найден."));
        }
        return ResponseEntity.ok(restaurantTableMapper.toResponse(restaurantTable));
    }

    @PostMapping
    public ResponseEntity<RestaurantTableResponse> createRestaurantTable(@Valid @RequestBody RestaurantTableRequest request) {
        RestaurantTable restaurantTable = restaurantTableService.save(restaurantTableMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantTableMapper.toResponse(restaurantTable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurantTable(@PathVariable Long id,
                                                   @Valid @RequestBody RestaurantTableRequest request) {
        if (restaurantTableService.exists(id)) {
            RestaurantTable restaurantTable = restaurantTableService.update(id, restaurantTableMapper.toEntity(request));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurantTableMapper.toResponse(restaurantTable));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Стол с id = " + id + " не найден."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurantTable(@PathVariable Long id) {
        if (restaurantTableService.exists(id)) {
            restaurantTableService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Стол с id = " + id + " не найден."));
        }
    }
}
