package com.example.ringle.service;


import com.example.ringle.dto.availabletime.AvailableTimeResponseDto;
import com.example.ringle.entity.AvailableTime;
import com.example.ringle.entity.Tutor;
import com.example.ringle.repository.AvailableTimeRepository;
import com.example.ringle.repository.TutorRepository;
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

    public List<AvailableTimeResponseDto> findAvailableTimes(LocalDateTime start, LocalDateTime end, int duration) {
        List<AvailableTime> all = availableTimeRepository.findAll();

        return all.stream()
                .filter(time -> time.getStartTime().isBefore(end) && time.getEndTime().isAfter(start))
                .filter(time -> {
                    long availableMinutes = java.time.Duration.between(time.getStartTime(), time.getEndTime()).toMinutes();
                    return availableMinutes >= duration;
                })
                .map(AvailableTimeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
