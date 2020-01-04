package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.entity.Order;
import com.haulmont.testtask.entity.OrderStatus;

import java.util.List;

public interface OrderDAO {

    void add(Order order);

    List<Order> getAll();

    Order getById(Client clientId, Mechanic mechanicId);

    void update(Order order);

    void remove(Order order);

    List<Order> getByFilter(OrderStatus status, Client client, String description);
}
