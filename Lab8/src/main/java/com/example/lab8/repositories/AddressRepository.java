package com.example.lab8.repositories;

import com.example.lab8.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    AddressRepository extends JpaRepository<Address, Long> {
}
