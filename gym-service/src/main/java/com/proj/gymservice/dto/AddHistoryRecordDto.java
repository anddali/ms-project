package com.proj.gymservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddHistoryRecordDto {

    @NotNull(message = "Plan ID cannot be null")
    private Long planId;

    @NotNull(message = "Weight cannot be null")
    private Double weight;

    @NotNull(message = "Reps cannot be null")
    private Integer repCount;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}