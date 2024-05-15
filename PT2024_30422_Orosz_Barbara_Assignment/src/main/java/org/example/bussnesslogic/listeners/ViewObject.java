package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.OrdersBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.presentation.AbstractPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewObject<T> implements ActionListener {
    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;
    private final String className;

    /**
     * constructor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     * @param className type of objects
     */
    public ViewObject(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL, String className) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
        this.className = className;
    }
    /**
     * <p>When the view button is pressed, shows a table of objects</p>
     * @param e current event
     */
    @Override
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        ArrayList<T> objectList;
        if (className.compareTo("client") == 0) {
            objectList = (ArrayList<T>)((ClientBLL)objectBLL).viewClients();
        } else if (className.compareTo("product") == 0) {
            objectList = (ArrayList<T>)((ProductsBLL)objectBLL).viewProducts();
        } else {
            objectList =(ArrayList<T>)((OrdersBLL)objectBLL).viewOrders();
        }
        objectPanel.adddObjectsInTable(objectList);
    }
}
