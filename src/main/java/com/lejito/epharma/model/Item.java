package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Item {
    private Medicine medicine;
    private int quantity;

    public Item(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    float calculatePrice() {
        return medicine.calculatePrice(quantity);
    }
}
