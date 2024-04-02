package com.proj.gymservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PlanDetailsDto {

    private Long id;

    @NotBlank(message = "Plan is mandatory")
    private Long planId;

    @NotBlank(message = "Exercise is mandatory")
    private Long exerciseId;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Set is mandatory")
    @Positive(message = "Set should be a positive number")
    private Integer set;

    @NotBlank(message = "Reps is mandatory")
    @PositiveOrZero(message = "Reps should be a positive number")
    private Integer reps;

}
