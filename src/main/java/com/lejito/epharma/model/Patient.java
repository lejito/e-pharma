package com.lejito.epharma.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {
    private Cart cart;

    public Patient(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.cart = new Cart();
    }

    public void addMedicineToCart(Medicine medicine, int quantity) {
        cart.addMedicine(medicine, quantity);
    }

    public void removeMedicineFromCart(Medicine medicine, int quantity) {
        cart.removeMedicine(medicine, quantity);
    }

    public void addPrescriptionToCart(Prescription prescription) {
        if (prescription.getPatient().getId() != this.getId()) {
            throw new RuntimeException("Prescription does not belong to this patient");
        }
        cart.addPrescription(prescription);
    }

    public void removePrescriptionFromCart(Prescription prescription) {
        cart.removePrescription(prescription);
    }

    public Cart confirmCart() {
        Cart confirmedCart = new Cart(cart);
        cart.confirm();
        return confirmedCart;
    }
}
