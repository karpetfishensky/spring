package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Administrator;
import com.example.restaurant.mapper.AdministratorMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.AdministratorService;
import com.example.restaurant.web.request.AdministratorRequest;
import com.example.restaurant.web.response.AdministratorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;

    @GetMapping
    public ResponseEntity<List<AdministratorResponse>> getAdministrators() {
        return ResponseEntity.ok(administratorService.getAll().stream()
                .map(administratorMapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministratorById(@PathVariable Long id) {
        Administrator administrator = administratorService.get(id);
        if (administrator == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Администратор с id = " + id + " не найден."));
        }
        return ResponseEntity.ok(administratorMapper.toResponse(administrator));
    }

    @PostMapping
    public ResponseEntity<AdministratorResponse> createAdministrator(@Valid @RequestBody AdministratorRequest request) {
        Administrator administrator = administratorService.save(administratorMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(administratorMapper.toResponse(administrator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrator(@PathVariable Long id,
                                                 @Valid @RequestBody AdministratorRequest request) {
        if (administratorService.exists(id)) {
            Administrator administrator = administratorService.update(id, administratorMapper.toEntity(request));
            return ResponseEntity.ok(administratorMapper.toResponse(administrator));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Администратор с id = " + id + " не найден."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdministrator(@PathVariable Long id) {
        if (administratorService.exists(id)) {
            administratorService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Администратор с id = " + id + " не найден."));
        }
    }
}
