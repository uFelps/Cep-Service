package com.service.cep_service.dto;

import com.service.cep_service.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String zipcode;
    private String state;
    private String city;
    private String neighborhood;
    private String address;
    private String number;

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();;
        zipcode = user.getZipcode();
        state = user.getState();
        city = user.getCity();
        neighborhood = user.getNeighborhood();
        address = user.getAddress();
        number = user.getNumber();
    }
}
