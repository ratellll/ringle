package com.example.dto;


import com.example.entity.Tutor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TutorDto {

    private Long id;
    private String name;


    public static TutorDto fromEntity(Tutor tutor) {
        return TutorDto.builder()
                .id(tutor.getId())
                .name(tutor.getName())
                .build();
    }

    public Tutor toEntity() {
        return Tutor.of(name);
    }


}
