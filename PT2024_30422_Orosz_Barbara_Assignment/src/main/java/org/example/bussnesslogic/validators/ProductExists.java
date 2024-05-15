package org.example.bussnesslogic.validators;

import org.example.model.BLL.ProductsBLL;
import org.example.model.Orders;
import org.example.presentation.Frame;

/**
 * <p>Order product validator</p>
 */
public class ProductExists implements Validator<Orders> {
    private static final String ErrorMessage = "Product does not exist , try again!";
    private final ProductsBLL productBLL = new ProductsBLL();

    /**
     * <p>Echeacks that an  order's product exists in database</p>
     * @param ordercriteria current order
     */
    @Override
    public void validate(Orders ordercriteria) {
        if (productBLL.searchProduct(ordercriteria.getProduct()) == null) {
           Frame.showAlert(ErrorMessage);
            throw new IllegalArgumentException(ErrorMessage);
        }
    }
}
