package DAO;

import database.MySQL;
import exception.ClientException;
import interfaces.ClientInterface;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientImplementation implements ClientInterface {
    private final Connection con = MySQL.getInstance();

    public ClientImplementation() {
    }

    /**
     * @param client
     * @return
     * @throws ClientException
     */
    @Override
    public Client get(Client client) throws ClientException {
        return null;
    }

    /**
     * @param client
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean Add(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO client (Name, Email) VALUES (?, ?)");
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
            e.printStackTrace();
            throw new ClientException(e.getMessage());
        }
    }

    /**
     * @param client
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean Update(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE client SET Name = ?, Email = ?");
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            return (stmt.executeUpdate() > 0);
        }catch (SQLException e){
            e.printStackTrace();
            throw new ClientException(e.getMessage());
        }
    }

    /**
     * @param client
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean Delete(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM client WHERE Id = ?");
            stmt.setInt(1, client.getId());
            return (stmt.executeUpdate() > 0);
        }catch (SQLException e){
            e.printStackTrace();
            throw new ClientException(e.getMessage());
        }
    }

    public Integer GetId(Client client) throws ClientException {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT Id FROM client WHERE Email = ?");
            stmt.setString(1, client.getEmail());
            ResultSet result = stmt.executeQuery();
            if (result.next())
                return result.getInt("Id");
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw new ClientException(e.getMessage());
        }
    }
}
