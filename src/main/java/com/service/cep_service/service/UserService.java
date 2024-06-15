package com.service.cep_service.service;

import com.service.cep_service.dto.BrasilApiResponseDTO;
import com.service.cep_service.dto.UserDTO;
import com.service.cep_service.dto.UserInsertDTO;
import com.service.cep_service.dto.ViaCepResponseDTO;
import com.service.cep_service.entities.User;
import com.service.cep_service.http.BrasilApiClient;
import com.service.cep_service.http.ViaCepClient;
import com.service.cep_service.repositories.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CepService cepService;


    @CircuitBreaker(name = "createUser", fallbackMethod = "createUserFallback")
    public UserDTO createUser(UserInsertDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setZipcode(dto.getZipcode());

        cepService.findAddressWithViaCep(user);

        return new UserDTO(repository.save(user));
    }

    public UserDTO createUserFallback(UserInsertDTO dto, Exception e) {
        System.out.println("Fallback chamado");
        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setZipcode(dto.getZipcode());

        cepService.findAddressWithBrasilApi(user);

        return new UserDTO(repository.save(user));
    }





}
