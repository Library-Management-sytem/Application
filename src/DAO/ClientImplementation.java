package DAO;

import database.Datasource;
import database.MySQL;
import exception.BookException;
import exception.ClientException;
import interfaces.ClientInterface;
import models.Book;
import models.Client;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.List;

public class ClientImplementation implements ClientInterface {
    private final Connection con = MySQL.getInstance();

    public ClientImplementation() {
    }

    @Override
    public Boolean get(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM client WHERE Email = ?", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getEmail());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                client.setId(result.getInt("Id"));
                client.setName(result.getString("Name"));
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new ClientException();
        }
    } // Done

    @Override
    public List<Client> get() throws ClientException {
        try {
            QueryRunner run = new QueryRunner(Datasource.getMySQLDataSource());
            ResultSetHandler<List<Client>> h = new BeanListHandler<>(Client.class);
            return run.query("SELECT * FROM client", h);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ClientException(e.getMessage());
        }
    }

    @Override
    public Boolean Add(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO client (Name, Email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            int result = stmt.executeUpdate();
            if  (result > 0){
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        client.setId(generatedKeys.getInt(1));
                        return true;
                    }
                    else {
                        throw new SQLException("Creating client failed, no ID obtained.");
                    }
                }
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new ClientException(e.getMessage());
        }
    } // Done

    @Override
    public Boolean Update(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE client SET Name = ?, Email = ?");
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            return (stmt.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new ClientException(e.getMessage());
        }
    } // Done

    @Override
    public Boolean Delete(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM client WHERE Id = ?");
            stmt.setInt(1, client.getId());
            return (stmt.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new ClientException(e.getMessage());
        }
    } // Done

}
