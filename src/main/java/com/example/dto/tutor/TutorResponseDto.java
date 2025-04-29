package com.example.dto.tutor;


import com.example.entity.Tutor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TutorResponseDto {

    @Schema(description = "튜터 ID", example = "1")
    private Long id;

    @Schema(description = "튜터 이름", example = "Tom")
    private String name;

    public static TutorResponseDto fromEntity(Tutor tutor) {
        return TutorResponseDto.builder()
                .id(tutor.getId())
                .name(tutor.getName())
                .build();
    }
}
