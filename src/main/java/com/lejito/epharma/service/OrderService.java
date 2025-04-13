package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.error.NotFoundError;
import com.lejito.epharma.model.Cart;
import com.lejito.epharma.model.Order;
import com.lejito.epharma.model.Patient;

import lombok.Data;

@Service
@Data
public class OrderService {
    private List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders(int idPatient) {
        List<Order> patientOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getIdPatient() == idPatient) {
                patientOrders.add(order);
            }
        }

        if (patientOrders.isEmpty()) {
            throw new NotFoundError("No orders found for this patient");
        }

        return patientOrders;
    }

    public Order getOrder(int idOrder) {
        for (Order order : orders) {
            if (order.getId() == idOrder) {
                return order;
            }
        }
        throw new NotFoundError("Order not found");
    }

    public Order addOrder(Patient patient, Cart cart) {
        int id = orders.size() + 1;
        Order order = new Order(id, patient.getId(), cart.getItems(), cart.getPrescriptions(), cart.calculatePrice());
        orders.add(order);
        return order;
    }

    public Order approveOrder(int idOrder) {
        Order order = getOrder(idOrder);
        order.approve();
        return order;
    }

    public Order rejectOrder(int idOrder) {
        Order order = getOrder(idOrder);
        order.reject();
        return order;
    }

    public Order removeOrder(int idOrder) {
        Order order = getOrder(idOrder);
        orders.remove(order);
        return order;
    }
}
