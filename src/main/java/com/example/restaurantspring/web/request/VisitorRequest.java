package com.example.restaurantspring.web.request;

import com.example.restaurant.entity.OwnerPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorRequest implements OwnerPhoneNumber {

    @Builder.ObtainVia(method = "getPhoneNumberFrom")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @NotBlank(message = "Имя резервации не может быть пустым")
    @NotNull(message = "Имя резервирования не может быть null")
    private String reservationName;
}
