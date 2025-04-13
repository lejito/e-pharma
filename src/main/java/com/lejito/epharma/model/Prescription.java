package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Prescription {
    private int id;
    private int idPatient;
    private Medicine medicine;
    private int quantity;
    private boolean available;

    public Prescription(int id, int idPatient, Medicine medicine, int quantity) {
        this.id = id;
        this.idPatient = idPatient;
        this.medicine = medicine;
        this.quantity = quantity;
        this.available = true;
    }
}
