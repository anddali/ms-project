package com.proj.gymservice.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proj.gymservice.dto.AddHistoryRecordDto;
import com.proj.gymservice.dto.ExerciseHistoryDto;
import com.proj.gymservice.entity.PlanDetails;
import com.proj.gymservice.exception.ResourceNotFoundException;
import com.proj.gymservice.repository.PlanDetailsRepository;
import com.proj.gymservice.service.IPlanDetailsService;
import com.proj.gymservice.service.client.TrackerFeintClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlanDetailsServiceImpl implements IPlanDetailsService {

    PlanDetailsRepository planDetailsRepository;
    TrackerFeintClient trackerFeintClient;

    @Override
    public List<PlanDetails> getAllPlanDetails() {
        return planDetailsRepository.findAll();
    }

    @Override
    public List<PlanDetails> getPlanDetailsByPlanId(Long planId) {
        List<PlanDetails> planDetails = planDetailsRepository.findByPlanId(planId);
        if (planDetails.isEmpty()) {
            throw new ResourceNotFoundException("No plan details found for plan with id " + planId);
        }
        return planDetails;
    }

    @Override
    public void addHistoryRecord(String correlationId, AddHistoryRecordDto addHistoryRecordDto) {
        Long planId = addHistoryRecordDto.getPlanId();
        Long userId = addHistoryRecordDto.getUserId();
        Double weight = addHistoryRecordDto.getWeight();
        Integer repCount = addHistoryRecordDto.getRepCount();

        PlanDetails planDetails = planDetailsRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan details not found for id " + planId));
        Long exerciseId = planDetails.getExercise().getId();
        Integer set = planDetails.getSet();
        ExerciseHistoryDto exerciseHistory = new ExerciseHistoryDto();
        exerciseHistory.setUserId(userId);
        exerciseHistory.setExerciseId(exerciseId);
        exerciseHistory.setSetNumber(set);
        exerciseHistory.setWeightInKg(weight);
        exerciseHistory.setRepCount(repCount);
        trackerFeintClient.createExerciseHistory(correlationId, exerciseHistory);
    }

    @Override
    public Object getPlanDetailsByExerciseIdAndPlanIdAndSetNumber(String correlationId, Long exerciseId, Long planId,
            Integer setNumber, Long userId) {
        Object planDetails = planDetailsRepository.findByExerciseIdAndAndPlanIdAndSet(exerciseId, planId,
                setNumber);
        if (planDetails == null) {
            throw new ResourceNotFoundException(
                    "No plan details found for exercise with id " + exerciseId + " and plan with id " + planId);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("planDetails", planDetails);

        List<ExerciseHistoryDto> exerciseHistory = trackerFeintClient
                .fetchLatestExerciseHistory(correlationId, userId, exerciseId, setNumber);

        if (exerciseHistory != null) {
            resultMap.put("exerciseHistory", exerciseHistory);
        } else {
            resultMap.put("exerciseHistory", null);
        }

        return resultMap;
    }

}
