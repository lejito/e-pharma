package com.lejito.epharma.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int idPatient;
    private List<Item> items;
    private List<Prescription> prescriptions;
    private float totalPrice;
    private OrderStatus status;

    public Order(int id, int idPatient, List<Item> items, List<Prescription> prescriptions, float totalPrice) {
        this.id = id;
        this.idPatient = idPatient;
        this.items = items;
        this.prescriptions = prescriptions;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
    }

    public void approve() {
        List<Item> deductedItems = new ArrayList<>();
        try {
            for (Item item : items) {
                item.getMedicine().decrementStock(item.getQuantity());
                deductedItems.add(item);
            }

            for (Prescription prescription : prescriptions) {
                prescription.setAvailable(false);
            }

            this.status = OrderStatus.APPROVED;
        } catch (RuntimeException e) {
            rollbackDecrement(deductedItems);
            throw e;
        }

    }

    public void reject() {
        this.status = OrderStatus.REJECTED;
    }

    private void rollbackDecrement(List<Item> deductedItems) {
        for (Item deductedItem : deductedItems) {
            deductedItem.getMedicine().incrementStock(deductedItem.getQuantity());
        }
    }
}
