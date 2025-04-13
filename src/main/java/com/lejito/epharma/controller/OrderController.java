package com.lejito.epharma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseDTO getOrders() {
        var orders = orderService.getOrders();
        return new ResponseDTO(true, "Orders fetched successfully", orders, HttpStatus.OK);
    }

    @GetMapping("/{idOrder}")
    public ResponseDTO getOrder(@PathVariable("idOrder") int idOrder) {
        var order = orderService.getOrder(idOrder);
        return new ResponseDTO(true, "Order fetched successfully", order, HttpStatus.OK);
    }

    @GetMapping("/patient/{idPatient}")
    public ResponseDTO getOrdersByPatient(@PathVariable("idPatient") int idPatient) {
        var orders = orderService.getOrders(idPatient);
        return new ResponseDTO(true, "Orders fetched successfully", orders, HttpStatus.OK);
    }

    @PutMapping("/approve/{idOrder}")
    public ResponseDTO approveOrder(@PathVariable("idOrder") int idOrder) {
        var order = orderService.approveOrder(idOrder);
        return new ResponseDTO(true, "Order approved successfully", order, HttpStatus.OK);
    }

    @PutMapping("/reject/{idOrder}")
    public ResponseDTO rejectOrder(@PathVariable("idOrder") int idOrder) {
        var order = orderService.rejectOrder(idOrder);
        return new ResponseDTO(true, "Order rejected successfully", order, HttpStatus.OK);
    }

    @DeleteMapping("/{idOrder}")
    public ResponseDTO removeOrder(@PathVariable("idOrder") int idOrder) {
        var order = orderService.removeOrder(idOrder);
        return new ResponseDTO(true, "Order removed successfully", order, HttpStatus.OK);
    }
}
