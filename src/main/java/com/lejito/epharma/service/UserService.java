package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.error.BadRequestError;
import com.lejito.epharma.error.NotFoundError;
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
        users = new ArrayList<>();
        users.add(new Patient(1, "John Doe", "john@example.com", "password123"));
        users.add(new Patient(2, "Jane Smith", "jane@example.com", "password456"));
        users.add(new User(3, "Admin User", "admin@example.com", "adminpass"));
        users.add(new Doctor(4, "The Doctor", "doctor@example.com", "doctorpass", "Cardiology"));
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

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new BadRequestError("Invalid email or password");
    }

    public User getUser(int idUser) {
        for (User user : users) {
            if (user.getId() == idUser) {
                return user;
            }
        }
        throw new NotFoundError("User not found");
    }

    private Patient getPatient(int idPatient) {
        User user = getUser(idPatient);
        if (user instanceof Patient) {
            return (Patient) user;
        }
        throw new NotFoundError("Patient not found");
    }

    public Cart getCart(int idPatient) {
        Patient patient = getPatient(idPatient);
        return patient.getCart();
    }

    public Cart addMedicineToCart(int idPatient, Medicine medicine, int quantity) {
        Patient patient = getPatient(idPatient);
        patient.addMedicineToCart(medicine, quantity);
        return patient.getCart();
    }

    public Cart removeMedicineFromCart(int idPatient, Medicine medicine, int quantity) {
        Patient patient = getPatient(idPatient);
        patient.removeMedicineFromCart(medicine, quantity);
        return patient.getCart();
    }

    public Cart addPrescriptionToCart(int idPatient, Prescription prescription) {
        Patient patient = getPatient(idPatient);
        patient.addPrescriptionToCart(prescription);
        return patient.getCart();
    }

    public Cart removePrescriptionFromCart(int idPatient, Prescription prescription) {
        Patient patient = getPatient(idPatient);
        patient.removePrescriptionFromCart(prescription);
        return patient.getCart();
    }

    public Cart confirmCart(int idPatient) {
        Patient patient = getPatient(idPatient);
        return patient.confirmCart();
    }
}
