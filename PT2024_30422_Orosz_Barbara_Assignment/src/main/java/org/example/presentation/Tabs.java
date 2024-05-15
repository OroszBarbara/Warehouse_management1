package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * <p>The window for all of the operations possibel and all tables possible to work with</p>

 */
public class Tabs extends JPanel {

    private final String objectName;

    private JPanel mainPanelActivity;
    private JButton createObject;
    private JButton editObject;
    private JButton removeObject;
    private JButton searchObject;
    private JButton viewObjects;

    /**
     * <p>Default constructor</p>
     * @param NameObject type of object
     */
    public Tabs(String NameObject) {
        this.objectName = NameObject;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addTheButton();
        this.addActivityPanel();
    }



    /**
     * <p>adds the button for all operations like add  , edit ,remove ,search,viewall</p>

     */

    private void addTheButton() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.BLUE);
        buttonsPanel.setLayout(new GridLayout(1, 5));
        buttonsPanel.setMaximumSize(new Dimension(1000, 40));
        this.add(buttonsPanel);

        createObject = new JButton("New " + this.objectName);

        createObject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createObject.setBackground(Color.white);
        buttonsPanel.add(createObject);

        if (objectName.compareTo("order") != 0) {
            editObject = new JButton("Edit " + this.objectName);


            editObject.setCursor(new Cursor(Cursor.HAND_CURSOR));
            editObject.setBackground(Color.white);
            buttonsPanel.add(editObject);

            removeObject = new JButton("Remove " + this.objectName);

            removeObject.setCursor(new Cursor(Cursor.HAND_CURSOR));
            removeObject.setBackground(Color.white);
            buttonsPanel.add(removeObject);

            searchObject = new JButton("Search " + this.objectName);

            searchObject.setCursor(new Cursor(Cursor.HAND_CURSOR));
            searchObject.setBackground(Color.white);
            buttonsPanel.add(searchObject);
        }

        viewObjects = new JButton("View all " + this.objectName + "s");

        viewObjects.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewObjects.setBackground(Color.white);
        buttonsPanel.add(viewObjects);
    }

    /**
     * <p>These are the panel for each table CRUD operations</p>

     */
    private void addActivityPanel() {
        if (objectName.compareTo("client") == 0) {
            mainPanelActivity = new ClientPanel();
        } else if (objectName.compareTo("product") == 0) {
            mainPanelActivity = new ProductsPanel();
        } else {
            mainPanelActivity = new OrdersPanel();
        }
        mainPanelActivity.setPreferredSize(new Dimension(-1, 410));
        this.add(mainPanelActivity, BorderLayout.SOUTH);
    }

    /**
     *  listener for the create button
     * @param btnListener createListener
     */
    public void addCreateButtonListener(ActionListener btnListener) {
        createObject.addActionListener(btnListener);
    }

    /**
     * listener for the edit button
     * @param btnListener editListener
     */
    public void addEditButtonListener(ActionListener btnListener) {
        editObject.addActionListener(btnListener);
    }
    /**
     * listener for the remove button
     * @param btnListener removeListener
     */

    public void addRemoveButtonListener(ActionListener btnListener) {
        removeObject.addActionListener(btnListener);
    }
    /**
     *  listener for the search button
     * @param btnListener searchListener
     */
    public void addSearchButtonListener(ActionListener btnListener) {
        searchObject.addActionListener(btnListener);
    }
    /**
     *  listener for the view button
     * @param btnListener viewListener
     */

    public void addViewButtonListener(ActionListener btnListener) {
        viewObjects.addActionListener(btnListener);
    }

    /**
     * <p>Returns the current  panel in use</p>
     * @return panel
     */
    public JPanel getMainPanelActivity() {
        return mainPanelActivity;
    }
}
