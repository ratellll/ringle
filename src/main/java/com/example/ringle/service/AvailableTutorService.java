package com.example.ringle.service;

import com.example.ringle.dto.availabletutor.AvailableTutorRequestDto;
import com.example.ringle.dto.tutor.TutorResponseDto;
import com.example.ringle.entity.Lesson;
import com.example.ringle.entity.Tutor;
import com.example.ringle.repository.LessonRepository;
import com.example.ringle.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableTutorService {

    private final TutorRepository tutorRepository;
    private final LessonRepository lessonRepository;

    public List<TutorResponseDto> findAvailableTutors(AvailableTutorRequestDto request) {
        List<Tutor> tutors = tutorRepository.findAll();
        List<Lesson> studentLessons = lessonRepository.findAll()
                .stream()
                .filter(lesson -> lesson.getStudent().getId().equals(request.getStudentId()))
                .collect(Collectors.toList());

        return tutors.stream()
                .filter(tutor -> isTutorAvailable(tutor, request.getStartTime(), request.getEndTime(), request.getDuration()))
                .filter(tutor -> isStudentAvailable(studentLessons, request.getStartTime(), request.getDuration()))
                .map(TutorResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    private boolean isTutorAvailable(Tutor tutor, LocalDateTime startTime, LocalDateTime endTime, int duration) {
        return tutor.getAvailableTimes().stream()
                .anyMatch(at ->
                        !at.getStartTime().isAfter(startTime) &&
                                !at.getEndTime().isBefore(startTime.plusMinutes(duration))
                ) &&
                tutor.getLessons().stream()
                        .noneMatch(lesson ->
                                startTime.isBefore(lesson.getEndTime()) &&
                                        startTime.plusMinutes(duration).isAfter(lesson.getStartTime())
                        );
    }

    private boolean isStudentAvailable(List<Lesson> studentLessons, LocalDateTime startTime, int duration) {
        LocalDateTime endTime = startTime.plusMinutes(duration);
        return studentLessons.stream()
                .noneMatch(lesson ->
                        startTime.isBefore(lesson.getEndTime()) &&
                                endTime.isAfter(lesson.getStartTime())
                );
    }
}