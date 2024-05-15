package org.example.presentation;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.ArrayList;

/**
 * <p>Abstract  panel for CRUD operations on tables client ,products ,orders</p>
 * @param <T> type of the object
 */
public abstract class AbstractPanel<T> extends JPanel {
    private final Class<T> type;
    private final JLabel panelTitle = new JLabel();
    private final ArrayList<JLabel> fieldsLabel = new ArrayList<>();
    private final ArrayList<JTextField> fieldsInput = new ArrayList<>();
    private final JTable objectTable = new JTable();
    protected final JPanel midPanel = new JPanel();

    protected DefaultTableModel tableEntries;
    private final JButton performOperationBtn = new JButton();
    private final JButton nextStepBtn = new JButton();


    @SuppressWarnings("unchecked") /**
     *  constructor
     */
    public AbstractPanel() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.setUp();
    }

    /**
     * <p>Sets up the panel</p>
     */
    private void setUp() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Frame.initializeTitle(panelTitle);
        Frame.initializeButton(performOperationBtn);
        Frame.initializeButton(nextStepBtn);

        ArrayList<String> tableHeader = new ArrayList<>();
        for (Field value : type.getDeclaredFields()) {
            fieldsLabel.add(Frame.createLabel(value.getName().toUpperCase() + ": "));
            fieldsInput.add(Frame.createInput());
            tableHeader.add(value.getName().toUpperCase());
        }

        tableEntries = new DefaultTableModel(tableHeader.toArray(), 0);
    }


    /**
     * <p>object creation object</p>
     */
    public void showCreateObjectPanel() {
        this.removeAll();

        panelTitle.setText("Create new " + type.getSimpleName().toLowerCase());
        panelTitle.setBackground(Color.white);
        this.add(panelTitle);

        this.showObjectInfo(false);
        this.enableFields(true);
        performOperationBtn.setBackground(Color.white);
        performOperationBtn.setText("Add");
        performOperationBtn.setBackground(Color.white);
        this.add(performOperationBtn);

        this.repaint();
        this.revalidate();
    }


    /**
     * <p>insert id panel</p>
     */
    public void showObjectByIdPanel() {
        this.removeAll();

        panelTitle.setText("Enter the id of the desired " + type.getSimpleName().toLowerCase());
        this.add(panelTitle);

        JPanel midPanel = new JPanel(new GridLayout(1, 2, -150, 10));
        midPanel.setMaximumSize(new Dimension(650, 75));
        midPanel.setBorder(new EmptyBorder(20, 100, 20, 100));
        this.add(midPanel);

        midPanel.add(fieldsLabel.get(0));
        midPanel.add(fieldsInput.get(0));
        nextStepBtn.setBackground(Color.white);
        nextStepBtn.setText("OK");
        nextStepBtn.setBackground(Color.white);
        this.add(nextStepBtn);
        this.enableFields(true);

        this.repaint();
        this.revalidate();
    }

    /**
     * <p>editing an object panel</p>
     * @param object object to be edited
     */
    public void showEditObjectPanel(T object) {
        this.removeAll();

        panelTitle.setText("Edit " + type.getSimpleName().toLowerCase());
        panelTitle.setBackground(Color.DARK_GRAY);
        this.add(panelTitle);

        this.showObjectInfo(false);
        this.setFields(object);
        this.enableFields(true);
        performOperationBtn.setBackground(Color.white);
        performOperationBtn.setText("Edit");
        performOperationBtn.setBackground(Color.white);
        this.add(performOperationBtn);

        this.repaint();
        this.revalidate();
    }

    /**
     * <p>panel used for removing an object</p>
     * @param object object to be removed
     */
    public void showRemoveObjectPanel(T object) {
        this.removeAll();

        panelTitle.setText("Remove " + type.getSimpleName().toLowerCase());
        panelTitle.setBackground(Color.white);
        this.add(panelTitle);

        this.showObjectInfo(true);
        this.setFields(object);
        this.enableFields(false);
        performOperationBtn.setBackground(Color.white);
        performOperationBtn.setText("Remove");
        performOperationBtn.setBackground(Color.white);
        this.add(performOperationBtn);

        this.repaint();
        this.revalidate();
    }

    /**
     * <p>panel used for searching for an object</p>
     * @param object object to be removed
     */
    public void showSearchObjectPanel(T object) {
        this.removeAll();

        panelTitle.setText("Search " + type.getSimpleName().toLowerCase());
        panelTitle.setBackground(Color.white);
        this.add(panelTitle);

        this.showObjectInfo(true);
        this.setFields(object);
        this.enableFields(false);

        this.repaint();
        this.revalidate();
    }

    /**
     * <p>panel used for viewing all</p>

     */
    public void showViewObjectsPanel() {
        this.removeAll();

        panelTitle.setText("Viewing all " + type.getSimpleName().toLowerCase() + "s");
        panelTitle.setBackground(Color.white);
        this.add(panelTitle);

        this.initializeTable();
        JScrollPane tableScroll = new JScrollPane(objectTable);
        this.add(tableScroll);

        this.repaint();
        this.revalidate();
    }

    /**
     * <p>Initializes the tables</p>

     */
    private void initializeTable() {
        objectTable.setModel(tableEntries);
        objectTable.setRowHeight(25);
        objectTable.getTableHeader().setReorderingAllowed(false);
        objectTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
        objectTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        objectTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        objectTable.setEnabled(false);
    }

    /**
     * <p>Adds the objects in table</p>
     * @param objectsList list of objects
     */
    public void adddObjectsInTable(ArrayList<T> objectsList) {
        tableEntries.setRowCount(0);
        for (T crtObject : objectsList) {
            Object[] values = this.getValues(crtObject);
            tableEntries.addRow(values);
        }
    }
    /**
     * <p>shows the objects in table</p>
     * @param hasId list of objects
     */

    public void showObjectInfo(boolean hasId) {
        int fieldsNb = hasId ? fieldsLabel.size() : fieldsLabel.size() - 1;
        midPanel.setLayout(new GridLayout(fieldsNb, 2, -150, 10));
        midPanel.setMaximumSize(new Dimension(650, fieldsNb * 50));
        midPanel.setBorder(new EmptyBorder(20, 100, 20, 100));
        this.add(midPanel);

        for (int i = 0; i < fieldsLabel.size(); i++) {
            if (hasId || i > 0) {
                midPanel.add(fieldsLabel.get(i));
                midPanel.add(fieldsInput.get(i));
            }
        }
    }

    /**
     * <p>Enables or disables the input based on a given condition</p>
     * @param isEnabled input status
     */
    public void enableFields(boolean isEnabled) {
        for (JTextField field : fieldsInput) {
            field.setEnabled(isEnabled);
        }
    }

    /**
     * <p>Returns the id taken from input field</p>
     * @return id field
     */
    public int getIdField() {
        if (fieldsInput.get(0).getText().trim().isBlank()) {
            return -1;
        } else {
            return Integer.parseInt(fieldsInput.get(0).getText());
        }
    }

    /**
     * <p>Returns the object created by the input field</p>
     * @return object
     */
    public T getFields() {
        T object = null;
        try {
            object = type.getDeclaredConstructor().newInstance();
            Field[] field = type.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field[i].getName(), type);
                Method method = propertyDescriptor.getWriteMethod();
                if(i > 0 && fieldsInput.get(i).getText().trim().isBlank()) {
                   Frame.showAlert("Fill in all the fields!");
                    return null;
                } else {
                    if (method.toString().endsWith("(int)")) {
                        try {
                            method.invoke(object, Integer.parseInt(fieldsInput.get(i).getText()));
                        } catch (Exception e) {
                            method.invoke(object, 0);
                        }
                    } else if (method.toString().endsWith("(java.sql.Date)")) {
                        method.invoke(object, new Date(System.currentTimeMillis()));
                    } else {
                        method.invoke(object, (fieldsInput.get(i)).getText());
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IntrospectionException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * <p>Puts object values in input fields </p>
     * @param object object where the values will be taken from
     */
    public void setFields(T object) {
        Object[] values = this.getValues(object);
        for (int i = 0; i < fieldsLabel.size(); i++) {
            fieldsInput.get(i).setText(values[i] + "");
        }
    }

    /**
     * <p>Cleares all the information about a field </p>

     */
    public void clearFields() {
        for (int i = 0; i < fieldsLabel.size(); i++) {
            if (fieldsInput.get(i).getClass() == JTextField.class) {
                fieldsInput.get(i).setText("");
            }
        }
    }

    /**
     * <p>list of an object's values is returned</p>
     * @param object object where the values will be taken from
     * @return list of object's values
     */
    private Object[] getValues(T object) {
        Object[] values = new Object[type.getDeclaredFields().length];
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                values[i] = fields[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    /**
     * <p> event listener for button</p>
     * @param btnListener event listener
     */
    public void addNextStepBtnListener(ActionListener btnListener) {
        nextStepBtn.addActionListener(btnListener);
    }

    /**
     * <p>event listener for button</p>
     * @param btnListener event listener
     */
    public void addPerformOperationBtnListener(ActionListener btnListener) {
        performOperationBtn.addActionListener(btnListener);
    }

    /**
     * <p>Removes all buttons' event listeners</p>
     */
    public void removeAllEventListeners() {
        for (ActionListener crtListener : nextStepBtn.getActionListeners()) {
            nextStepBtn.removeActionListener(crtListener);
        }
        for (ActionListener crtListener : performOperationBtn.getActionListeners()) {
            performOperationBtn.removeActionListener(crtListener);
        }
    }
}