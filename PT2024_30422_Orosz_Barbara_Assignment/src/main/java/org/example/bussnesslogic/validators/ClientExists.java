package org.example.bussnesslogic.validators;

import org.example.model.BLL.ClientBLL;
import org.example.model.Orders;
import org.example.presentation.Frame;
/**
 * <p>Order client validator</p>
 */
public class ClientExists implements Validator<Orders> {
    private static final String message = "Client does not exist or maybe a typo!";
    private final ClientBLL clientBLL = new ClientBLL();
    /**
     * <p>cheacks than an order's client exists in database</p>
     * @param crtOrder current order
     */
    @Override
    public void validate(Orders crtOrder) {
        if (clientBLL.searchClient(crtOrder.getClient()) == null) {
           Frame.showAlert(message);
            throw new IllegalArgumentException(message);
        }
    }
}