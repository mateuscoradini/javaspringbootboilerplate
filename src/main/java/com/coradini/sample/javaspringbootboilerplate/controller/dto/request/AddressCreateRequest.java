package com.coradini.sample.javaspringbootboilerplate.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressCreateRequest {

    private UUID personId;
    private String cep;          // Postal code
    private String street;       // Logradouro
    private String complement;   // Complemento
    private String unit;         // Unidade
    private String neighborhood; // Bairro
    private String locality;     // Localidade
    private String stateCode;    // UF (State Code)
    private String state;        // Estado
    private String region;       // Região
    private String ibge;         // IBGE Code
    private String gia;          // GIA Code
    private String ddd;          // DDD Code
    private String siafi;        // SIAFI Code
}
