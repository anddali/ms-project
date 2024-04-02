package com.proj.gymservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExerciseDto {

    private Long id;

    @NotBlank(message = "Exercise name is mandatory")
    private String exerciseName;

    @NotBlank(message = "Exercise description is mandatory")
    private String exerciseDescription;

    @NotBlank(message = "Muscle group is mandatory")
    private String muscleGroup;

}