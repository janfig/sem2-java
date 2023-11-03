package com.example.lab5.repositories;

import com.example.lab5.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {
}