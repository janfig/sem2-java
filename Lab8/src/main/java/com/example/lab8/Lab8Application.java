package com.example.lab8;

import com.example.lab8.repositories.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class Lab8Application {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab8Application.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        byte[] fileBytes = Files.readAllBytes(Path.of("/home/jan/Studia/java/example.pdf"));
        for (var s : studentRepository.findAll()) {
            s.setAttachment(fileBytes);
            studentRepository.save(s);
        }
    }
}
