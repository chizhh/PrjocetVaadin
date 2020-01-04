package com.haulmont.testtask.web.clientForm.impl;

import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.web.clientForm.ClientEdit;
import com.vaadin.ui.Button;

public class ClientEditImpl extends ClientEdit {

    protected Client client;

    public ClientEditImpl() {}

    public Button buttonClick() {
        return save;
    }

    public void editForm(Client client) {
        this.client = client;
        this.name.setValue(client.getName());
        this.patronymic.setValue(client.getPatronymic());
        this.surname.setValue(client.getSurname());
        this.phone.setValue(client.getPhone());
    }

    public void clearForm() {
        this.name.setValue("");
        this.patronymic.setValue("");
        this.surname.setValue("");
        this.phone.setValue("");
    }

    public Client getClient(){
        Client client = new Client();
        client.setName(this.name.getValue());
        client.setSurname(this.surname.getValue());
        client.setPatronymic(this.patronymic.getValue());
        client.setPhone(this.phone.getValue());

        return client;
    }
}
