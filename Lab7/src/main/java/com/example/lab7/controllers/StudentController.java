package com.example.lab7.controllers;

import com.example.lab7.entities.Student;
import com.example.lab7.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getStudentList();
    }

    @PostMapping("/students")
    public String add(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
        return "Dodano studenta do bazy";
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id) {
        if (!studentService.studentExists(id)) {
            throw new ResourceNotFoundException();
        }
        studentService.deleteStudent(id);
    }

    @PutMapping("/students/{id}")
    public void update(@PathVariable int id, @RequestBody @Valid Student student) {
        if (!studentService.studentExists(id)) {
            throw new ResourceNotFoundException();
        }
        studentService.updateStudent(id, student);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleException(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String, List<String>> response = new HashMap<>();
        response.put("errors", errorList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
