package com.service.cep_service.http;

import com.service.cep_service.dto.BrasilApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "brasilapi", url = "https://brasilapi.com.br/api/cep/v1")
public interface BrasilApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{zipcode}")
    BrasilApiResponseDTO findAddress(@PathVariable String zipcode);
}
