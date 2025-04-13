package com.lejito.epharma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lejito.epharma.model.Item;
import com.lejito.epharma.model.Order;
import com.lejito.epharma.model.Patient;
import com.lejito.epharma.model.Prescription;

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
            if (order.getPatient().getId() == idPatient) {
                patientOrders.add(order);
            }
        }
        return patientOrders;
    }

    public Order getOrder(int idOrder) {
        for (Order order : orders) {
            if (order.getId() == idOrder) {
                return order;
            }
        }
        throw new RuntimeException("Order not found");
    }

    public void addOrder(Patient patient, List<Item> items, List<Prescription> prescriptions, float totalPrice) {
        int id = orders.size() + 1;
        Order order = new Order(id, patient, items, prescriptions, totalPrice);
        orders.add(order);
    }

    public void approveOrder(int idOrder) {
        Order order = getOrder(idOrder);
        order.approve();
    }

    public void rejectOrder(int idOrder) {
        Order order = getOrder(idOrder);
        order.reject();
    }

    public void removeOrder(int idOrder) {
        Order order = getOrder(idOrder);
        orders.remove(order);
    }
}
