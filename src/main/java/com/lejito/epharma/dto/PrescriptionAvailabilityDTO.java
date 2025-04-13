package com.lejito.epharma.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionAvailabilityDTO {
    @NotNull(message = "Availability is required")
    private boolean available;
}
