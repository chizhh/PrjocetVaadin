package com.haulmont.testtask.web.orderForm.impl;

import com.haulmont.testtask.dao.impl.ClientDAOImpl;
import com.haulmont.testtask.dao.impl.MechanicDAOimpl;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.entity.Order;
import com.haulmont.testtask.entity.OrderStatus;
import com.haulmont.testtask.web.orderForm.OrderEdit;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;

import java.util.List;

public class OrderEditImpl extends OrderEdit {

    protected Order order;
    protected ComboBox mechanic;
    protected ComboBox status;
    protected ComboBox client;

    public OrderEditImpl() {
        super();
        this.status = new ComboBox();
        this.status.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ID);
        this.status.addItems(OrderStatus.values());
        this.statusBoxH.addComponent(this.status);

        this.mechanic = new ComboBox();
        this.mechanic.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ID);
        List<Mechanic> mechanics = new MechanicDAOimpl().getAll();
        this.mechanic.addItems(mechanics);
        this.mechanicBlocH.addComponent(this.mechanic);

        this.client = new ComboBox();
        this.client.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ID);
        List<Client> clients = new ClientDAOImpl().getAll();
        this.client.addItems(clients);
        this.clientBoxH.addComponent(this.client);

    }

    public Button buttonClicks() {
        return save;
    }

    public void editForm(Order order) {
        this.order = order;
        this.description.setValue(order.getDescription());
        this.dateCreature.setValue(order.getDateCreature());
        this.dateOfWork.setValue(order.getDateOfWork());
        this.cost.setValue(order.getCost().toString());
        this.status.select(order.getStatus());
        this.mechanic.setValue(order.getMechanic());
        this.client.setValue(order.getClient());
    }

    public void clearForm() {
        this.description.clear();
        this.dateCreature.clear();
        this.dateOfWork.clear();
        this.cost.clear();
        this.status.clear();
        this.mechanic.clear();
        this.client.clear();
    }

    public Order getOrder() {
        Order order = new Order();
        order.setDescription(this.description.getValue());
        order.setDateCreature(this.dateCreature.getValue());
        order.setDateOfWork(this.dateOfWork.getValue());
        order.setCost(Double.parseDouble(this.cost.getValue()));
        order.setClient((Client) client.getValue());
        order.setMechanic((Mechanic) mechanic.getValue());
        order.setStatus((OrderStatus) status.getValue());
        return order;
    }
}
