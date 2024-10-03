package com.example.restaurantspring.web.controller;

import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.mapper.TableMapper;
import com.example.restaurant.mapper.VisitorMapper;
import com.example.restaurant.service.BookingService;
import com.example.restaurant.service.TableService;
import com.example.restaurant.web.request.BookingRequest;
import com.example.restaurant.web.response.BookingResponse;
import com.example.restaurant.web.response.RestaurantTableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final TableService tableService;
    private final TableMapper tableMapper;
    private final VisitorMapper visitorMapper;

    @GetMapping("/free")
    public ResponseEntity<List<RestaurantTableResponse>> getFreeTables(
            @RequestParam(required = false) Long restaurantId,
            @RequestParam(required = false) Integer countSeat) {

        List<RestaurantTable> tables;
        if (countSeat != null) {
            tables = tableService.getFreeTable(restaurantId, countSeat);
        } else {
            tables = tableService.getFreeTable(restaurantId);
        }

        return ResponseEntity.ok(tables.stream()
                .map(tableMapper::toResponse)
                .toList());
    }

    @GetMapping("/book")
    public ResponseEntity<BookingResponse> book(@RequestBody BookingRequest request) {
        RestaurantTable table = bookingService.booking(request.getVisitorId(),
                request.getTableId(), request.getCountSeat());
        return ResponseEntity.ok(BookingResponse.builder()
                .message("Столик забронирован")
                .visitor(visitorMapper.toResponse(table.getVisitor()))
                .table(tableMapper.toResponse(table))
                .build());
    }

    @GetMapping("/unBook")
    public ResponseEntity<BookingResponse> unBook(@RequestBody BookingRequest request) {
        RestaurantTable table = bookingService.unBooking(request.getTableId());
        return ResponseEntity.ok(BookingResponse.builder()
                .message("Бронь снята")
                .visitor(visitorMapper.toResponse(table.getVisitor()))
                .table(tableMapper.toResponse(table))
                .build());
    }
}

