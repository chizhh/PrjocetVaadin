package com.haulmont.testtask.entity;

import java.util.Date;

public class Order {

    protected Long id;

    protected String description;

    protected Client client;

    protected Mechanic mechanic;

    protected Date dateCreature;

    protected Date dateOfWork;

    protected Double cost;

    protected String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Date getDateCreature() {
        return dateCreature;
    }

    public void setDateCreature(Date dateCreature) {
        this.dateCreature = dateCreature;
    }

    public Date getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(Date dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        if (status == null){
            return null;
        }
        return OrderStatus.fromId(status);
    }

    public void setStatus(OrderStatus status) {
        if (status == null){
            this.status = null;
        }
        this.status = status.getId();
    }
}
