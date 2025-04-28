package com.example.service;


import com.example.dto.LessonDto;
import com.example.entity.Lesson;
import com.example.entity.Student;
import com.example.entity.Tutor;
import com.example.repository.LessonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {


    private final LessonRepository lessonRepository;
    private final StudentService studentService;
    private final TutorService tutorService;

    @Transactional
    public LessonDto bookLesson(Long tutorId, Long studentId, LocalDateTime startTime, LocalDateTime endTime, int duration) {
        Tutor tutor = tutorService.findById(tutorId);
        Student student = studentService.findById(studentId);

        validateBooking(tutor, startTime, endTime);

        Lesson lesson = Lesson.of(tutor, student, startTime, endTime, duration, "BOOKED");
        return LessonDto.fromEntity(lesson);
    }

    public List<LessonDto> getLessonsByStudent(Long studentId) {
        return lessonRepository.findAll()
                .stream()
                .filter(lesson -> lesson.getStudent().getId().equals(studentId))
                .map(LessonDto::fromEntity)
                .collect(Collectors.toList());
    }

    private void validateBooking(Tutor tutor, LocalDateTime startTime, LocalDateTime endTime) {
        List<Lesson> existingLessons = tutor.getLessons();

        boolean conflict = existingLessons.stream()
                .anyMatch(lesson ->
                        startTime.isBefore(lesson.getEndTime()) && endTime.isAfter(lesson.getStartTime())
                );
        if (conflict) {
            throw new IllegalStateException("Lesson time conflicts with existing lessons.");
        }



    }
}
