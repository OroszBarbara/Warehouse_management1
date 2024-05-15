package org.example.presentation;

import org.example.bussnesslogic.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Hre is the PopUp where the specfications and button to the warehouse are</p>

 */
public class PopUp extends JFrame {

    /**
     * <p>Default contructor for initializng it</p>

     */
    public PopUp() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Create a panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;


        JPanel labelsPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        labelsPanel.setBackground(Color.CYAN);
        labelsPanel.add(new JLabel(  "                     Some information       "));
        labelsPanel.add(new JLabel("*The age must be between 18 and 101"));
        labelsPanel.add(new JLabel("*The amount of a product must be at least 1 and at most 999"));
        labelsPanel.add(new JLabel("*The price of a product must be at least 1 and at most 9999"));
        labelsPanel.add(new JLabel("*The email must contain special characters"));


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        JButton button = new JButton("Enter Warehouse");
        button.setBackground(Color.WHITE);

        button.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(button);
        /**
         * <p>The actionListener that opens the Frame window when opened</p>

         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                Controller mainController = new Controller(frame);
                frame.setVisible(true);



            }
        });
        mainPanel.add(Box.createVerticalGlue(), gbc);

        mainPanel.add(buttonPanel, gbc);

        mainPanel.add(labelsPanel, gbc);

        mainPanel.setBackground(Color.CYAN);
        add(mainPanel);

        setVisible(true);
    }
}
