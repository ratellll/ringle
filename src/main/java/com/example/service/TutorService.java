package com.example.service;


import com.example.dto.TutorDto;
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
    public TutorDto createTutor(String name) {
        Tutor tutor = Tutor.of(name);
        return TutorDto.fromEntity(tutorRepository.save(tutor));
    }

    public List<TutorDto> getAllTutors() {
        return tutorRepository.findAll()
                .stream()
                .map(TutorDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Tutor findById(Long tutorId) {
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found: " + tutorId));
    }

}
