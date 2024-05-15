package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.OrdersBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Products;
import org.example.presentation.AbstractPanel;
import org.example.presentation.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Listener for creating a new object</p>
 * @param <T> type of object
 */
public class ObjectListener<T> implements ActionListener {
    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;

    /**
     * constructor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     */
    public ObjectListener(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
    }



    /**
     * <p>Tries to insert an object into database when create is  pressed</p>
     * @param e current event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int id;
        T newObject = objectPanel.getFields();
        if(objectBLL.getClass() == ClientBLL.class) {
            id = ((ClientBLL) objectBLL).createClient((Client)newObject);
            ((Client)newObject).setId(id);
        } else if(objectBLL.getClass() == ProductsBLL.class) {
            id = ((ProductsBLL) objectBLL).createProduct((Products)newObject);
            ((Products)newObject).setId(id);
        } else {
            id = ((OrdersBLL) objectBLL).createOrder((Orders)newObject);
            ((Orders)newObject).setId(id);
        }

        if (id == -1) {
            Frame.showAlert("Failed to add new " + newObject.getClass().getSimpleName().toLowerCase() + "!");
        } else {
            Frame.showAlert("Successfully added a new " + newObject.getClass().getSimpleName().toLowerCase() + "!");
        }

        objectPanel.removeAll();
        objectPanel.revalidate();
    }
}
