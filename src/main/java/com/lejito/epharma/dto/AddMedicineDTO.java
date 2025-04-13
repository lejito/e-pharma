package com.lejito.epharma.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMedicineDTO {
    @NotNull(message = "idMedicine is required")
    private int idMedicine;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be at least 1")
    private int quantity;
}
