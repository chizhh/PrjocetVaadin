package com.haulmont.testtask;

import com.haulmont.testtask.web.clientForm.impl.ClientBrowseImpl;
import com.haulmont.testtask.web.mechanicForm.impl.MechanicBrowseImp;
import com.haulmont.testtask.web.orderForm.impl.OrderBrowseImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        TabSheet tabSheet = createTabSheet();
        tabSheet.setSizeFull();
        layout.addComponent(tabSheet);

        setContent(layout);
    }

    protected TabSheet createTabSheet() {
        TabSheet tabsheet = new TabSheet();

        VerticalLayout tabClient = new VerticalLayout();
        tabClient.addComponent(new ClientBrowseImpl());
        tabClient.setCaption("Клиент");
        tabsheet.addTab(tabClient);

        VerticalLayout tabMechanic = new VerticalLayout();
        tabMechanic.addComponent(new MechanicBrowseImp());
        tabMechanic.setCaption("Механик");
        tabsheet.addTab(tabMechanic);

        VerticalLayout tabOrder = new VerticalLayout();
        tabOrder.addComponent(new OrderBrowseImpl());
        tabOrder.setCaption("Заказ");
        tabsheet.addTab(tabOrder);

        return tabsheet;
    }


}