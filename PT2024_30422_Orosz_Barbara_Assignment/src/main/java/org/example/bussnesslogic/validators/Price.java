package org.example.bussnesslogic.validators;

import org.example.model.Products;
import org.example.presentation.Frame;
/**
 * <p>Pprice for a product validator</p>
 */
public class Price implements Validator<Products> {
    private static final int minPrice = 1;
    private static  int MaxPrice = 9999;
    private static final String MessageError = "Product price is invalid!";

    private boolean changeEnabled=false;

    /**
     * <p>checks that the given product has a valid price</p>
     * @param crtProduct current product
     */
    @Override
    public void validate(Products crtProduct) {
        if (crtProduct.getPrice() < minPrice || crtProduct.getPrice() > MaxPrice) {
        Frame.showAlert(MessageError);
            throw new IllegalArgumentException(MessageError);
        }
    }

    public void changePricequalifications(Products products) {
        if (products.getPrice() > 999 && changeEnabled == true) {
            MaxPrice = products.getPrice();
        }


    }
}
