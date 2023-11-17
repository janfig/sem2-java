package com.example.lab7.services;

import com.example.lab7.entities.Student;
import com.example.lab7.entities.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public boolean deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public boolean updateStudent(int id, Student student) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        student.setId(id);
        studentRepository.save(student);
        return true;
    }

    public boolean studentExists(int id) {
        return studentRepository.existsById(id);
    }
}
