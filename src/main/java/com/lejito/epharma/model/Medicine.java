package com.lejito.epharma.model;

import lombok.Data;

@Data
public class Medicine {
    private int id;
    private String name;
    private String description;
    private float price;
    private int stock;
    private boolean prescriptionOnly;

    public Medicine(int id, String name, String description, float price, int stock, boolean prescriptionOnly) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.prescriptionOnly = prescriptionOnly;
    }

    float calculatePrice(int quantity) {
        return price * quantity;
    }

    boolean hasStock(int quantity) {
        return stock >= quantity;
    }
}
