package com.example.restaurantspring.entity.embeddeble;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    @NotBlank(message = "Серия паспорта не может быть пустой")
    @NotNull(message = "Серия паспорта не может быть null")
    @Size(min = 4, max = 4, message = "Серия паспорта должна содержать ровно 4 символа")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта должна состоять только из цифр")
    @Column(name = "passport_series", length = 4, nullable = false)
    private String series;

    @NotBlank(message = "Номер паспорта не может быть пустым")
    @NotNull(message = "Номер паспорта не может быть null")
    @Size(min = 6, max = 6, message = "Номер паспорта должен содержать ровно 6 символов")
    @Pattern(regexp = "\\d{6}", message = "Номер паспорта должен состоять только из цифр")
    @Column(name = "passport_number", length = 6, nullable = false)
    private String number;

    @NotBlank(message = "Кем выдан паспорт не может быть пустым")
    @Size(max = 255, message = "Поле 'Кем выдан' не может превышать 255 символов")
    @Column(name = "issued")
    private String issued;

    @Past(message = "Дата выдачи паспорта должна быть в прошлом")
    @Column(name = "issue_date")
    private LocalDate issueDate;
}
