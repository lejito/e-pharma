package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Prescription {
    private int id;
    private Patient patient;
    private Medicine medicine;
    private int quantity;
    private boolean available;

    public Prescription(int id, Patient patient, Medicine medicine, int quantity) {
        this.id = id;
        this.patient = patient;
        this.medicine = medicine;
        this.quantity = quantity;
        this.available = true;
    }
}
