package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.model.Cart;
import com.lejito.epharma.model.Doctor;
import com.lejito.epharma.model.Medicine;
import com.lejito.epharma.model.Patient;
import com.lejito.epharma.model.Prescription;
import com.lejito.epharma.model.User;

import lombok.Data;

@Service
@Data
public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>(
                List.of(
                        new Patient(1, "John Doe", "john@example.com", "password123"),
                        new Patient(2, "Jane Smith", "jane@example.com", "password456"),
                        new User(3, "Admin User", "admin@example.com", "adminpass"),
                        new Doctor(4, "The Doctor", "doctor@example.com", "doctorpass", "Cardiology")));
    }

    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Patient) {
                patients.add((Patient) user);
            }
        }
        return patients;
    }

    User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

    public User getUser(int idUser) {
        for (User user : users) {
            if (user.getId() == idUser) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    private Patient getPatient(int idPatient) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId() == idPatient) {
                if (user instanceof Patient) {
                    return (Patient) user;
                } else {
                    throw new RuntimeException("User is not a patient");
                }
            }
        }
        throw new RuntimeException("User not found");
    }

    public Cart getCart(int idPatient) {
        Patient patient = getPatient(idPatient);
        return patient.getCart();
    }

    public void addMedicineToCart(int idPatient, Medicine medicine, int quantity) {
        Patient patient = getPatient(idPatient);
        patient.addMedicineToCart(medicine, quantity);
    }

    public void removeMedicineFromCart(int idPatient, Medicine medicine, int quantity) {
        Patient patient = getPatient(idPatient);
        patient.removeMedicineFromCart(medicine, quantity);
    }

    public void addPrescriptionToCart(int idPatient, Prescription prescription) {
        Patient patient = getPatient(idPatient);
        patient.addPrescriptionToCart(prescription);
    }

    public void removePrescriptionFromCart(int idPatient, Prescription prescription) {
        Patient patient = getPatient(idPatient);
        patient.removePrescriptionFromCart(prescription);
    }

    public Cart confirmCart(int idPatient) {
        Patient patient = getPatient(idPatient);
        return patient.confirmCart();
    }
}
