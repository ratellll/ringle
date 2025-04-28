package com.example.dto;


import com.example.entity.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentDto {

    private Long id;
    private String name;

    public static StudentDto fromEntity(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .build();
    }

    public Student toEntity() {
        return Student.of(name);
    }


}
