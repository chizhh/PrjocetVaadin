package com.haulmont.testtask.dao.impl;

import com.haulmont.testtask.bd.BusinessLogic.Util;
import com.haulmont.testtask.dao.OrderDAO;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.entity.Order;
import com.haulmont.testtask.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void add(Order order) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO ORDER(DESCRIPTION,ID_CLIENT,ID_MECHANIC,DATECREATURE,DATEOFWORK,VALUE,STATUS) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, order.getDescription());
            ps.setLong(2, order.getClient().getId());
            ps.setLong(3, order.getMechanic().getId());
            ps.setDate(4, new java.sql.Date(order.getDateCreature().getTime()));
            ps.setDate(5, new java.sql.Date(order.getDateOfWork().getTime()));
            ps.setDouble(6, order.getCost());
            ps.setString(7, order.getStatus().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Order> getAll() {

        Connection con = Util.connection();
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT ID, DESCRIPTION,ID_CLIENT,ID_MECHANIC,DATECREATURE,DATEOFWORK,VALUE,STATUS FROM ORDER";
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Order order = new Order();
                ClientDAOImpl client = new ClientDAOImpl();
                MechanicDAOimpl mechanic = new MechanicDAOimpl();
                order.setId(resultSet.getLong("ID"));
                order.setDescription(resultSet.getString("DESCRIPTION"));
                order.setClient(client.getById(resultSet.getLong("ID_CLIENT")));
                order.setMechanic(mechanic.getById(resultSet.getLong("ID_MECHANIC")));
                order.setDateCreature(resultSet.getDate("DATECREATURE"));
                order.setDateOfWork(resultSet.getDate("DATEOFWORK"));
                order.setCost(resultSet.getDouble("VALUE"));
                order.setStatus(OrderStatus.fromId(resultSet.getString("STATUS")));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderList;
    }


    @Override
    public Order getById(Client client, Mechanic mechanic) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "SELECT  ID, DESCRIPTION,ID_CLIENT,ID_MECHANIC,DATECREATURE,DATEOFWORK,VALUE,STATUS  FROM ORDER WHERE ID_CLIENT=?,ID_MECHANIC=? ";
        Order order = new Order();
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, client.getId());
            ps.setLong(2, mechanic.getId());
            ResultSet resultSet = ps.executeQuery();
            order.setId(resultSet.getLong("ID"));
            order.setDescription(resultSet.getString(" DESCRIPTION"));
            order.setClient(new ClientDAOImpl().getById(resultSet.getLong("ID_CLIENT")));
            order.setClient(new ClientDAOImpl().getById(resultSet.getLong("ID_MECHANIC")));
            order.setDateCreature(resultSet.getDate("DATECREATURE"));
            order.setDateCreature(resultSet.getDate("DATEOFWORK"));
            order.setCost(resultSet.getDouble("VALUE"));
            order.setStatus(OrderStatus.fromId(resultSet.getString("STATUS")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {

            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return order;

    }

    @Override
    public void update(Order order) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "UPDATE ORDER SET DESCRIPTION=?,ID_CLIENT=?,ID_MECHANIC=?,DATECREATURE=?,DATEOFWORK=?,VALUE=?,STATUS=?  WHERE ID=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, order.getDescription());
            preparedStatement.setLong(2, order.getClient().getId());
            preparedStatement.setLong(3, order.getMechanic().getId());
            preparedStatement.setDate(4, new java.sql.Date(order.getDateCreature().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(order.getDateOfWork().getTime()));
            preparedStatement.setDouble(6, order.getCost());
            preparedStatement.setString(7, order.getStatus().getId());
            preparedStatement.setLong(8, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void remove(Order order) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM ORDER WHERE ID =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public List<Order> getByFilter(OrderStatus status, Client client, String description) {
        if(description.isEmpty() && client == null && status == null){
            return getAll();
        }

        Connection con = Util.connection();
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT ID, DESCRIPTION,ID_CLIENT,ID_MECHANIC,DATECREATURE,DATEOFWORK,VALUE,STATUS FROM ORDER " + getFilter(status, client, description);
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Order order = new Order();
                ClientDAOImpl clientFind = new ClientDAOImpl();
                MechanicDAOimpl mechanic = new MechanicDAOimpl();
                order.setId(resultSet.getLong("ID"));
                order.setDescription(resultSet.getString("DESCRIPTION"));
                order.setClient(clientFind.getById(resultSet.getLong("ID_CLIENT")));
                order.setMechanic(mechanic.getById(resultSet.getLong("ID_MECHANIC")));
                order.setDateCreature(resultSet.getDate("DATECREATURE"));
                order.setDateOfWork(resultSet.getDate("DATEOFWORK"));
                order.setCost(resultSet.getDouble("VALUE"));
                order.setStatus(OrderStatus.fromId(resultSet.getString("STATUS")));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderList;
    }

    protected String getFilter(OrderStatus status, Client client, String description) {
        StringBuilder filter = new StringBuilder();
        filter.append("where ");
        if (status != null) {
            filter.append(String.format("STATUS like '%s' ", status.getId()));
            if (client != null) {
                filter.append(" and ");
            }
        }

        if (client != null) {
            filter.append(String.format("ID_CLIENT = %s ", client.getId()));
            if (!description.isEmpty()) {
                filter.append(" and ");
            }
        }

        if (!description.isEmpty()) {
            filter.append(String.format("DESCRIPTION like '%s' ", "%" + description + "%"));
        }

        return filter.toString();
    }
}
