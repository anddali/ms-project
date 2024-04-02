package com.proj.gymservice.mapper;

import com.proj.gymservice.dto.WorkoutPlanDto;
import com.proj.gymservice.entity.WorkoutPlan;

public class WorkoutPlanMapper {

    public WorkoutPlan toEntity(Long planId) {
        WorkoutPlan entity = new WorkoutPlan();
        entity.setId(planId);
        return entity;
    }

    public WorkoutPlanDto toDTO(WorkoutPlan entity) {
        WorkoutPlanDto dto = new WorkoutPlanDto();
        dto.setId(entity.getId());
        dto.setPlanName(entity.getPlanName());
        dto.setPlanDescription(entity.getPlanDescription());
        return dto;
    }
}