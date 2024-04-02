package com.proj.gymservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.gymservice.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
