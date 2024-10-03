package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.enumeration.CuisineType;
import com.example.restaurant.mapper.MenuMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.web.request.ComboRequest;
import com.example.restaurant.web.request.MenuRequest;
import com.example.restaurant.web.response.MenuResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getMenus() {
        return ResponseEntity.ok(menuService.getAll().stream()
                .map(menuMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable Long id) {
        Menu menu = menuService.get(id);
        if (menu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Меню с id = " + id + " не найдено."));
        }
        return ResponseEntity.ok(menuMapper.toResponse(menu));
    }

    @GetMapping("/combo")
    public ResponseEntity<List<MenuResponse>> getCombo(@RequestBody ComboRequest request,
                                                       @RequestParam(required = false) String children) {
        return ResponseEntity.ok(menuService.getCombo(CuisineType.valueOf(request.getCuisineType()),
                        request.getPrice(), children != null).stream()
                .map(menuMapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody MenuRequest request) {
        Menu menu = menuService.save(menuMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuMapper.toResponse(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id,
                                        @Valid @RequestBody MenuRequest request,
                                        @RequestParam(required = false) Long chefId) {
        if (!menuService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Меню с id = " + id + " не найдено."));
        }
        if (chefId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Необходимо указать id шеф-повара в строке запросе"));
        }
        Menu menu = menuService.updateMenuByChefId(id, menuMapper.toEntity(request), chefId);
        if (menu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Шеф-повар может менять только те позиции в меню, что относятся к его кухне"));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuMapper.toResponse(menu));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        if (menuService.exists(id)) {
            menuService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Меню с id = " + id + " не найдено."));
        }
    }
}
