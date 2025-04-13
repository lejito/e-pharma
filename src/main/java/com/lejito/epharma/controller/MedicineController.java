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
import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.dto.UpdateMedicineStockDTO;
import com.lejito.epharma.service.MedicineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/")
    public ResponseDTO getMedicines() {
        var medicines = medicineService.getMedicines();
        return new ResponseDTO(true, "Medicines fetched successfully", medicines, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseDTO addMedicine(@Valid @RequestBody AddMedicineDTO body) {
        var medicine = medicineService.addMedicine(body.getName(), body.getDescription(), body.getPrice(),
                body.getStock(),
                body.isPrescriptionOnly());
        return new ResponseDTO(true, "Medicine added successfully", medicine, HttpStatus.CREATED);
    }

    @PutMapping("/increment/{idMedicine}")
    public ResponseDTO incrementStock(@PathVariable("idMedicine") int idMedicine,
            @Valid @RequestBody UpdateMedicineStockDTO body) {
        var medicine = medicineService.incrementStock(idMedicine, body.getQuantity());
        return new ResponseDTO(true, "Stock incremented successfully", medicine, HttpStatus.OK);
    }

    @PutMapping("/decrement/{idMedicine}")
    public ResponseDTO decrementStock(@PathVariable("idMedicine") int idMedicine,
            @Valid @RequestBody UpdateMedicineStockDTO body) {
        var medicine = medicineService.decrementStock(idMedicine, body.getQuantity());
        return new ResponseDTO(true, "Stock decremented successfully", medicine, HttpStatus.OK);
    }

    @DeleteMapping("/{idMedicine}")
    public ResponseDTO removeMedicine(@PathVariable("idMedicine") int idMedicine) {
        var medicine = medicineService.removeMedicine(idMedicine);
        return new ResponseDTO(true, "Medicine removed successfully", medicine, HttpStatus.OK);
    }
}
