package com.example.lab8.services;

import com.example.lab8.domain.Student;
import com.example.lab8.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getAllStudents();
    public List<StudentDto> getAllStudentsNoAttachments();
}
