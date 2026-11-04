package com.zaljumaei.driveflow.lesson.dto.TheoryLesson;

import com.zaljumaei.driveflow.instructor.service.InstructorService;
import com.zaljumaei.driveflow.lesson.domain.TheoryLesson;
import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.service.TheoryTopicService;
import org.springframework.stereotype.Component;

@Component
public class TheoryLessonMapper {

    private TheoryTopicService theoryTopicService;
    private InstructorService instructorService;

    /**
     * Map a request to entity, this can be used to create new entity.
     *
     * @param request the request with all data for {@link TheoryLesson}.
     * @return the entity after be created and filled with data from request.
     */
    public TheoryLesson toEntity(TheoryLessonRequest request){
        TheoryLesson theoryLesson = new TheoryLesson();

        TheoryTopic topic = theoryTopicService.findById(request.topicId());
        theoryLesson.setTopic(topic);
        theoryLesson.setInstructor(instructorService.findEntityById(request.instructorId()));
        theoryLesson.setLessonDate(request.lessonDate());
        theoryLesson.setStartTime(request.startTime());
        theoryLesson.setEndTime(request.endTime());
        theoryLesson.setDurationMinutes(request.durationMinutes());

        return theoryLesson;
    }

    /**
     * Map the {@link TheoryLesson} entity to {@link TheoryLessonResponse}
     *
     * @param theoryLesson the entity.
     * @return the response after mapping.
     */
    public TheoryLessonResponse toTheoryLessonResponse(TheoryLesson theoryLesson) {
        return TheoryLessonResponse.builder()
                .id(theoryLesson.getId())
                .topicId(theoryLesson.getTopic().getId())
                .topicTitle(theoryLesson.getTopic().getTitle())
                .instructorId(theoryLesson.getInstructor().getId())
                .instructorName(
                        theoryLesson.getInstructor().getPersonDetails().getFullname())
                .lessonDate(theoryLesson.getLessonDate())
                .startTime(theoryLesson.getStartTime())
                .endTime(theoryLesson.getEndTime())
                .durationMinutes(theoryLesson.getDurationMinutes())
                .notes(theoryLesson.getNotes())
                .build();
    }

    /**
     * Apply partially or whole updating on the {@link TheoryLesson} entity from request.
     * This methode assume that the entity exist.
     *
     * @param request the request with the new data.
     * @param lesson the theoryLesson entity.
     */
    public void updateEntity(
            TheoryLessonRequest request,
            TheoryLesson lesson
    ) {
        if(request.instructorId() != null){
            lesson.setInstructor(instructorService.findEntityById(request.instructorId()));
        }
        if(request.topicId() != null){
            lesson.setTopic(theoryTopicService.findById(request.topicId()));
        }
        if (request.lessonDate() != null) {
            lesson.setLessonDate(request.lessonDate());
        }

        if (request.startTime() != null) {
            lesson.setStartTime(request.startTime());
        }

        if (request.endTime() != null) {
            lesson.setEndTime(request.endTime());
        }

        if (request.durationMinutes() > 0) {
            lesson.setDurationMinutes(request.durationMinutes());
        }

        if (request.notes() != null) {
            lesson.setNotes(request.notes());
        }
    }
}
