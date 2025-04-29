package com.example.service;


import com.example.dto.tutor.TutorRequestDto;
import com.example.dto.tutor.TutorResponseDto;
import com.example.entity.Tutor;
import com.example.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorService {


    private final TutorRepository tutorRepository;

    @Transactional
    public TutorResponseDto createTutor(TutorRequestDto request) {
        Tutor tutor = Tutor.builder()
                .name(request.getName())
                .build();
        return TutorResponseDto.fromEntity(tutorRepository.save(tutor));
    }

    public List<TutorResponseDto> getAllTutors() {
        return tutorRepository.findAll()
                .stream()
                .map(TutorResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Tutor findById(Long tutorId) {
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found: " + tutorId));
    }

}
