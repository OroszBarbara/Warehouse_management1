package org.example.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
/**
 * <p>Main application frame</p>
 */
public class Frame extends JFrame{


    private static final int appWidth = 1000;
    private static final int appHeight = 600;
    private static final int imageWidth = 150;
    private static final int imageHeight = 450;

    private final JPanel contentPanel = new JPanel();
    private final JPanel imagePanel = new JPanel();
    private final JPanel mainPanel = new JPanel();
    private static final  JTabbedPane panelContainer = new JTabbedPane();

    /**
     * <p>Default constructor ,sets up the frame</p>
     */
    public Frame() {
        this.setMinimumSize(new Dimension(appWidth, appHeight));
        this.setResizable(false);
        this.setTitle("Warehouse-app");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.ContentPanelSettingUp();

        this.MainPanelAddingComponents();
    }

    /**
     * <p>Sets up content for the panel</p>
     */
    private void ContentPanelSettingUp() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.CYAN);
        this.setContentPane(contentPanel);
    }

    /**
     * <p>Adds the components like the title, sets up the panel</p>
     */
    private void MainPanelAddingComponents() {
        this.PanelSetUp();
        this.AppTitle();
        this.PanelTabsAddons();
    }
    /**
     * <p>Sets up panel</p>
     */
    private void PanelSetUp() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        contentPanel.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * <p>Adds the title</p>
     */

    private void AppTitle() {
        JLabel appTitle = new JLabel("Please select an operation");
        appTitle.setFont(new Font("New Times Roman", Font.PLAIN, 40));
        appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(appTitle);
    }

    /**
     * <p>Adds the 3 windows</p>
     */
    private void PanelTabsAddons() {
        panelContainer.addTab("Client", new Tabs("client"));
        panelContainer.addTab("Product", new Tabs("product"));
       panelContainer.addTab("Order", new Tabs("order"));
        mainPanel.add(panelContainer);
    }

    /**
     * <p>Creates labels</p>
     * * @param message label message
     *     * @return label
     */
    public static JLabel createLabel(String message) {
        JLabel newLabel = new JLabel(message);
        newLabel.setHorizontalAlignment(SwingConstants.LEFT);
        newLabel.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        return newLabel;
    }

    /**
     * <p>Creates input fields</p>
     * * @param message label message
     *     * @return jtextfield
     */

    public static JTextField createInput() {
        JTextField newInput = new JTextField(50);
        newInput.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        return newInput;
    }

    /**
     * <p>Creates combo boxes for selection</p>
     * * @param message label message
     *     * @return combobox
     */
    public static JComboBox<String> createComboBox() {
        JComboBox<String> newComboBox = new JComboBox<>();
        newComboBox.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        return newComboBox;
    }

    /**
     * <p>Puts the title in bold</p>
     *
     */
    public static void initializeTitle(JLabel panelTitle) {
        panelTitle.setAlignmentX(CENTER_ALIGNMENT);
        panelTitle.setFont(new Font("New Times Roman", Font.BOLD, 25));
        panelTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
    }
    /**
     * <p>initializes the buttons</p>
     *
     */

    public static void initializeButton(JButton crtButton) {
        crtButton.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        crtButton.setAlignmentX(CENTER_ALIGNMENT);
        crtButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * <p>shows alerts</p>
     * * @param alert message
     *
     */
    public static void showAlert(String message) {
        JLabel alertMessage = new JLabel(message);
        alertMessage.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, alertMessage);
    }

    /**
     * <p>Shows Pop for confirmation</p>
     * * @param  message
     *     * @return message
     */
    public static int showConfirm(String message) {
        JLabel confirmMessage = new JLabel(message);
        confirmMessage.setFont(new Font("New Times Roman", Font.PLAIN, 20));
        return JOptionPane.showConfirmDialog(null, confirmMessage, "Are you sure?", JOptionPane.YES_NO_OPTION);
    }
    /**
     * <p>shows panel container</p>

     *     * @return panel
     */
    public static JTabbedPane getPanelContainer() {
        return panelContainer;
    }


    /**
     * <p>listener to panel to remove all components</p>
     * @param crtListener current listener
     */
    public void addChangeTabListener(ChangeListener crtListener) {panelContainer.addChangeListener(crtListener);}
}