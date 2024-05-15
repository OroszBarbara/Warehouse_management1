package org.example.presentation;

import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Products;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * <p> panel for orders</p>
 */
public class OrdersPanel extends AbstractPanel<Orders> {
    private final ArrayList<JLabel> fieldsLabel = new ArrayList<>();
    private final ArrayList<Object> fieldsInput = new ArrayList<>();

    /**
     * <p>constructor</p>
     */
    public OrdersPanel() {
        fieldsInput.add(Frame.createComboBox());
        fieldsInput.add(Frame.createComboBox());
        fieldsInput.add(Frame.createInput());

        for (Field value : Orders.class.getDeclaredFields()) {
            fieldsLabel.add(Frame.createLabel(value.getName().toUpperCase() + ": "));
        }
    }

    /**
     * <p> panel for showing order's info</p>
     * @param hasId boolean value representing the needing of showing the id of object or not
     */
    @Override
    @SuppressWarnings("unchecked")

    public void showObjectInfo(boolean hasId) {
        this.addClientsToList();
        this.addProductsToList();

        int fieldsNb = hasId ? fieldsLabel.size() : fieldsLabel.size() - 3;
        midPanel.setLayout(new GridLayout(fieldsNb, 2, -150, 10));
        midPanel.setMaximumSize(new Dimension(650, fieldsNb * 55));
        midPanel.setBorder(new EmptyBorder(20, 100, 20, 100));
        this.add(midPanel);

        for (int i = 0; i < fieldsInput.size(); i++) {
            midPanel.add(fieldsLabel.get(i + 1));
            if (fieldsInput.get(i).getClass().getSimpleName().compareTo("JTextField") == 0) {
                midPanel.add((JTextField) fieldsInput.get(i));
            } else {
                midPanel.add((JComboBox<String>) fieldsInput.get(i));
            }
        }
    }

    /**
     * <p>Adds clients into a  list with drop</p>
     */
    @SuppressWarnings("unchecked")

    public void addClientsToList() {
        ((JComboBox<String>) fieldsInput.get(0)).removeAllItems();
        ArrayList<Client> clientList = new ClientBLL().viewClients();
        for (Client crtClient : clientList) {
            ((JComboBox<String>) fieldsInput.get(0)).addItem(crtClient.getName());
        }
    }

    /**
     * <p>Adds products into a  list with drop</p>
     */
    @SuppressWarnings("unchecked")

    public void addProductsToList() {
        ((JComboBox<String>) fieldsInput.get(1)).removeAllItems();
        ArrayList<Products> productList = new ProductsBLL().viewProducts();
        for (Products crtProduct : productList) {
            ((JComboBox<String>) fieldsInput.get(1)).addItem(crtProduct.getName());
        }
    }

    /**
     * <p>Adds objects into a table</p>
     */
    @Override

    public void adddObjectsInTable(ArrayList<Orders> objectsList) {
        tableEntries.setRowCount(0);
        for (Orders crtOrder : objectsList) {
            Object[] values = this.getValues(crtOrder);
            tableEntries.addRow(values);
        }
    }

    /**
     * <p>Makes an order objects from the input fields</p>
     * @return a newly created order
     */
    @Override
    @SuppressWarnings("unchecked")
    public Orders getFields() {
        Orders object = null;
        if(((JTextField)fieldsInput.get(2)).getText().trim().isBlank()) {
            Frame.showAlert("Fill in all the fields!");
        } else {
            object = new Orders();
            object.setClient(new ClientBLL().searchClient(Objects.requireNonNull(((JComboBox<String>) fieldsInput.get(0)).getSelectedItem()).toString()).getId());
            object.setProduct(new ProductsBLL().searchProduct(Objects.requireNonNull(((JComboBox<String>) fieldsInput.get(1)).getSelectedItem()).toString()).getId());
            object.setAmount(Integer.parseInt(((JTextField)fieldsInput.get(2)).getText()));
            object.setDate(new Date(System.currentTimeMillis()));
        }
        return object;
    }

    /**
     * <p>Returns a list of an object's values</p>
     * @param object object where the values will be taken from
     * @return list of object's values
     */
    private Object[] getValues(Orders object) {
        ArrayList<Object> values = new ArrayList<>();
        values.add(object.getId());
        values.add(new ClientBLL().searchClient(object.getClient()).getName());
        values.add(new ProductsBLL().searchProduct(object.getProduct()).getName());
        values.add(object.getAmount());
        values.add(object.getPrice());
        values.add(object.getDate());
        return values.toArray();
    }
}