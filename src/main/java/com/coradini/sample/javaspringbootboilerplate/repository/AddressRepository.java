package com.coradini.sample.javaspringbootboilerplate.repository;

import com.coradini.sample.javaspringbootboilerplate.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
