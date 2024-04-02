package com.proj.gymservice.mapper;

import com.proj.gymservice.dto.PlanDetailsDto;
import com.proj.gymservice.entity.PlanDetails;

public class PlanDetailsMapper {

    private ExerciseMapper exerciseMapper;

    private WorkoutPlanMapper workoutPlanMapper;

    public PlanDetails toEntity(PlanDetailsDto dto) {
        PlanDetails entity = new PlanDetails();
        entity.setId(dto.getId());
        entity.setPlan(workoutPlanMapper.toEntity(dto.getPlanId()));
        entity.setExercise(exerciseMapper.toEntity(dto.getExerciseId()));
        entity.setCategory(dto.getCategory());
        entity.setSet(dto.getSet());
        entity.setReps(dto.getReps());
        return entity;
    }

    public PlanDetailsDto toDTO(PlanDetails entity) {
        PlanDetailsDto dto = new PlanDetailsDto();
        dto.setId(entity.getId());
        dto.setPlanId(entity.getPlan().getId());
        dto.setExerciseId(entity.getExercise().getId());
        dto.setCategory(entity.getCategory());
        dto.setSet(entity.getSet());
        dto.setReps(entity.getReps());
        return dto;
    }
}