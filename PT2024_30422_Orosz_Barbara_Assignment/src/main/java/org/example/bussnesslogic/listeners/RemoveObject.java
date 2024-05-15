package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.presentation.AbstractPanel;
import org.example.presentation.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Listener for removing an object</p>
 * @param <T> type of object
 */
public class RemoveObject<T> implements ActionListener {

    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;
    private final String className;

    /**
     *  constructor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     * @param className class of object
     */
    public RemoveObject(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL, String className) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
        this.className = className;
    }
    /**
     * <p>Tries to remove an object when the remove is pressed</p>
     * @param e current event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int id = objectPanel.getIdField();
        if (Frame.showConfirm("Are you sure?") == 0) {
            if (className.compareTo("client") == 0) {
                if (((ClientBLL) objectBLL).removeClient(id)) {
                   Frame.showAlert("Successfully removed the client!");
                } else {
                   Frame.showAlert("Failed to remove the client!");
                }
            } else if (className.compareTo("product") == 0) {
                if (((ProductsBLL) objectBLL).removeProduct(id)) {
                   Frame.showAlert("Successfully removed the product!");
                } else {
                  Frame.showAlert("Failed to remove the product!");
                }
            }
            objectPanel.removeAll();
            objectPanel.revalidate();
        }
    }
}
