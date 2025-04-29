package com.example.service;


import com.example.dto.availabletime.AvailableTimeResponseDto;
import com.example.entity.AvailableTime;
import com.example.entity.Tutor;
import com.example.repository.AvailableTimeRepository;
import com.example.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableTimeService {


    private final AvailableTimeRepository availableTimeRepository;
    private final TutorRepository tutorRepository;


    @Transactional
    public AvailableTimeResponseDto createAvailableTime(Long tutorId, LocalDateTime startTime, LocalDateTime endTime) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found: " + tutorId));

        AvailableTime availableTime = AvailableTime.builder()
                .tutor(tutor)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return AvailableTimeResponseDto.fromEntity(availableTimeRepository.save(availableTime));
    }

    @Transactional
    public void deleteAvailableTime(Long availableTimeId) {
        availableTimeRepository.deleteById(availableTimeId);
    }

    public List<AvailableTimeResponseDto> getAvailableTimesByTutor(Long tutorId) {
        return availableTimeRepository.findAll()
                .stream()
                .filter(at -> at.getTutor().getId().equals(tutorId))
                .map(AvailableTimeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

}
