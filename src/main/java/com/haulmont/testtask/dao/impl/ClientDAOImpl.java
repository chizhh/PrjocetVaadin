package com.haulmont.testtask.dao.impl;

import com.haulmont.testtask.bd.BusinessLogic.Util;
import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public void add(Client client) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO CLIENT(NAME,SURNAME,PATRONYMIC,PHONE) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getPatronymic());
            ps.setString(4, client.getPhone());
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
    public List<Client> getAll() {
        Connection con = Util.connection();
        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT ID, NAME, SURNAME, PATRONYMIC, PHONE FROM CLIENT";
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getLong("ID"));
                client.setName(resultSet.getString("NAME"));
                client.setSurname(resultSet.getString("SURNAME"));
                client.setPatronymic(resultSet.getString("PATRONYMIC"));
                client.setPhone(resultSet.getString("PHONE"));

                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return clientList;
    }


    @Override
    public Client getById(Long id) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "SELECT ID,NAME,SURNAME,PATRONYMIC,PHONE FROM CLIENT WHERE ID=?";
        Client client = new Client();
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                client.setId(resultSet.getLong("ID"));
                client.setName(resultSet.getString("NAME"));
                client.setSurname(resultSet.getString("SURNAME"));
                client.setPatronymic(resultSet.getString("PATRONYMIC"));
                client.setPhone(resultSet.getString("PHONE"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            if (ps != null) {
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

    @Override
    public void update(Client client) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "UPDATE CLIENT SET NAME=?,SURNAME=?,PATRONYMIC=?,PHONE=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getSurname());
            preparedStatement.setString(3,client.getPatronymic());
            preparedStatement.setString(4,client.getPhone());
            preparedStatement.setLong(5,client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void remove(Client client) {
        Connection con = Util.connection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM CLIENT WHERE ID =?";

        try {
            ps=con.prepareStatement(sql);
            ps.setLong(1,client.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
