package org.example.bussnesslogic.validators;

import org.example.model.BLL.ProductsBLL;
import org.example.model.Orders;
import org.example.presentation.Frame;
/**
 * <p>Order amount validator</p>
 */
public class Amount implements Validator<Orders>{


    private static final int minAmount = 1;
    private static  int maxAmount = 999;
    private static final String MessageInvalid = "Invalid amount!";
    private static final String MessageTooFew = "Not enough products in stock!";
    private final ProductsBLL productBLL = new ProductsBLL();

    private boolean changeEnabled=false;
    /**
     * <p>cheacks that  the the amount wanted is a finite one and there are enough products in stock when placing an order</p>
     * @param crtOrder current order
     */
    @Override
    public void validate(Orders crtOrder) {
        if (crtOrder.getAmount() < minAmount || crtOrder.getAmount() > maxAmount) {
           Frame.showAlert(MessageInvalid);
            throw new IllegalArgumentException(MessageInvalid);
        } else if(productBLL.searchProduct(crtOrder.getProduct()).getStock() < crtOrder.getAmount()) {
           Frame.showAlert(MessageTooFew);
            throw new IllegalArgumentException(MessageTooFew);
        }
    }
    /**
     * <p>changes he amount limits</p>
     * @param orders current order
     */
    public void changeQuantityqualifications(Orders orders) {
        if (orders.getAmount() > 999 && changeEnabled == true) {
            maxAmount = orders.getAmount();
        }


    }
}
