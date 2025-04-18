package com.lejito.epharma.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lejito.epharma.error.BadRequestError;
import com.lejito.epharma.error.NotFoundError;

import lombok.Data;

@Data
public class Cart {
    private List<Item> items;
    private List<Prescription> prescriptions;

    public Cart() {
        this.items = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public Cart(Cart cart) {
        this.items = new ArrayList<>(cart.getItems());
        this.prescriptions = new ArrayList<>(cart.getPrescriptions());
    }

    public void addMedicine(Medicine medicine, int quantity, Prescription prescription) {
        if (!medicine.hasStock(quantity)) {
            throw new BadRequestError(String.format("Not enough stock for %s (only %d available)", medicine.getName(),
                    medicine.getStock()));
        }

        if (medicine.isPrescriptionOnly()) {
            if (prescription == null) {
                throw new BadRequestError(String.format("Medicine %s requires a prescription", medicine.getName()));
            }

            if (prescription.getMedicine().getId() != medicine.getId()) {
                throw new BadRequestError(String.format("Prescription does not match medicine %s", medicine.getName()));
            }
        }

        for (Item item : items) {
            if (item.getMedicine().getId() == medicine.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new Item(medicine, quantity));
    }

    public void addMedicine(Medicine medicine, int quantity) {
        addMedicine(medicine, quantity, null);
    }

    public void removeMedicine(Medicine medicine, int quantity) {
        for (Item item : items) {
            if (item.getMedicine().getId() == medicine.getId()) {
                if (item.getQuantity() < quantity) {
                    throw new BadRequestError(
                            String.format("Not enough quantity of %s in cart (only %d available)", medicine.getName(),
                                    item.getQuantity()));
                } else if (item.getQuantity() == quantity) {
                    items.remove(item);
                } else {
                    item.setQuantity(item.getQuantity() - quantity);
                }
                return;
            }
        }
        throw new BadRequestError(String.format("Medicine %s not found in cart", medicine.getName()));
    }

    public void addPrescription(Prescription prescription) {
        if (!prescription.isAvailable()) {
            throw new BadRequestError("Prescription is not available");
        }

        for (Prescription presc : prescriptions) {
            if (presc.getId() == prescription.getId()) {
                throw new BadRequestError("Prescription already exists in cart");
            }
        }
        addMedicine(prescription.getMedicine(), prescription.getQuantity(), prescription);
        prescriptions.add(prescription);
    }

    public void removePrescription(Prescription prescription) {
        for (Prescription presc : prescriptions) {
            if (presc.getId() == prescription.getId()) {
                removeMedicine(prescription.getMedicine(), prescription.getQuantity());
                prescriptions.remove(presc);
                return;
            }
        }
        throw new NotFoundError("Prescription not found in cart");
    }

    @JsonProperty("totalPrice")
    public float calculatePrice() {
        float total = 0;
        for (Item item : items) {
            total += item.calculatePrice();
        }
        return total;
    }

    public Cart confirm() {
        if (items.isEmpty()) {
            throw new BadRequestError("Cart is empty");
        }

        Cart cart = new Cart(this);
        clear();
        return cart;
    }

    private void clear() {
        items.clear();
        prescriptions.clear();
    }
}
