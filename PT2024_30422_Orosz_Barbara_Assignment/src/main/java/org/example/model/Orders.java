package org.example.model;

import java.util.Date;
/**
 * Model class for an entry in 'Orders' table
 */
public class Orders {
    private int id;
    private int client;
    private int product;
    private int amount;
    private int price;
    private Date date;

    /**
     * <p>constructor</p>
     */
    public Orders() { }

    /**
     * <p>Constructor with parameters</p>
     * @param client client id
     * @param product product id
     * @param amount product amount
     * @param price total price
     * @param date order date
     */
    public Orders(int client, int product, int amount, int price, Date date) {
        this.client = client;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    /**
     * <p>Constructor with parameters</p>
     * @param id order id
     * @param client client id
     * @param product product id
     * @param amount product amount
     * @param price total price
     * @param date order date
     */
    public Orders(int id, int client, int product, int amount, int price, Date date) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    /**
     * <p>Getter for orderid</p>
     * @return order id
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Setter for order id</p>
     * @param id new order id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <p>Getter for client id</p>
     * @return order client id
     */
    public int getClient() {
        return client;
    }


    /**
     * <p>Setter for client id</p>
     * @param client new order client id
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     * <p>Getter for product id</p>
     * @return order product id
     */
    public int getProduct() {
        return product;
    }

    /**
     * <p>Setter for product id</p>
     * @param product new order product id
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * <p>Getter for product amount</p>
     * @return order product amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * <p>Setter for product amount</p>
     * @param amount new order product amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    /**
     * <p>Getter for order price</p>
     * @return order price
     */

    public int getPrice() {
        return price;
    }

    /**
     * <p>Setter for order price</p>
     * @param price new order price
     */
    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * <p>Getter for order date</p>
     * @return order date
     */
    public Date getDate() {
        return date;
    }

    /**
     * <p>Setter for order date</p>
     * @param date new order date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
