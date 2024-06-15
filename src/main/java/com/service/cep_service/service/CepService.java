package com.service.cep_service.service;

import com.service.cep_service.controllers.exceptions.CepClientException;
import com.service.cep_service.dto.BrasilApiResponseDTO;
import com.service.cep_service.dto.ViaCepResponseDTO;
import com.service.cep_service.entities.User;
import com.service.cep_service.http.BrasilApiClient;
import com.service.cep_service.http.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private BrasilApiClient brasilApiClient;

    public void findAddressWithViaCep(User user) {
        try {
            ViaCepResponseDTO response = viaCepClient.findAddress(user.getZipcode());
            user.setState(response.getUf());
            user.setCity(response.getLocalidade());
            user.setNeighborhood(response.getBairro());
            user.setAddress(response.getLogradouro());

            if (response.getLogradouro().isEmpty()){
                throw new CepClientException("Error On ViaCep API. Check if your zipcode is correct");
            }

        } catch (Exception e) {
            throw new CepClientException("Error On ViaCep API");
        }


    }

    public void findAddressWithBrasilApi(User user) {
        try {
            BrasilApiResponseDTO response = brasilApiClient.findAddress(user.getZipcode());

            user.setState(response.getState());
            user.setCity(response.getCity());
            user.setNeighborhood(response.getNeighborhood());
            user.setAddress(response.getStreet());
        } catch (Exception e) {
            throw new CepClientException("Error On Brasil API. Check if your zipcode is correct");
        }
    }
}
