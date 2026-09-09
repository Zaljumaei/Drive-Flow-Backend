package com.zaljumaei.driveflow.lesson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zaljumaei.driveflow.lesson.dto.TheoryTopicResponse;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;
import com.zaljumaei.driveflow.lesson.service.TheoryTopicService;

import java.util.List;

/**
 * Controller class to handel HTTP requests for the theory topic.
 * And delegate the actual operations to the service layer.
 */
@RestController
@RequestMapping("/api/theory-topic")
public class TheoryTopicController {

    private final TheoryTopicService theoryTopicService;

    public TheoryTopicController(TheoryTopicService theoryTopicService) {
        this.theoryTopicService = theoryTopicService;
    }

    /**
     * Http endpoint for handling Post call to creat TheoryTopic.
     *
     * @param request The request including the Data of theory topic
     * @return response, which including CREATED status and theory topic response.
     */
    @PostMapping("/add")
    public ResponseEntity<TheoryTopicResponse> processTheoryTopic(@RequestBody TheoryTopicRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theoryTopicService.addTopic(request));
    }

    /**
     * Http endpoint to handle Update existed theory topic.
     *
     * @param request The request including the new data.
     * @param topicId The id of theory topic to be updated.
     * @return response of theory topic after update.
     */
    @PatchMapping("/update/{topicId}")
    public ResponseEntity<TheoryTopicResponse> updateTheoryTopic(@RequestBody TheoryTopicRequest request,
                                                         @PathVariable String topicId) {
        return ResponseEntity.ok().body(theoryTopicService.updateTopic(request, topicId));
    }

    /**
     * Http endpoint to handle Get request to get all TheoryTopic of DrivingSchool.
     *
     * @return List of all theoryTopicResponses.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TheoryTopicResponse>> getAll() {
        return ResponseEntity.ok().body(theoryTopicService.getAll());
    }

    /**
     * Http endpoint to handle Delete request for theory topic.
     *
     * @param topicId The id of theory topic to be deleted.
     */
    @DeleteMapping("/delete/{topicId}")
    public void deleteTheoryTopic(@PathVariable String topicId) {
        theoryTopicService.deleteTopic(topicId);
    }
}
