package com.haulmont.testtask.web.orderForm.impl;

import com.haulmont.testtask.dao.impl.ClientDAOImpl;
import com.haulmont.testtask.dao.impl.OrderDAOImpl;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Order;
import com.haulmont.testtask.entity.OrderStatus;
import com.haulmont.testtask.web.orderForm.OrderBrowse;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import java.util.List;

public class OrderBrowseImpl extends OrderBrowse {

    protected OrderEditImpl orderEdit;
    protected List<Order> orders;
    protected BeanItemContainer<Order> container;

    //filter fild
    protected ComboBox statusFilter;
    protected ComboBox clientFilter;
    protected TextField description;

    public OrderBrowseImpl() {
        upGrid();
        upFilter();
        filretBtn.addClickListener(clickEvent -> {
            upGrid((OrderStatus) statusFilter.getValue(), (Client) clientFilter.getValue(), description.getValue());
        });
        create.addClickListener(clickEvent -> {
            formEditCreate();
        });
        update.addClickListener(clickEvent -> {
            formUpdateCreate();
        });
        delete.addClickListener(clickEvent -> {
            deleteRow();
        });

    }

    protected void upFilter() {
        this.description = new TextField();
        this.description.setCaption("Описание");
        this.filterColumn.addComponent(this.description);

        this.statusFilter = new ComboBox();
        this.statusFilter.setCaption("Статус");
        this.statusFilter.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ID);
        this.statusFilter.addItems(OrderStatus.values());
        this.filterColumn.addComponent(this.statusFilter);

        this.clientFilter = new ComboBox();
        this.clientFilter.setCaption("Клиент");
        this.clientFilter.setItemCaptionMode(AbstractSelect.ItemCaptionMode.ID);
        List<Client> clients = new ClientDAOImpl().getAll();
        this.clientFilter.addItems(clients);
        this.filterColumn.addComponent(this.clientFilter);


    }

    protected void deleteRow() {
        Order order = new Order();
        order.setId(((Order)grid.getSelectedRow()).getId());
        new OrderDAOImpl().remove(order);
        upGrid();
    }

    protected void upGrid(OrderStatus status, Client client, String description) {
        orders = new OrderDAOImpl().getByFilter(status, client, description);
        container =   new BeanItemContainer<>(Order.class, orders);
        grid.setContainerDataSource(container);
    }

    protected void upGrid() {
        orders = new OrderDAOImpl().getAll();
        container =   new BeanItemContainer<>(Order.class, orders);
        grid.setContainerDataSource(container);
    }

    protected void upGrid(Client client) {

    }

    protected void formEditCreate(){
        final Window window = createWindows();
        orderEdit.buttonClicks().addClickListener(clickEvent -> {
            window.close();
            Order order = orderEdit.getOrder();
            new OrderDAOImpl().add(order);
            upGrid();
        });
        window.setContent(orderEdit);
    }

    protected void formUpdateCreate(){
        final Window window = createWindows();
        Order order = (Order) grid.getSelectedRow();
        orderEdit.editForm(order);
        orderEdit.buttonClicks().addClickListener(clickEvent -> {
            window.close();
            Order orderNew = orderEdit.getOrder();
            orderNew.setId(order.getId());
            new OrderDAOImpl().update(orderNew);
            upGrid();
        });
        window.setContent(orderEdit);
    }

    protected Window createWindows(){
        final Window window = new Window();
        window.setModal(true);
        window.center();
        UI.getCurrent().addWindow(window);
        orderEdit = new OrderEditImpl();
        return window;
    }
}
