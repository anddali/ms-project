package com.proj.gymservice.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.proj.gymservice.dto.ExerciseHistoryDto;

@FeignClient(name = "tracker", fallback = TrackerFallback.class)
public interface TrackerFeintClient {

    @GetMapping("/api/history/{userId}/{exerciseId}/{setNumber}")
    public List<ExerciseHistoryDto> fetchLatestExerciseHistory(
            @RequestHeader("correlation-id") String correlationId, @PathVariable("userId") Long userId,
            @PathVariable("exerciseId") Long exerciseId, @PathVariable("setNumber") Integer setNumber);

    @PostMapping("/api/history")
    public void createExerciseHistory(@RequestHeader("correlation-id") String correlationId,
            ExerciseHistoryDto exerciseHistory);
}
