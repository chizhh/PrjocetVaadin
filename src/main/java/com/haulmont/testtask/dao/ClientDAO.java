package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Client;

import java.util.List;

public interface ClientDAO {

    void add(Client client);

    List<Client> getAll() ;

    Client getById(Long id);

    void update(Client client);

    void remove(Client client);


}
