package com.lejito.epharma.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Administrator extends User {
    private String role;

    public Administrator(int id, String name, String email, String password, String role) {
        super(id, name, email, password);
        this.role = role;
    }
}
