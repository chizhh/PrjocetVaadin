package com.haulmont.testtask.dao;


import com.haulmont.testtask.entity.Mechanic;
import java.util.List;


public interface MechanicDAO {

    void add(Mechanic mechanic);

    List<Mechanic> getAll();

    Mechanic getById(Long id);

    void update(Mechanic mechanic);

    void remove(Mechanic mechanic);
}
