package com.proj.gymservice.service;

import java.util.List;

import com.proj.gymservice.dto.AddHistoryRecordDto;
import com.proj.gymservice.entity.PlanDetails;

public interface IPlanDetailsService {
    // get all plan details
    public List<PlanDetails> getAllPlanDetails();

    // get all exercises for a plan
    public Object getPlanDetailsByPlanId(Long planId);

    // add history record for a plan
    public void addHistoryRecord(String correlationId, AddHistoryRecordDto addHistoryRecordDto);

    public Object getPlanDetailsByExerciseIdAndPlanIdAndSetNumber(String correlationId, Long exerciseId, Long planId,
            Integer setNumber, Long userId);

}
