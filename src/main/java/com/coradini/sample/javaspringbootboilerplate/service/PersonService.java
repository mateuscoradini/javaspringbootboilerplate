package com.coradini.sample.javaspringbootboilerplate.service;

import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.PersonCreateRequest;
import com.coradini.sample.javaspringbootboilerplate.controller.dto.request.PersonUpdateRequest;
import com.coradini.sample.javaspringbootboilerplate.entity.Person;
import com.coradini.sample.javaspringbootboilerplate.repository.PersonRepository;
import com.coradini.sample.javaspringbootboilerplate.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    // Create a new person
    public Person create(PersonCreateRequest personCreateRequest) {
        Person person = Person.builder()
                .email(personCreateRequest.getEmail())
                .name(personCreateRequest.getName())
                .lastName(personCreateRequest.getLastName())
                .build();

        return personRepository.save(person);
    }

    // Update an existing person
    public Person update(UUID id, PersonUpdateRequest personUpdateRequest) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setEmail(personUpdateRequest.getEmail());
            existingPerson.setName(personUpdateRequest.getName());
            existingPerson.setLastName(personUpdateRequest.getLastName());
            // Update other fields if necessary
            return personRepository.save(existingPerson);
        } else {
            throw new NotFoundException("Person not found with id: " + id);
        }
    }

    // Delete a person by ID
    public void delete(UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new NotFoundException("Person not found with id: " + id);
        }
    }

    // Get a person by ID
    public Person getById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));
    }
}
