package com.example.service;


import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentDto createStudent(String name) {
        Student student = Student.of(name);
        return StudentDto.fromEntity(studentRepository.save(student));
    }

    public List<StudentDto> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("학생 id값 =  " + id + " 존재 하지않음"));
    }

}
