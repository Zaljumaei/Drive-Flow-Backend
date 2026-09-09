package com.zaljumaei.driveflow.lesson.service.impl;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;
import com.zaljumaei.driveflow.lesson.repository.TheoryTopicRepository;
import com.zaljumaei.driveflow.lesson.service.TheoryTopicService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TheoryTopicServiceImpl implements TheoryTopicService {

    private final TheoryTopicRepository theoryTopicRepository;

    public TheoryTopicServiceImpl(TheoryTopicRepository topicRepository) {
        this.theoryTopicRepository = topicRepository;
    }

    /**
     * Add Theory topic to the DrivingSchool, if it is not added before.
     *
     * @param request The request include the topic`s information.
     * @return created Response for created theory topic.
     */
    @Override
    public TheoryTopicResponse addTopic(TheoryTopicRequest request) {
        checkIfExistByTitle(request.title());

        TheoryTopic theoryTopic = TheoryTopic.builder()
                .title(request.title())
                .description(request.description())
                .topicNumber(request.topicNumber())
                .build();
        theoryTopicRepository.save(theoryTopic);

        return TheoryTopicResponse.builder()
                .id(theoryTopic.getId())
                .title(theoryTopic.getTitle())
                .description(theoryTopic.getDescription())
                .topicNumber(theoryTopic.getTopicNumber())
                .build();
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
     * @return TheoryTopicResponse after updated.
     */
    @Override
    public TheoryTopicResponse updateTopic(TheoryTopicRequest request, String theoryTopicId) {

        TheoryTopic theoryTopic = checkIfExistById(theoryTopicId);

        if (!request.title().equals(theoryTopic.getTitle())){
            theoryTopic.setTitle(request.title());
        }

        if (!request.description().equals(theoryTopic.getDescription())){
            theoryTopic.setDescription(request.description());
        }

        if (request.topicNumber() != theoryTopic.getTopicNumber()){
            theoryTopic.setTopicNumber(request.topicNumber());
        }

        theoryTopicRepository.save(theoryTopic);

        return TheoryTopicResponse.builder()
                .id(theoryTopic.getId())
                .title(theoryTopic.getTitle())
                .description(theoryTopic.getDescription())
                .topicNumber(theoryTopic.getTopicNumber())
                .build();
    }


    /**
     * Get all topics of school.
     * We don't use paging here, because it assumed that the number of lessons is not huge.
     *
     * @return List of all TheoryTopicResponse.
     */
    public List<TheoryTopicResponse> getAll() {

        List<TheoryTopic> theoryTopicList = theoryTopicRepository.findAll();

        return theoryTopicList.stream().map(theoryTopic -> TheoryTopicResponse.builder()
                .id(theoryTopic.getId())
                .title(theoryTopic.getTitle())
                .description(theoryTopic.getDescription())
                .topicNumber(theoryTopic.getTopicNumber())
                .build()).collect(Collectors.toList());
    }

    /**
     * Check if the topic with this title is already exist for this DrivingSchool,
     * if so, then throw an exception.
     * to avoid duplicate.
     *
     * @param title The title of the Topic.
     */
    private void checkIfExistByTitle(String title){
        theoryTopicRepository.findByTitle(title)
                .ifPresent( s  -> {
                    throw new EntityExistsException("The topic with the title "+ title + " already exist.");
                });
    }

    /**
     * Chek if theory topic is existed by its id, then return it if so,
     * otherwise arise a EntityNotFound exception.
     *
     * @param theoryTopicId The id of theory topic.
     * @return The existed Theory topic.
     */
    private TheoryTopic checkIfExistById(String theoryTopicId){
        TheoryTopic theoryTopic = theoryTopicRepository.findById(theoryTopicId)
                .orElseThrow(()-> new EntityNotFoundException("Theory topic with Id " +theoryTopicId+" not found!."));
        return theoryTopic;
    }
}
