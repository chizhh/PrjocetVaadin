package com.haulmont.testtask.web.clientForm.impl;

import com.haulmont.testtask.dao.impl.ClientDAOImpl;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.web.clientForm.ClientBrowse;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import java.util.List;

public class ClientBrowseImpl  extends ClientBrowse implements View {

    ClientEditImpl clientEditImpl;

    List<Client> clients;
    BeanItemContainer<Client> container;

    public ClientBrowseImpl() {
        upGrid();
        create.addClickListener(clickEvent -> {
            createRow();
        });
        update.addClickListener(clickEvent -> {
            updateRow();
        });
        delete.addClickListener(clickEvent -> {
            deleteRow();
        });
    }

    protected void updateRow() {
        formUpdateCreate();
    }

    protected void deleteRow() {
        Client client = new Client();
        client.setId(((Client)grid.getSelectedRow()).getId());
        new ClientDAOImpl().remove(client);
        upGrid();
    }

    protected void createRow() {
        formEditCreate();
    }

    protected void upGrid() {
        clients = new ClientDAOImpl().getAll();
        container =   new BeanItemContainer<>(Client.class, clients);
        grid.setContainerDataSource(container);

        grid.setColumnOrder("id","name","surname","patronymic","phone");
        grid.getColumn("id").setHeaderCaption("ID");
        grid.getColumn("name").setHeaderCaption("Имя");
        grid.getColumn("surname").setHeaderCaption("Фамилия");
        grid.getColumn("patronymic").setHeaderCaption("Отчество");
        grid.getColumn("phone").setHeaderCaption("Телефон");
    }


    protected void formEditCreate(){
        final Window window = createWindows();
        clientEditImpl.buttonClick().addClickListener(clickEvent -> {
            window.close();
            Client client = clientEditImpl.getClient();
            new ClientDAOImpl().add(client);
            upGrid();
        });
        window.setContent(clientEditImpl);
    }

    protected void formUpdateCreate(){
        final Window window = createWindows();
        Client client = (Client) grid.getSelectedRow();
        clientEditImpl.editForm(client);
        clientEditImpl.buttonClick().addClickListener(clickEvent -> {
            window.close();
            Client clientNew = clientEditImpl.getClient();
            clientNew.setId(client.getId());
            new ClientDAOImpl().update(clientNew);
            upGrid();
        });
        window.setContent(clientEditImpl);
    }

    protected Window createWindows(){
        final Window window = new Window();
        window.setModal(true);
        window.center();
        UI.getCurrent().addWindow(window);
        clientEditImpl = new ClientEditImpl();
        return window;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}