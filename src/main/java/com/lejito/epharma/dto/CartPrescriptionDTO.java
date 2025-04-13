package com.lejito.epharma.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartPrescriptionDTO {
    @NotNull(message = "idPrescription is required")
    private int idPrescription;

}
