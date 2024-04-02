package com.proj.gymservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WorkoutPlanDto {

    private Long id;

    @NotBlank(message = "Plan name is mandatory")
    private String planName;

    @NotBlank(message = "Plan description is mandatory")
    private String planDescription;

}