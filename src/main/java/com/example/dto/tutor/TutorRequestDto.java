package com.example.dto.tutor;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TutorRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    @Schema(description = "튜터 이름", example = "홍길동")
    private String name;

}
