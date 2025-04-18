package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.error.NotFoundError;
import com.lejito.epharma.model.Medicine;
import com.lejito.epharma.model.Patient;
import com.lejito.epharma.model.Prescription;

import lombok.Data;

@Service
@Data
public class PrescriptionService {
    private List<Prescription> prescriptions;

    public PrescriptionService() {
        prescriptions = new ArrayList<>();
    }

    public List<Prescription> getPrescriptions(int idPatient) {
        List<Prescription> patientPrescriptions = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            if (prescription.getIdPatient() == idPatient) {
                patientPrescriptions.add(prescription);
            }
        }
        return patientPrescriptions;
    }

    public Prescription getPrescription(int idPrescription) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getId() == idPrescription) {
                return prescription;
            }
        }
        throw new NotFoundError("Prescription not found");
    }

    public Prescription addPrescription(Patient patient, Medicine medicine, int quantity) {
        int id = prescriptions.size() + 1;
        Prescription prescription = new Prescription(id, patient.getId(), medicine, quantity);
        prescriptions.add(prescription);
        return prescription;
    }

    public Prescription changeAvailability(int idPrescription, boolean available) {
        Prescription prescription = getPrescription(idPrescription);
        prescription.setAvailable(available);
        return prescription;
    }

    public Prescription removePrescription(int idPrescription) {
        Prescription prescription = getPrescription(idPrescription);
        prescriptions.remove(prescription);
        return prescription;
    }
}
