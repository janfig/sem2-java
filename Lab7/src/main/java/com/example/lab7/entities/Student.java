package com.example.lab7.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonView
    @NotBlank(message = "Imie nie może być puste")
    private String name;

    @JsonView
    @NotBlank(message = "Nazwisko nie może być puste")
    private String surname;

    @JsonView
    @NotNull
    @Range(min = 2, max = 5, message = "Średnia musi być w zakresie 2.0 - 5.0")
    private double average;

}
