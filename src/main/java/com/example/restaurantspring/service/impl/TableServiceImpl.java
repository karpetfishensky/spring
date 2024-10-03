package com.example.restaurantspring.service.impl;

import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.repository.TableRepository;
import com.example.restaurant.service.AbstractService;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.service.TableService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableServiceImpl
        extends AbstractService<RestaurantTable, TableRepository>
        implements TableService {

    private final RestaurantService restaurantService;

    public TableServiceImpl(TableRepository repository, RestaurantService restaurantService) {
        super(repository);
        this.restaurantService = restaurantService;
    }

    @Override
    public String getEntityNotFoundExceptionFormatString() {
        return "Столик с id = %d не найден";
    }

    @Override
    public List<RestaurantTable> getFreeTable(Long restaurantId) {
        if (restaurantId == null) {
            throw new EntityNotFoundException("Необходимо указать id ресторана в строке запроса");
        }
        if (!restaurantService.exists(restaurantId)) {
            throw new EntityNotFoundException("Ресторан с id = " + restaurantId + " не найден");
        }
        return repository.findAllByRestaurantId(restaurantId)
                .stream().filter(table -> !table.getReservation())
                .toList();
    }

    @Override
    public List<RestaurantTable> getFreeTable(Long restaurantId, Integer countSeat) {
        List<RestaurantTable> tables = getFreeTable(restaurantId);
        int minDifference = Integer.MAX_VALUE;
        List<RestaurantTable> suitableTables = new ArrayList<>();

        for (RestaurantTable table : tables) {
            int seatCount = table.getSeatCount();
            if (seatCount >= countSeat) {
                int difference = seatCount - countSeat;

                if (difference < minDifference) {
                    minDifference = difference;
                    suitableTables.clear();
                    suitableTables.add(table);
                } else if (difference == minDifference) {
                    suitableTables.add(table);
                }
            }
        }

        return suitableTables;
    }

}
