package com.coradini.sample.javaspringbootboilerplate.controller;

import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.PersonCreateRequest;
import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.PersonUpdateRequest;
import com.coradini.sample.javaspringbootboilerplate.entity.Person;
import com.coradini.sample.javaspringbootboilerplate.service.PersonService;
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
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Create a new person", description = "Creates a new person with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonCreateRequest request) {
        Person createdPerson = personService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @Operation(summary = "Update an existing person", description = "Updates the details of an existing person.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @Parameter(description = "ID of the person to be updated", required = true) @PathVariable UUID id,
            @Valid @RequestBody PersonUpdateRequest request) {
        Person updatedPerson = personService.update(id, request);
        return ResponseEntity.ok(updatedPerson);
    }

    @Operation(summary = "Delete a person", description = "Deletes a person by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Person deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@Parameter(description = "ID of the person to be deleted", required = true) @PathVariable UUID id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a person by ID", description = "Retrieves a person by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@Parameter(description = "ID of the person to be retrieved", required = true) @PathVariable UUID id) {
        Person person = personService.getById(id);
        return ResponseEntity.ok(person);
    }
}
