package com.proj.gymservice.mapper;

import com.proj.gymservice.dto.ExerciseDto;
import com.proj.gymservice.entity.Exercise;

public class ExerciseMapper {

    public Exercise toEntity(Long exerciseId) {
        Exercise entity = new Exercise();
        entity.setId(exerciseId);
        return entity;
    }

    public ExerciseDto toDTO(Exercise entity) {
        ExerciseDto dto = new ExerciseDto();
        dto.setId(entity.getId());
        dto.setExerciseName(entity.getExerciseName());
        dto.setExerciseDescription(entity.getExerciseDescription());
        dto.setMuscleGroup(entity.getMuscleGroup());
        return dto;
    }
}