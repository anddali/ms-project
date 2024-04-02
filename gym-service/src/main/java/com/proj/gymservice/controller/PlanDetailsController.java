package com.proj.gymservice.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.proj.gymservice.dto.AddHistoryRecordDto;
import com.proj.gymservice.service.IPlanDetailsService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
// @AllArgsConstructor
@Validated
public class PlanDetailsController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(PlanDetailsController.class);
    private IPlanDetailsService planDetailsService;

    public PlanDetailsController(IPlanDetailsService planDetailsService) {
        this.planDetailsService = planDetailsService;
    }

    // showing cloud config server
    @Value("${build.version}")
    private String buildVersion;
    @Value("${details.description}")
    private String description;
    @Value("${details.author}")
    private String author;

    // get all plan details
    @GetMapping("/plan-details")
    public ResponseEntity<?> getAllPlanDetails() {
        return ResponseEntity.status(200).body(planDetailsService.getAllPlanDetails());
    }

    // get all exercises for a plan
    @GetMapping("/plan-details/{planId}")
    public ResponseEntity<?> getPlanDetailsByPlanId(@PathVariable Long planId) {
        return ResponseEntity.status(200).body(planDetailsService.getPlanDetailsByPlanId(planId));
    }

    // get exercise details by exercise id, user id, plan id and set number.
    @GetMapping("/plan-details/exercise/{planId}/{exerciseId}/{setNumber}/{userId}")
    public ResponseEntity<?> getPlanDetailsByExerciseIdAndUserIdAndPlanIdAndSetNumber(
            @RequestHeader("correlation-id") String correlationId,
            @PathVariable Long exerciseId,
            @PathVariable Long planId,
            @PathVariable Integer setNumber,
            @PathVariable Long userId) {
        logger.debug("CORRELATION ID: " + correlationId);
        return ResponseEntity.status(200).body(planDetailsService
                .getPlanDetailsByExerciseIdAndPlanIdAndSetNumber(correlationId, exerciseId, planId, setNumber, userId));
    }

    // add history record for a plan
    @PostMapping("/plan-details/history")
    public ResponseEntity<?> addHistoryRecord(@RequestHeader("correlation-id") String correlationId,
            @RequestBody @Valid AddHistoryRecordDto addHistoryRecordDto) {
        logger.debug("CORRELATION ID: " + correlationId);
        planDetailsService.addHistoryRecord(correlationId, addHistoryRecordDto);
        return ResponseEntity.status(201).body("History record added successfully");
    }

    // show cloud config server
    @GetMapping("/info")
    public String getVersion(@RequestHeader("correlation-id") String correlationId) {
        logger.debug("CORRELATION ID: " + correlationId);
        return description + ". Author: " + author + ". Build: " + buildVersion;
    }

}
