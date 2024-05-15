package org.example.DAO;

import org.example.connection.ConnectionEnabler;
import org.example.model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 * Product specific queries
 */
public class ProductsDAO extends AbstractDAO<Products> {

    /**
     * <p>Returns a product with the given name</p>
     * @param productName name of the product to look for
     * @return product with the given name
     */
    public Products findByName(String productName) {
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        Products toReturn = null;
        try {
            dbConnection = ConnectionEnabler.getConnection();
            findStatement = dbConnection.prepareStatement(createFindByNameQuery());
            findStatement.setString(1, productName);
            resultSet = findStatement.executeQuery();
            toReturn = createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Product DAO: findById " + e.getMessage());
        } finally {
            ConnectionEnabler.closeAll(dbConnection, findStatement, resultSet);
        }
        return toReturn;
    }

    /**
     * <p> query for finding a product with name is created</p>
     * @return find query
     */
    private String createFindByNameQuery() {
        return "SELECT * FROM `Products` WHERE NAME = ?";
    }

    /**
     * <p>decrements the stock of a product by an amount in an order</p>
     * @param productId id of product
     * @param usedAmount amount used in order
     */
    public void decrementStock(int productId, int usedAmount)  {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = ConnectionEnabler.getConnection();
            preparedStatement = dbConnection.prepareStatement(createUpdateStockQuery());
            preparedStatement.setInt(1, usedAmount);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.log(Level.WARNING, "ProductDAO: decrementStock " + sqlEx.getMessage());
        } finally {
            ConnectionEnabler.closeAll(dbConnection, preparedStatement, null);
        }
    }

    /**
     * <p>Builds the query for updating the stock of a product</p>
     * @return stock update
     */
    private String createUpdateStockQuery() {
        return "UPDATE `Products` SET stock = stock - ? WHERE ID = ?";
    }

    /**
     * <p>queries the price for tbe current order</p>
     * @param productId  product
     * @return product price
     */
    public int getPrice(int productId) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalPrice;
        try {
            dbConnection = ConnectionEnabler.getConnection();
            preparedStatement = dbConnection.prepareStatement(createPriceQuery());
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            totalPrice = resultSet.getInt(1);
        } catch (SQLException sqlEx) {
            LOGGER.log(Level.WARNING, "ProductDAO: getPrice " + sqlEx.getMessage());
            totalPrice = -1;
        } finally {
                ConnectionEnabler.closeAll(dbConnection, preparedStatement, resultSet);
        }
        return totalPrice;
    }

    /**
     * <p>the query for selecting the price of a product is built</p>
     * @return price query
     */
    private String createPriceQuery() {
        return "SELECT price FROM `Products` WHERE id = ?";
    }
}
