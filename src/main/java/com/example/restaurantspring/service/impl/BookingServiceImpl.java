package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.entity.Visitor;
import com.example.restaurant.service.BookingService;
import com.example.restaurant.service.TableService;
import com.example.restaurant.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final TableService tableService;
    private final VisitorService visitorService;

    @Override
    public RestaurantTable booking(Long visitorId, Long restaurantTableId, Integer countSeat) {
        Visitor visitor = visitorService.get(visitorId);
        RestaurantTable restaurantTable = tableService.get(restaurantTableId);
        if (restaurantTable.getReservation()) {
            throw new EntityNotFoundException("Данный столик уже забронирован");
        }
        if (restaurantTable.getSeatCount() < countSeat) {
            throw new EntityNotFoundException("На данном столике недостаточно места");
        }
        restaurantTable.setVisitor(visitor);
        restaurantTable.setReservation(true);
        restaurantTable.setReservationSeat(restaurantTable.getReservationSeat() - countSeat);
        return tableService.update(restaurantTable.getId(), restaurantTable);
    }

    @Override
    public RestaurantTable unBooking(Long restaurantTableId) {
        RestaurantTable restaurantTable = tableService.get(restaurantTableId);
        restaurantTable.setReservation(false);
        restaurantTable.setReservationSeat(0);
        restaurantTable.setVisitor(null);
        return tableService.update(restaurantTable.getId(), restaurantTable);
    }
}
