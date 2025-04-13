package com.lejito.epharma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lejito.epharma.dto.CartMedicineDTO;
import com.lejito.epharma.dto.CartPrescriptionDTO;
import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.service.MedicineService;
import com.lejito.epharma.service.PrescriptionService;
import com.lejito.epharma.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final UserService userService;
    private final MedicineService medicineService;
    private final PrescriptionService prescriptionService;

    public CartController(UserService userService, MedicineService medicineService,
            PrescriptionService prescriptionService) {
        this.userService = userService;
        this.medicineService = medicineService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/{idPatient}")
    public ResponseDTO getCart(@PathVariable("idPatient") int idPatient) {
        var cart = userService.getCart(idPatient);
        return new ResponseDTO(true, "Cart fetched successfully", cart, HttpStatus.OK);
    }

    @PostMapping("/medicine/{idPatient}")
    public ResponseDTO addMedicine(@PathVariable("idPatient") int idPatient, @Valid @RequestBody CartMedicineDTO body) {
        var medicine = medicineService.getMedicine(body.getIdMedicine());
        userService.addMedicineToCart(idPatient, medicine, body.getQuantity());
        var cart = userService.getCart(idPatient);
        return new ResponseDTO(true, "Medicine added to cart successfully", cart, HttpStatus.OK);
    }

    @DeleteMapping("/medicine/{idPatient}")
    public ResponseDTO removeMedicine(@PathVariable("idPatient") int idPatient,
            @Valid @RequestBody CartMedicineDTO body) {
        var medicine = medicineService.getMedicine(body.getIdMedicine());
        userService.removeMedicineFromCart(idPatient, medicine, body.getQuantity());
        var cart = userService.getCart(idPatient);
        return new ResponseDTO(true, "Medicine removed from cart successfully", cart, HttpStatus.OK);
    }

    @PostMapping("/prescription/{idPatient}")
    public ResponseDTO addPrescription(@PathVariable("idPatient") int idPatient,
            @Valid @RequestBody CartPrescriptionDTO body) {
        var prescription = prescriptionService.getPrescription(body.getIdPrescription());
        userService.addPrescriptionToCart(idPatient, prescription);
        var cart = userService.getCart(idPatient);
        return new ResponseDTO(true, "Prescription added to cart successfully", cart, HttpStatus.OK);
    }

    @DeleteMapping("/prescription/{idPatient}")
    public ResponseDTO removePrescription(@PathVariable("idPatient") int idPatient,
            @Valid @RequestBody CartPrescriptionDTO body) {
        var prescription = prescriptionService.getPrescription(body.getIdPrescription());
        userService.removePrescriptionFromCart(idPatient, prescription);
        var cart = userService.getCart(idPatient);
        return new ResponseDTO(true, "Prescription removed from cart successfully", cart, HttpStatus.OK);
    }

    @PostMapping("/confirm/{idPatient}")
    public ResponseDTO confirmCart(@PathVariable("idPatient") int idPatient) {
        var cart = userService.confirmCart(idPatient);
        return new ResponseDTO(true, "Cart confirmed successfully", cart, HttpStatus.OK);
    }
}
