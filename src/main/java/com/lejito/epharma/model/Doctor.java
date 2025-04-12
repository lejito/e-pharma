package com.lejito.epharma.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends User {
    private String specialization;

    public Doctor(int id, String name, String email, String password, String specialization) {
        super(id, name, email, password);
        this.specialization = specialization;
    }

}
