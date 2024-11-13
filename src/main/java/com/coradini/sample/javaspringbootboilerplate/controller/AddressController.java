package com.coradini.sample.javaspringbootboilerplate.controller;

import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.AddressCreateRequest;
import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.AddressUpdateRequest;
import com.coradini.sample.javaspringbootboilerplate.entity.Address;
import com.coradini.sample.javaspringbootboilerplate.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Create a new address", description = "Creates a new address with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressCreateRequest request) {
        Address createdAddress = addressService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @Operation(summary = "Update an existing address", description = "Updates the details of an existing address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @Parameter(description = "ID of the address to be updated", required = true) @PathVariable UUID id,
            @Valid @RequestBody AddressUpdateRequest request) {
        Address updatedAddress = addressService.update(id, request);
        return ResponseEntity.ok(updatedAddress);
    }

    @Operation(summary = "Delete an address", description = "Deletes an address by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@Parameter(description = "ID of the address to be deleted", required = true) @PathVariable UUID id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get an address by ID", description = "Retrieves an address by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@Parameter(description = "ID of the address to be retrieved", required = true) @PathVariable UUID id) {
        Address address = addressService.getById(id);
        return ResponseEntity.ok(address);
    }
}
