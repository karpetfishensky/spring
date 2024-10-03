package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.Visitor;
import com.example.restaurant.mapper.VisitorMapper;
import com.example.restaurant.message.Message;
import com.example.restaurant.service.VisitorService;
import com.example.restaurant.web.request.VisitorRequest;
import com.example.restaurant.web.response.VisitorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/visitors")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;
    private final VisitorMapper visitorMapper;

    @GetMapping
    public ResponseEntity<List<VisitorResponse>> getVisitors() {
        List<VisitorResponse> visitors = visitorService.getAll().stream()
                .map(visitorMapper::toResponse)
                .toList();
        return ResponseEntity.ok(visitors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVisitorById(@PathVariable Long id) {
        Visitor visitor = visitorService.get(id);
        if (visitor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Посетитель с id = " + id + " не найден."));
        }
        return ResponseEntity.ok(visitorMapper.toResponse(visitor));
    }

    @PostMapping
    public ResponseEntity<VisitorResponse> createVisitor(@Valid @RequestBody VisitorRequest request) {
        Visitor visitor = visitorService.save(visitorMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(visitorMapper.toResponse(visitor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorRequest request) {
        if (visitorService.exists(id)) {
            Visitor visitor = visitorService.update(id, visitorMapper.toEntity(request));
            return ResponseEntity.ok(visitorMapper.toResponse(visitor));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Посетитель с id = " + id + " не найден."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVisitor(@PathVariable Long id) {
        if (visitorService.exists(id)) {
            visitorService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Message("Посетитель с id = " + id + " не найден."));
        }
    }
}
