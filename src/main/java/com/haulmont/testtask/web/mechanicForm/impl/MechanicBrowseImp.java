package com.haulmont.testtask.web.mechanicForm.impl;

import com.haulmont.testtask.dao.impl.MechanicDAOimpl;
import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.web.mechanicForm.MechanicBrowse;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import java.util.List;

public class MechanicBrowseImp extends MechanicBrowse implements View {


    MechanicEditImp mechanicEditImp;
    List<Mechanic> mechanics;
    BeanItemContainer<Mechanic> container;

    public MechanicBrowseImp() {
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
        Mechanic mechanic = new Mechanic();
        mechanic.setId(((Mechanic)grid.getSelectedRow()).getId());
        new MechanicDAOimpl().remove(mechanic);
        upGrid();
    }

    protected void createRow() {
        formEditCreate();
    }

    protected void upGrid() {
        mechanics = new MechanicDAOimpl().getAll();
        container =   new BeanItemContainer<>(Mechanic.class, mechanics);
        grid.setContainerDataSource(container);
        grid.setColumnOrder("id","name","surname","patronymic","payment");
    }

    protected void formEditCreate(){
        final Window window = createWindows();
        mechanicEditImp.buttonClicks().addClickListener(clickEvent -> {
            window.close();
            Mechanic mechanic = mechanicEditImp.getMechanic();
            new MechanicDAOimpl().add(mechanic);
            upGrid();
        });
        window.setContent(mechanicEditImp);
    }

    protected void formUpdateCreate(){
        final Window window = createWindows();
        Mechanic mechanic = (Mechanic) grid.getSelectedRow();
        mechanicEditImp.editForm(mechanic);
        mechanicEditImp.buttonClicks().addClickListener(clickEvent -> {
            window.close();
            Mechanic mechanicNew = mechanicEditImp.getMechanic();
            mechanicNew.setId(mechanic.getId());
            new MechanicDAOimpl().update(mechanicNew);
            upGrid();
        });
        window.setContent(mechanicEditImp);
    }

    protected Window createWindows(){
        final Window window = new Window();
        window.setModal(true);
        window.center();
        UI.getCurrent().addWindow(window);
        mechanicEditImp = new MechanicEditImp();
        return window;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
