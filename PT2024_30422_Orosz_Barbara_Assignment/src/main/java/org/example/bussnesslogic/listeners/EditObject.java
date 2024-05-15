package org.example.bussnesslogic.listeners;

import org.example.model.BLL.AbstractBLL;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.model.Client;
import org.example.model.Products;
import org.example.presentation.AbstractPanel;
import org.example.presentation.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Listener for editing all the current objects</p>
 * @param <T> type of objects current
 */
public class EditObject <T> implements ActionListener {

    private final AbstractPanel<T> objectPanel;
    private final AbstractBLL<T> objectBLL;
    /**
     * Default constructor
     * @param objectPanel GUI panel
     * @param objectBLL specific object queries
     */

    public EditObject(AbstractPanel<T> objectPanel, AbstractBLL<T> objectBLL) {
        this.objectPanel = objectPanel;
        this.objectBLL = objectBLL;
    }
    /**
     *  constructor when we need to edit a certain object
     * @param object GUI panel

     */
    public void EditOjectAgain(AbstractBLL<T> object)

    {
     //     this.objectPanel=object;
    }

    /**
     * <p>Tries to make changes to an object from database when the edit is pressed</p>
     * @param e current event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean returnedValue = false;
        T newObject = objectPanel.getFields();
        if(objectBLL.getClass() == ClientBLL.class) {
            returnedValue = ((ClientBLL) objectBLL).editClient((Client)newObject);
        } else if(objectBLL.getClass() == ProductsBLL.class) {
            returnedValue = ((ProductsBLL) objectBLL).editProduct((Products)newObject);
        }

        if(returnedValue) {
           Frame.showAlert("With succes edited the " + newObject.getClass().getSimpleName().toLowerCase() + "!");
        } else {
            Frame.showAlert("Unfortunately failed to edit the " + newObject.getClass().getSimpleName().toLowerCase() + "!");
        }
        objectPanel.removeAll();
        objectPanel.revalidate();
    }
}
