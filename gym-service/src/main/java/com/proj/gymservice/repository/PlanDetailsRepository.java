package com.proj.gymservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.gymservice.entity.PlanDetails;

public interface PlanDetailsRepository extends JpaRepository<PlanDetails, Long> {

    Object findDistinctCategoriesByPlanId(Long planId);

    List<PlanDetails> findByPlanId(Long planId);

    Object findByPlanIdAndCategory(Long planId, String category);

    Object findByExerciseIdAndAndPlanIdAndSet(Long exerciseId, Long planId, Integer setNumber);

}
