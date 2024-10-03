package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Chef;
import com.example.restaurant.mapper.ChefMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.ChefService;
import com.example.restaurant.web.request.ChefRequest;
import com.example.restaurant.web.response.ChefResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chefs")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;
    private final ChefMapper chefMapper;

    @GetMapping
    public ResponseEntity<List<ChefResponse>> getAllChefs() {
        List<Chef> chefs = chefService.getAll();
        return ResponseEntity.ok(chefs.stream()
                .map(chefMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChefById(@PathVariable Long id) {
        Chef chef = chefService.get(id);
        if (chef == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Шеф-повар с id = " + id + " не найден."));
        }
        return ResponseEntity.ok(chefMapper.toResponse(chef));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChef(@PathVariable Long id,
                                        @Valid @RequestBody ChefRequest request) {
        if (!chefService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Шеф-повар с id = " + id + " не найден."));
        }
        Chef chef = chefService.get(id);
        if (!chef.getAdministrator().getId().equals(request.getAdministratorId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Изменить данные шеф-повара может только принявший его на работу администратор"));
        }
        Chef chefTo = chefMapper.toEntity(request);
        chefTo = chefService.update(id, chefTo);
        return ResponseEntity.ok(chefMapper.toResponse(chefTo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChef(@PathVariable Long id) {
        if (!chefService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Шеф-повар с id = " + id + " не найден."));
        }
        chefService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
