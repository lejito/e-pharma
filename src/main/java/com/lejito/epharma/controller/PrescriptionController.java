package com.lejito.epharma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lejito.epharma.dto.AddMedicineDTO;
import com.lejito.epharma.dto.PrescriptionAvailabilityDTO;
import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.service.MedicineService;
import com.lejito.epharma.service.PrescriptionService;
import com.lejito.epharma.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final UserService userService;
    private final MedicineService medicineService;

    public PrescriptionController(PrescriptionService prescriptionService, UserService userService,
            MedicineService medicineService) {
        this.prescriptionService = prescriptionService;
        this.userService = userService;
        this.medicineService = medicineService;
    }

    @GetMapping("/")
    public ResponseDTO getPrescriptions() {
        var prescriptions = prescriptionService.getPrescriptions();
        return new ResponseDTO(true, "Prescriptions fetched successfully", prescriptions, HttpStatus.OK);
    }

    @GetMapping("/{idPrescription}")
    public ResponseDTO getPrescription(@PathVariable("idPrescription") int idPrescription) {
        var prescription = prescriptionService.getPrescription(idPrescription);
        return new ResponseDTO(true, "Prescription fetched successfully", prescription, HttpStatus.OK);
    }

    @GetMapping("/patient/{idPatient}")
    public ResponseDTO getPrescriptionsByPatient(@PathVariable("idPatient") int idPatient) {
        var prescriptions = prescriptionService.getPrescriptions(idPatient);
        return new ResponseDTO(true, "Prescriptions fetched successfully", prescriptions, HttpStatus.OK);
    }

    @PostMapping("/{idPatient}")
    public ResponseDTO addPrescription(@PathVariable("idPatient") int idPatient,
            @Valid @RequestBody AddMedicineDTO body) {
        var patient = userService.getPatient(idPatient);
        var medicine = medicineService.getMedicine(body.getIdMedicine());
        var prescription = prescriptionService.addPrescription(patient, medicine, body.getQuantity());
        return new ResponseDTO(true, "Prescription added successfully", prescription, HttpStatus.CREATED);
    }

    @PutMapping("/{idPrescription}")
    public ResponseDTO changeAvailability(@PathVariable("idPrescription") int idPrescription,
            @Valid @RequestBody PrescriptionAvailabilityDTO body) {
        var prescription = prescriptionService.changeAvailability(idPrescription, body.isAvailable());
        return new ResponseDTO(true, "Prescription availability updated successfully", prescription, HttpStatus.OK);
    }

    @DeleteMapping("/{idPrescription}")
    public ResponseDTO removePrescription(@PathVariable("idPrescription") int idPrescription) {
        var prescription = prescriptionService.removePrescription(idPrescription);
        return new ResponseDTO(true, "Prescription removed successfully", prescription, HttpStatus.OK);
    }
}
