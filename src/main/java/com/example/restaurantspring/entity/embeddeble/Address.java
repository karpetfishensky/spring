package com.example.restaurantspring.entity.embeddeble;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotNull(message = "Страна не может быть null")
    @NotBlank(message = "Страна не может быть пустой")
    @Size(max = 50, message = "Длина страны не должна превышать 50 символов")
    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @NotNull(message = "Город не может быть null")
    @NotBlank(message = "Город не может быть пустым")
    @Size(max = 50, message = "Длина города не должна превышать 50 символов")
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @NotNull(message = "Улица не может быть null")
    @NotBlank(message = "Улица не могут быть пустой")
    @Size(max = 100, message = "Длина улицы не должна превышать 100 символов")
    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @NotNull(message = "Номер дома не может быть null")
    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(max = 10, message = "Длина номера дома не должна превышать 10 символов")
    @Column(name = "house_number", length = 10, nullable = false)
    private String houseNumber;

    @Pattern(regexp = "\\d{5,10}", message = "Почтовый индекс должен содержать от 5 до 10 цифр")
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Size(max = 50, message = "Длина района не должна превышать 50 символов")
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50, message = "Длина региона не должна превышать 50 символов")
    @Column(name = "region", length = 50)
    private String region;

}
