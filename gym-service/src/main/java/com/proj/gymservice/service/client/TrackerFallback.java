package com.proj.gymservice.service.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proj.gymservice.dto.ExerciseHistoryDto;

@Component
public class TrackerFallback implements TrackerFeintClient {

    @Override
    public List<ExerciseHistoryDto> fetchLatestExerciseHistory(String correlationId, Long userId,
            Long exerciseId, Integer setNumber) {
        return null;
    }

    @Override
    public void createExerciseHistory(String correlationId, ExerciseHistoryDto exerciseHistory) {
        throw new RuntimeException("Failed to create exercise history");
    }

}
