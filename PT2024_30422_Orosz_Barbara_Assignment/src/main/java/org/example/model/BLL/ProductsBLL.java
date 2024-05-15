package org.example.model.BLL;

import org.example.DAO.ProductsDAO;
import org.example.bussnesslogic.validators.Price;
import org.example.bussnesslogic.validators.Stock;
import org.example.bussnesslogic.validators.Validator;
import org.example.model.BLL.AbstractBLL;
import org.example.model.Products;

import java.util.ArrayList;
import java.util.List;
/**
 * <p> orders business logic</p>
 */
public class ProductsBLL  extends AbstractBLL<Products> {
    private final ProductsDAO productDAO = new ProductsDAO();
    private final List<Validator<Products>> validators = new ArrayList<>();

    /**
     * <p>constructor adding the validators</p>
     */
    public ProductsBLL() {
        validators.add(new Price());
        validators.add(new Stock());
    }

    /**
     * <p>validates a product and, if it is valid, makes call to insert it into database</p>
     * @param newProduct product added to database
     * @return inserted product id
     */
    public int createProduct(Products newProduct) {
        for (Validator<Products> crtValidator : validators) {
            crtValidator.validate(newProduct);
        }
        return productDAO.insert(newProduct);
    }

    /**
     * <p> validates a product and, if it is valid, makes call to update an existing product from database</p>
     * @param toUpdate product to be updated
     * @return boolean value representing success or fail
     */
    public boolean editProduct(Products toUpdate) {
        for (Validator<Products> crtValidator : validators) {
            crtValidator.validate(toUpdate);
        }
        return productDAO.update(toUpdate);
    }

    /**
     * <p>searchs a product in database</p>
     * @param productId id of the product to be searched
     * @return the found product or null
     */
    public Products searchProduct(int productId) {
        return productDAO.findById(productId);
    }

    /**
     * <p>searchs a product in database</p>
     * @param productName name of the product to be searched
     * @return the found product or null
     */
    public Products searchProduct(String productName) {
        return productDAO.findByName(productName);
    }

    /**
     * <p>removes a product from database</p>
     * @param productId id of the product to be removed
     * @return boolean value representing success or fail
     */
    public boolean removeProduct(int productId) {
        return productDAO.remove(productId);
    }

    /**
     * <p>selects all the products from database</p>
     * @return a list of all the products existing in database
     */
    public ArrayList<Products> viewProducts() {
        return productDAO.findAll();
    }

    /**
     * <p>Returns the price of the product</p>
     * @param productId desired product
     * @return price product
     */
    public int getPrice(int productId) { return productDAO.getPrice(productId); }

    /**
     * <p>Decrements the stock of a product with an  certain amount</p>
     * @param productId desired product
     * @param usedAmount amount the stock will be decremented with
     */
    public void decrementStock(int productId, int usedAmount) { productDAO.decrementStock(productId, usedAmount);}
}
