package com.service.cep_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "zipcode can't be empty")
    @Size(min = 8, max = 8, message = "zipcode must have 8 numbers")
    private String zipcode;
    @NotBlank(message = "number can't be empty")
    private String number;
}
