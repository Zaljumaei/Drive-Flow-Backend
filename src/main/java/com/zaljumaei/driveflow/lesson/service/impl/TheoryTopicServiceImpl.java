package com.zaljumaei.driveflow.lesson.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;
import com.zaljumaei.driveflow.lesson.repository.TheoryTopicRepository;
import com.zaljumaei.driveflow.lesson.service.TheoryTopicService;


@Service
public class TheoryTopicServiceImpl implements TheoryTopicService {

    private final TheoryTopicRepository theoryTopicRepository;

    public TheoryTopicServiceImpl(TheoryTopicRepository topicRepository) {
        this.theoryTopicRepository = topicRepository;
    }

    /**
     * Add Theory topic to the DrivingSchool, if it is not added before.
     * Since Theory topic is simple object, we don't need a DTO and Mapper.
     *
     * @param request The request include the topic`s information.
     * @return created theory topic.
     */
    @Override
    public TheoryTopic addTopic(TheoryTopicRequest request) {
        checkIfExistByTitle(request.title());

        TheoryTopic theoryTopic = TheoryTopic.builder()
                .title(request.title())
                .description(request.description())
                .topicNumber(request.TopicNumber())
                .build();
        theoryTopicRepository.save(theoryTopic);

        return theoryTopic;
    }

    /**
     * Delete a theory topic if it existed.
     *
     * @param theoryTopicId The id of the theory topic to be deleted.
     */
    @Override
    public void deleteTopic(String theoryTopicId) {
        TheoryTopic theoryTopic = checkIfExistById(theoryTopicId);

        theoryTopicRepository.delete(theoryTopic);
    }

    /**
     * Update an existed theory topic with new data from request.
     *
     * @param request The request included the new information.
     * @param theoryTopicId The id of theory topic to be updated.
     * @return TheoryTopic after updated.
     */
    @Override
    public TheoryTopic updateTopic(TheoryTopicRequest request, String theoryTopicId) {

        TheoryTopic theoryTopic = checkIfExistById(theoryTopicId);

        if (!request.title().equals(theoryTopic.getTitle())){
            theoryTopic.setTitle(request.title());
        }

        if (!request.description().equals(theoryTopic.getDescription())){
            theoryTopic.setDescription(request.description());
        }

        if (request.TopicNumber() != theoryTopic.getTopicNumber()){
            theoryTopic.setTopicNumber(request.TopicNumber());
        }

        theoryTopicRepository.save(theoryTopic);

        return theoryTopic;
    }

    /**
     * Check if the topic with this title is alread exist for this DrivingSchool,
     * if so, then throw an exception.
     *
     * @param title The title of the Topic.
     */
    private void checkIfExistByTitle(String title){
        theoryTopicRepository.findByTitle(title)
                .ifPresent( s  -> {
                    throw new EntityExistsException("The topic with the title "+ title + " already exist.");
                });
    }

    private TheoryTopic checkIfExistById(String theoryTopicId){
        TheoryTopic theoryTopic = theoryTopicRepository.findById(theoryTopicId)
                .orElseThrow(()-> new EntityNotFoundException("Theorytopic with Id " +theoryTopicId+" not found!."));
        return theoryTopic;
    }
}
