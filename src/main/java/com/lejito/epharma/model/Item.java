package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Item {
    private Medicine medicine;
    private int quantity;

    float calculatePrice() {
        return medicine.calculatePrice(quantity);
    }
}
