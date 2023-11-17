package com.example.lab8.services;

import com.example.lab8.converters.StudentConverter;
import com.example.lab8.converters.StudentMapper;
import com.example.lab8.dtos.StudentDto;
import com.example.lab8.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final StudentMapper studentMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .toList();
//        return studentRepository.findAll().stream()
//                .map(studentConverter::convert)
//                .toList();
    }

    @Override
    public List<StudentDto> getAllStudentsNoAttachments() {
        return studentRepository.findAllNoAttachment();
    }
}
