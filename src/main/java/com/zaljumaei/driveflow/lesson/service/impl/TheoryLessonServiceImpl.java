package com.zaljumaei.driveflow.lesson.service.impl;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.domain.TheoryLesson;
import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonMapper;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonResponse;
import com.zaljumaei.driveflow.lesson.repository.TheoryLessonRepository;
import com.zaljumaei.driveflow.lesson.repository.TheoryTopicRepository;
import com.zaljumaei.driveflow.lesson.service.TheoryLessonService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation class for the interface {@link TheoryLessonService}.
 * The request and response pattern (dto) is used in most methode.
 */
@Service
public class TheoryLessonServiceImpl implements TheoryLessonService {

    private final TheoryLessonRepository theoryLessonRepository;
    private final TheoryTopicRepository theoryTopicRepository;
    private final TheoryLessonMapper theoryLessonMapper;

    public TheoryLessonServiceImpl(
        TheoryLessonRepository theoryLessonRepository,
        TheoryTopicRepository theoryTopicRepository,
        TheoryLessonMapper theoryLessonMapper
    ) {
        this.theoryLessonRepository = theoryLessonRepository;
        this.theoryTopicRepository = theoryTopicRepository;
        this.theoryLessonMapper = theoryLessonMapper;
    }


    /**
     * Create a new theory lesson.
     *
     * @param request The theory lesson request.
     * @return The created theory lesson response.
     */
    @Override
    public TheoryLessonResponse create(TheoryLessonRequest request) {

        validateLessonTimes(request);

        TheoryLesson lesson = theoryLessonMapper.toEntity(request);

        TheoryLesson savedLesson = theoryLessonRepository.save(lesson);

        return theoryLessonMapper.toTheoryLessonResponse(savedLesson);
    }


    /**
     * Find a theory lesson by id and return its response.
     *
     * @param id The theory lesson id.
     * @return The theory lesson response.
     */
    @Override
    public TheoryLessonResponse findById(String id) {

        TheoryLesson lesson = checkIfExistById(id);

        return theoryLessonMapper.toTheoryLessonResponse(lesson);
    }

    /**
     * Find a theory lesson entity by id.
     *
     * @param id The theory lesson id.
     * @return The theory lesson entity.
     */
    @Override
    public TheoryLesson findEntityById(String id) {
        return checkIfExistById(id);
    }

    /**
     * Find all theory lessons with pagination.
     *
     * @param pageNumber Page number.
     * @return A page of theory lesson responses.
     */
    @Override
    public PageResponse<TheoryLessonResponse> findAll(int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, 14);
        Page<TheoryLesson> lessons = theoryLessonRepository.findAll(pageable);

        return PageResponse.<TheoryLessonResponse>builder()
                .content(lessons.stream().map(theoryLessonMapper::toTheoryLessonResponse).collect(Collectors.toList()))
                .totalPages(lessons.getTotalPages())
                .totalElement(lessons.getNumberOfElements())
                .isFirst(lessons.isFirst())
                .isLast(lessons.isLast())
                .build();
    }

    /**
     * Update an existing theory lesson.
     *
     * @param id The theory lesson id.
     * @param request The updated data.
     * @return The updated theory lesson response.
     */
    @Override
    public TheoryLessonResponse update(
            String id,
            TheoryLessonRequest request
    ) {

        validateLessonTimes(request);

        TheoryLesson lesson = checkIfExistById(id);

        theoryLessonMapper.updateEntity(request, lesson);

        return theoryLessonMapper.toTheoryLessonResponse(lesson);
    }

    /**
     * Delete a theory lesson by id.
     *
     * @param id The theory lesson id.
     */
    @Override
    public void delete(String id) {

        TheoryLesson lesson = checkIfExistById(id);

        theoryLessonRepository.delete(lesson);
    }



    //------------------Help methode----------------//

    /**
     * Check if a theory lesson exists by id.
     * If not, an exception will be thrown.
     *
     * @param id The theory lesson id.
     * @return The existing theory lesson entity.
     * @throws EntityNotFoundException If the lesson does not exist.
     */
    private TheoryLesson checkIfExistById(String id) {

        Optional<TheoryLesson> lesson = theoryLessonRepository.findById(id);

        if (lesson.isEmpty()) {
            throw new EntityNotFoundException(
                    "Theory lesson with id " + id + " does not exist"
            );
        }

        return lesson.get();
    }

    /**
     * Check if a theory topic exists by id.
     * If not, an exception will be thrown.
     *
     * @param id The theory topic id.
     * @return The existing theory topic entity.
     */
    private TheoryTopic checkIfTopicExistById(String id) {
        Optional<TheoryTopic> topic = theoryTopicRepository.findById(id);

        if (topic.isEmpty()) {
            throw new EntityNotFoundException(
                    "Theory topic with id " + id + " does not exist"
            );
        }
        return topic.get();
    }

    /**
     * Validate the lesson times and duration.
     *
     * @param request The theory lesson request.
     */
    private void validateLessonTimes(TheoryLessonRequest request) {

        if (request == null
                || request.lessonDate() == null
                || request.startTime() == null
                || request.endTime() == null) {

            throw new IllegalArgumentException(
                    "Lesson date, start time and end time are required"
            );
        }

        if (!request.endTime().isAfter(request.startTime())) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }

        long duration = ChronoUnit.MINUTES.between(
                request.startTime(),
                request.endTime()
        );

        if (duration != request.durationMinutes()) {
            throw new IllegalArgumentException(
                    "Duration does not match start and end time"
            );
        }
    }

}
