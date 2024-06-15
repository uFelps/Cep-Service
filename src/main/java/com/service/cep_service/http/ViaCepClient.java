package com.service.cep_service.http;

import com.service.cep_service.dto.ViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{zipcode}/json")
    ViaCepResponseDTO findAddress(@PathVariable String zipcode);
}
