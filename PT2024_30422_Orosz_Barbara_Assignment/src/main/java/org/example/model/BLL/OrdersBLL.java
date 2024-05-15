package org.example.model.BLL;

import org.example.DAO.OrdersDAO;
import org.example.bussnesslogic.validators.Amount;
import org.example.bussnesslogic.validators.ClientExists;
import org.example.bussnesslogic.validators.ProductExists;
import org.example.bussnesslogic.validators.Validator;
import org.example.model.Orders;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Various products  logic</p>
 */
public class OrdersBLL extends AbstractBLL<Orders> {
    private final List<Validator<Orders>> validators = new ArrayList<>();
    private final OrdersDAO orderDAO = new OrdersDAO();
    private final ProductsBLL productBLL = new ProductsBLL();
    private final ClientBLL clientBLL = new ClientBLL();

    /**
     * <p>constructor adding the validators</p>
     */
    public OrdersBLL() {
        validators.add(new ClientExists());
        validators.add(new ProductExists());
        validators.add(new Amount());
    }

    /**
     * <p>>validates an order and, if it is valid, inserts it into database</p>
     * @param newOrder order added to database
     * @return inserted order id
     */
    public int createOrder(Orders newOrder) {
        for (Validator<Orders> crtValidator : validators) {
            crtValidator.validate(newOrder);
        }
        newOrder.setPrice(newOrder.getAmount() * productBLL.getPrice(newOrder.getProduct()));
        productBLL.decrementStock(newOrder.getProduct(), newOrder.getAmount());
        int orderId = orderDAO.insert(newOrder);
        this.printBill(newOrder, orderId);
        return orderId;
    }

    /**
     * <p>Prints the bill of a placed order</p>
     * @param crtOrder our order
     * @param orderId id of the order
     */
    private void printBill(Orders crtOrder, int orderId) {
        ArrayList<String> fields = orderDAO.getFields(crtOrder);
        ArrayList<Object> values = orderDAO.getValues(crtOrder);

        StringBuilder orderBill = new StringBuilder("---- Order no. ");
        orderBill.append(orderId);
        orderBill.append(" ----\n");
        for (int i = 1; i < fields.size(); i++) {
            orderBill.append(fields.get(i));
            orderBill.append(": ");
            if(fields.get(i).compareTo("client") == 0) {
                orderBill.append(clientBLL.searchClient((int)values.get(i)).getName());
            } else if (fields.get(i).compareTo("product") == 0) {
                orderBill.append(productBLL.searchProduct((int)values.get(i)).getName());
            } else {
                orderBill.append(values.get(i));
            }
            orderBill.append("\n");
        }
        orderBill.append("----- THANK YOU! -----\n\n");

        try {
            FileWriter fileWriter = new FileWriter("bill.txt", true);
            fileWriter.write(orderBill.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p> searches an order in database</p>
     * @param orderId id of the order to be searched
     * @return the found order or null
     */
    public Orders searchOrder(int orderId) {
        return orderDAO.findById(orderId);
    }

    /**
     * <p>selects all the orders from database</p>
     * @return a list of all the orders existing in database
     */
    public ArrayList<Orders> viewOrders() {
        return orderDAO.findAll();
    }
}
