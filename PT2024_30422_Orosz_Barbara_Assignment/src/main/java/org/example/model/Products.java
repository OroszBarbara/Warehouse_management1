package org.example.model;
/**
 * <p>Model class for an entry in 'Products' table</p>
 */
public class Products {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stock;

    /**
     * <p> constructor</p>
     */
    public Products() {
    }

    /**
     * Constructor with parameters
     * @param name product name
     * @param description product description
     * @param price product price
     * @param stock product stock
     */
    public Products(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor with parameters
     * @param id product id
     * @param name product name
     * @param description product description
     * @param price product price
     * @param stock product stock
     */
    public Products(int id, String name, String description, int price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public String getDescription() {
        return description;
    }

    /**
     * <p>Setter for description</p>
     * @param description new description of product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>Getter for price</p>
     * @return price of product
     */
    public int getPrice() {
        return price;
    }

    /**
     * <p>Setter for price</p>
     * @param price new price of product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * <p>Getter for stock</p>
     * @return stock of product
     */
    public int getStock() {
        return stock;
    }

    /**
     * <p>Setter for stock</p>
     * @param stock new stock of product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * <p>Getter for id</p>
     * @return id of product
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Setter for id</p>
     * @param id new id of product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <p>Getter for name</p>
     * @return name of product
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for name</p>
     * @param name new name of product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Getter for description</p>
     * @return description of product
     */

}
