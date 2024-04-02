package com.proj.gymservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.gymservice.entity.WorkoutPlan;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

}
