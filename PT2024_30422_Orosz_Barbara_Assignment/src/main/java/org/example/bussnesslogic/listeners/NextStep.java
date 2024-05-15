package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.OrdersBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.presentation.AbstractPanel;
import org.example.presentation.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Listener for showing object id input field</p>
 * @param <T> type of object
 */
public class NextStep<T> implements ActionListener {
    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;
    private final String currentPanel;
    private final String nextPanel;
    /**
     *  constructor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     * @param currentPanel current panel
     * @param nextPanel next panel
     */
    public NextStep(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL, String currentPanel, String nextPanel) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
        this.currentPanel = currentPanel;
        this.nextPanel = nextPanel;
    }

    /**
     * <p>Tries to find an object with a given id when pressed</p>
     * @param e current event
     */
    @Override
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        if (objectPanel.getIdField() == -1) {
            Frame.showAlert("Field is empty!");
            return;
        }

        T foundObject;
        if (currentPanel.compareTo("client") == 0) {
            foundObject = (T) ((ClientBLL) objectBLL).searchClient(objectPanel.getIdField());
        } else if (currentPanel.compareTo("product") == 0) {
            foundObject = (T) ((ProductsBLL) objectBLL).searchProduct(objectPanel.getIdField());
        } else {
            foundObject = (T) ((OrdersBLL) objectBLL).searchOrder(objectPanel.getIdField());
        }

        if (foundObject == null) {
            Frame.showAlert(currentPanel.toUpperCase().charAt(0) + currentPanel.substring(1) + " not found!");
            objectPanel.removeAll();
            objectPanel.revalidate();
            return;
        }

        if (nextPanel.compareTo("edit") == 0) {
            objectPanel.showEditObjectPanel(foundObject);
        } else if (nextPanel.compareTo("remove") == 0) {
            objectPanel.showRemoveObjectPanel(foundObject);
        } else {
            objectPanel.showSearchObjectPanel(foundObject);
        }
    }
}
