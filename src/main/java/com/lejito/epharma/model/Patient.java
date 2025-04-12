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

}
