package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.error.NotFoundError;
import com.lejito.epharma.model.Medicine;

import lombok.Data;

@Service
@Data
public class MedicineService {
    private List<Medicine> medicines;

    public MedicineService() {
        medicines = new ArrayList<>();
        medicines.add(new Medicine(1, "Paracetamol", "Pain reliever and fever reducer", 5.99f, 10, false));
        medicines.add(new Medicine(2, "Ibuprofen", "Anti-inflammatory pain reliever", 7.99f, 50, false));
        medicines.add(new Medicine(3, "Amoxicillin", "Antibiotic for bacterial infections", 15.99f, 10, true));
        medicines.add(new Medicine(4, "Aspirin", "Pain reliever and anti-inflammatory", 4.99f, 20, false));
    }

    public Medicine getMedicine(int idMedicine) {
        for (Medicine medicine : medicines) {
            if (medicine.getId() == idMedicine) {
                return medicine;
            }
        }
        throw new NotFoundError("Medicine not found");
    }

    public Medicine addMedicine(String name, String description, float price, int stock, boolean prescriptionOnly) {
        int id = medicines.size() + 1;
        Medicine medicine = new Medicine(id, name, description, price, stock, prescriptionOnly);
        medicines.add(medicine);
        System.out.println(medicines);
        return medicine;
    }

    public Medicine incrementStock(int idMedicine, int quantity) {
        Medicine medicine = getMedicine(idMedicine);
        medicine.incrementStock(quantity);
        return medicine;
    }

    public Medicine decrementStock(int idMedicine, int quantity) {
        Medicine medicine = getMedicine(idMedicine);
        medicine.decrementStock(quantity);
        return medicine;
    }

    public Medicine removeMedicine(int idMedicine) {
        Medicine medicine = getMedicine(idMedicine);
        medicines.remove(medicine);
        return medicine;
    }
}
