package com.lejito.epharma.model;

import java.util.List;

import lombok.Data;

@Data
public class Order {
    private int id;
    private Patient patient;
    private List<Item> items;
    private List<Prescription> prescriptions;
    private float totalPrice;
    private OrderStatus status;

    public Order(int id, Patient patient, List<Item> items, List<Prescription> prescriptions, float totalPrice) {
        this.id = id;
        this.patient = patient;
        this.items = items;
        this.prescriptions = prescriptions;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
    }

    public void approve() {
        this.status = OrderStatus.APPROVED;
    }

    public void reject() {
        this.status = OrderStatus.REJECTED;
    }
}
