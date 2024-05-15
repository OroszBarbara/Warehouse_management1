package org.example.bussnesslogic.validators;

import org.example.model.Products;
import org.example.presentation.Frame;
/**
 * <p>stock of a product  validator</p>
 */
public class Stock implements Validator<Products> {
    private static final int MinStock = 1;
    private static final int maxStock = 9999;
    private static final String ErrorMessage = "Product stock is is not in the specified terminal!";

    /**
     * <p>checksthat the given product has a valid stock</p>
     * @param crtProduct current product
     */
    @Override
    public void validate(Products crtProduct) {
        if (crtProduct.getStock() < MinStock || crtProduct.getStock() > maxStock) {
         Frame.showAlert(ErrorMessage);
            throw new IllegalArgumentException(ErrorMessage);
        }
    }
}