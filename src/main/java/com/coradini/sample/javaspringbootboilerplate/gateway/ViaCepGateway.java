package com.coradini.sample.javaspringbootboilerplate.gateway;

import com.coradini.sample.javaspringbootboilerplate.gateway.response.CepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepGateway {

    private final RestTemplate restTemplate;

    public ViaCepGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepResponse findAddressByCep(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        return restTemplate.getForObject(url, CepResponse.class);
    }
}
