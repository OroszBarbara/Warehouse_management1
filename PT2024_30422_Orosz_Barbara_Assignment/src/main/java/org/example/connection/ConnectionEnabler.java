package org.example.connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * <p>Class for connection to database</p>
 */
public class ConnectionEnabler {

    private static final Logger LOGGER = Logger.getLogger(ConnectionEnabler.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/warehouse";
    private static final String USER = "root";
    private static final String PASSWORD = "19731214Sam!";
    private static final ConnectionEnabler connectionInstance = new ConnectionEnabler();

    /**
     * <p> constructor prepares the connection driver</p>
     */
    private ConnectionEnabler() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException classEx) {
            classEx.printStackTrace();
        }
    }

    /**
     * <p> creates the connections to the database</p>
     * @return database connection
     */
    private Connection createConnection() {
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwable) {
            LOGGER.log(Level.WARNING, "[DB] An error occurred while trying to connect to the database!");
            throwable.printStackTrace();
        }
        return dbConnection;
    }

    /**
     * <p>Returns instance of database connection object</p>
     * @return connection to the database
     */
    public static Connection getConnection() {
        return connectionInstance.createConnection();
    }

    /**
     * <p>Closes the database related objects</p>
     * @param dbConnection database connection
     * @param crtStatement connection statement
     * @param resultSet connection result set
     */
    public static void closeAll(Connection dbConnection, Statement crtStatement, ResultSet resultSet) {
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
            if (crtStatement != null) {
                crtStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwable) {
            LOGGER.log(Level.WARNING, "An error occurred while trying to close the Connection, Statement or ResultSet!");
            throwable.printStackTrace();
        }
    }
}
