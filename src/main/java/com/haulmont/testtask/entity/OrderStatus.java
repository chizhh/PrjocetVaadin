package com.haulmont.testtask.entity;

public enum OrderStatus {
    SCHEDULED("scheduled", "Запланирован"),
    ACCEPTEDBYCUSTOMER("acceptedByCustomer", "Принят клиентом"),
    COMPLETED("completed", "Выполнен");

    String id;

    String name;

    OrderStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static OrderStatus fromId(String id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
