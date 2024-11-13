package com.coradini.sample.javaspringbootboilerplate.repository;

import com.coradini.sample.javaspringbootboilerplate.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
