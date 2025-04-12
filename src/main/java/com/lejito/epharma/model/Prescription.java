package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Prescription {
    private int id;
    private Patient patient;
    private Medicine medicine;
    private int quantity;
    private Doctor prescribedBy;
    private boolean available;
}
