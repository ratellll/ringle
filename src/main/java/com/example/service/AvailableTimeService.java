package com.example.service;


import com.example.dto.AvailableTimeDto;
import com.example.dto.TutorDto;
import com.example.entity.AvailableTime;
import com.example.entity.Tutor;
import com.example.repository.AvailableTimeRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TutorRepository;
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


    public AvailableTimeDto createAvailableTime(Long tutorId, LocalDateTime startTime, LocalDateTime endTime) {
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("Tutor 가 맞는지 체크해주십시오"));
        AvailableTime availableTime = AvailableTime.of(startTime, endTime, tutor);
        return AvailableTimeDto.fromEntity(availableTimeRepository.save(availableTime));
    }

    public void deleteAvailableTime(Long availableTimeId) {
        availableTimeRepository.deleteById(availableTimeId);
    }

    public List<AvailableTimeDto> getAvailableTimesByTutor(Long tutorId) {
        return availableTimeRepository.findAll()
                .stream()
                .filter(at -> at.getTutor().getId().equals(tutorId))
                .map(AvailableTimeDto::fromEntity)
                .collect(Collectors.toList());
    }

}
