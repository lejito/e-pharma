package com.lejito.epharma.model;

import com.lejito.epharma.error.BadRequestError;

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

    public void incrementStock(int quantity) {
        stock += quantity;
    }

    public void decrementStock(int quantity) {
        if (stock < quantity) {
            throw new BadRequestError(String.format("Not enough stock for %s (only %d available)", name, stock));
        }
        stock -= quantity;
    }

    public boolean hasStock(int quantity) {
        return stock >= quantity;
    }

    public float calculatePrice(int quantity) {
        return price * quantity;
    }
}
