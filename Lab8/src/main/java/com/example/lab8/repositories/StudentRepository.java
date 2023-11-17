package com.example.lab8.repositories;

import com.example.lab8.domain.Student;
import com.example.lab8.dtos.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.lab8.dtos.StudentDto(s.name, s.surname, s.age, "+
    "s.address.street, s.address.city, s.address.state, s.address.zip) from Student s")
    List<StudentDto> findAllNoAttachment();
}
