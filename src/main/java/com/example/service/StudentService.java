package com.example.service;


import com.example.dto.student.StudentRequestDto;
import com.example.dto.student.StudentResponseDto;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {
        Student student = Student.builder()
                .name(request.getName())
                .build();
        return StudentResponseDto.fromEntity(studentRepository.save(student));
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("학생 id값 =  " + id + " 존재 하지않음"));
    }

}
