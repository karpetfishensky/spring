package com.example.restaurantspring.entity.embeddeble;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FullName {

    @NotBlank(message = "Фамилия не может быть пустой")
    @NotNull(message = "Фамилия не может быть null")
    @Size(max = 50, message = "Фамилия не может превышать 50 символов")
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotBlank(message = "Имя не может быть пустым")
    @NotNull(message = "Имя паспорта не может быть null")
    @Size(max = 50, message = "Имя не может превышать 50 символов")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50, message = "Отчество не может превышать 50 символов")
    @Column(name = "middle_name", length = 50)
    private String middleName;
}
