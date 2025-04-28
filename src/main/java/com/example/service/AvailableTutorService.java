package com.example.service;

import com.example.dto.TutorDto;
import com.example.entity.Lesson;
import com.example.entity.Tutor;
import com.example.repository.AvailableTimeRepository;
import com.example.repository.LessonRepository;
import com.example.repository.TutorRepository;
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

    public List<TutorDto> findAvailableTutors(Long studentId,LocalDateTime startDateTime, LocalDateTime endDateTime, int duration) {
        List<Tutor> tutors = tutorRepository.findAll();
        List<Lesson> studentLessons = lessonRepository.findAll()
                .stream()
                .filter(lesson -> lesson.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        return tutors.stream()
                .filter(tutor -> isTutorAvailable(tutor, startDateTime, endDateTime, duration))
                .filter(tutor -> isStudentAvailable(studentLessons, startDateTime, duration))
                .map(TutorDto::fromEntity)
                .collect(Collectors.toList());
    }

    private boolean isTutorAvailable(Tutor tutor, LocalDateTime startDateTime, LocalDateTime endDateTime, int duration) {
        boolean available = tutor.getAvailableTimes().stream()
                .anyMatch(av ->
                        !av.getStartTime().isAfter(startDateTime) &&
                                !av.getEndTime().isBefore(startDateTime.plusMinutes(duration))
                );
        if (!available) {
            return false;
        }
        boolean conflict = tutor.getLessons().stream()
                .anyMatch(lesson ->
                        startDateTime.isBefore(lesson.getEndTime()) &&
                                startDateTime.plusMinutes(duration).isAfter(lesson.getStartTime())
                );

        return !conflict;
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