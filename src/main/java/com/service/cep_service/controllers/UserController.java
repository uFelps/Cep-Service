package com.service.cep_service.controllers;

import com.service.cep_service.dto.UserDTO;
import com.service.cep_service.dto.UserInsertDTO;
import com.service.cep_service.http.ViaCepClient;
import com.service.cep_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {



    @Autowired
    private UserService service;

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserInsertDTO dto){

        UserDTO newUser = service.createUser(dto);

        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();


        return ResponseEntity.created(uri).body(newUser);
    }
}
