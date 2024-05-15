package org.example.DAO;

import org.example.connection.ConnectionEnabler;
import org.example.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 * Specific clients queries
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * <p>Returns  client with  name</p>
     * @param clientName name of the client
     * @return client with name
     */
    public Client findByName(String clientName) {
        ResultSet resultSet = null;
        Client toReturn = null;
        Connection dbConnection = null;
        PreparedStatement findStatement = null;

        try {
            dbConnection = ConnectionEnabler.getConnection();
            findStatement = dbConnection.prepareStatement(createFindByNameQuery());
            findStatement.setString(1, clientName);
            resultSet = findStatement.executeQuery();
            toReturn = createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Client DAO: findById " + e.getMessage());
        } finally {
            ConnectionEnabler.closeAll(dbConnection, findStatement, resultSet);
        }
        return toReturn;
    }


    /**
     * <p>Build the query for finding a client with a given name</p>
     * @return find query
     */
    private String createFindByNameQuery() {
        return "SELECT * FROM `Client` WHERE NAME = ?";
    }
}
