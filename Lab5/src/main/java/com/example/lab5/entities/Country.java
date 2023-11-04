package com.example.lab5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "country")
@Data
public class Country {
    @Column(columnDefinition = "char")
    @Id
    String code;

    @Column(columnDefinition = "char")
    String name;

    Integer population;

    @Column(columnDefinition = "decimal")
    Integer surfaceArea;

    @Column(columnDefinition = "enum('Asia', 'Europe', 'North America', 'Africa', 'Oceania', 'Antarctica', 'South America')")
    String continent;
}
