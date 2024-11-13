package com.coradini.sample.javaspringbootboilerplate.service;

import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.AddressCreateRequest;
import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.AddressUpdateRequest;
import com.coradini.sample.javaspringbootboilerplate.entity.Address;
import com.coradini.sample.javaspringbootboilerplate.entity.Person;
import com.coradini.sample.javaspringbootboilerplate.exception.NotFoundException;
import com.coradini.sample.javaspringbootboilerplate.gateway.ViaCepGateway;
import com.coradini.sample.javaspringbootboilerplate.gateway.response.CepResponse;
import com.coradini.sample.javaspringbootboilerplate.repository.AddressRepository;
import com.coradini.sample.javaspringbootboilerplate.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final ViaCepGateway viaCepGateway;

    // Create a new address
    public Address create(AddressCreateRequest addressCreateRequest) {
        // Chamada à API do ViaCEP para buscar os dados do endereço
        CepResponse viaCepAddress = viaCepGateway.findAddressByCep(addressCreateRequest.getCep());
        //TODO can be null or not found, need try catch with notfound person exception
        Optional<Person> person = personRepository.findById(addressCreateRequest.getPersonId());
        Address address = Address.builder()
                .person(person.get())
                .cep(addressCreateRequest.getCep())
                .street(viaCepAddress.getLogradouro())
                .complement(addressCreateRequest.getComplement())
                .unit(addressCreateRequest.getUnit())
                .neighborhood(viaCepAddress.getBairro())
                .locality(viaCepAddress.getLocalidade())
                .stateCode(viaCepAddress.getUf())
                .state(viaCepAddress.getEstado())
                .region(viaCepAddress.getRegiao())
                .ibge(viaCepAddress.getIbge())
                .gia(viaCepAddress.getGia())
                .ddd(viaCepAddress.getDdd())
                .siafi(viaCepAddress.getSiafi())
                .build();

        return addressRepository.save(address);
    }

    // Update an existing address
    public Address update(UUID id, AddressUpdateRequest addressUpdateRequest) {
        Optional<Address> existingAddressOptional = addressRepository.findById(id);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            existingAddress.setCep(addressUpdateRequest.getCep());
            existingAddress.setStreet(addressUpdateRequest.getStreet());
            existingAddress.setComplement(addressUpdateRequest.getComplement());
            existingAddress.setUnit(addressUpdateRequest.getUnit());
            existingAddress.setNeighborhood(addressUpdateRequest.getNeighborhood());
            existingAddress.setLocality(addressUpdateRequest.getLocality());
            existingAddress.setStateCode(addressUpdateRequest.getStateCode());
            existingAddress.setState(addressUpdateRequest.getState());
            existingAddress.setRegion(addressUpdateRequest.getRegion());
            existingAddress.setIbge(addressUpdateRequest.getIbge());
            existingAddress.setGia(addressUpdateRequest.getGia());
            existingAddress.setDdd(addressUpdateRequest.getDdd());
            existingAddress.setSiafi(addressUpdateRequest.getSiafi());

            return addressRepository.save(existingAddress);
        } else {
            throw new NotFoundException("Address not found with id: " + id);
        }
    }

    // Delete an address by ID
    public void delete(UUID id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new NotFoundException("Address not found with id: " + id);
        }
    }

    // Get an address by ID
    public Address getById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found with id: " + id));
    }
}
