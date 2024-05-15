package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.OrdersBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.presentation.AbstractPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Listener for searching an object</p>
 * @param <T> type of object
 */

public class SearchObject<T> implements ActionListener {

    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;
    private final String className;


    /**
     * constuctor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     * @param className type of object
     */
    public SearchObject(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL, String className) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
        this.className = className;
    }

    /**
     * <p> searches for an object when the search is  pressed</p>
     * @param e current event
     */
    @Override
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        T foundObject;
        int id = objectPanel.getIdField();
        if(className.compareTo("client") == 0) {
            foundObject = (T)((ClientBLL)objectBLL).searchClient(id);
        } else if(className.compareTo("product") == 0) {
            foundObject = (T)((ProductsBLL)objectBLL).searchProduct(id);
        } else {
            foundObject = (T)((OrdersBLL)objectBLL).searchOrder(id);
        }
        objectPanel.setFields(foundObject);
    }
}
