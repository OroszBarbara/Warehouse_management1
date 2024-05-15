package org.example.bussnesslogic;



import org.example.bussnesslogic.listeners.*;
import org.example.model.BLL.ClientBLL;
import org.example.model.BLL.OrdersBLL;
import org.example.model.BLL.ProductsBLL;
import org.example.presentation.Frame;
import org.example.presentation.Tabs;
import org.example.presentation.ClientPanel;
import org.example.presentation.OrdersPanel;
import org.example.presentation.ProductsPanel;

/**
 * <p>Class used for adding button event listeners</p>
 */
public class Controller {
    private final Frame mainFrame;

    /**
     * constructor
     *
     * @param mainFrame  frame
     */
    public Controller(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.addTabbedPaneChangeListener();
        this.addClientEventListeners();
        this.addProductEventListeners();
        this.addOrderEventListeners();
    }

    /**
     *
     *<p>used to add certain event listeners</p>
     *
     */
    private void addEventListeners() {

    }
    /**
     * <p>when removing tab it removes previous components</p>
     */
    private void addTabbedPaneChangeListener() {
        mainFrame.getPanelContainer().addChangeListener(e -> {
            Tabs tabbedPanel = (Tabs) mainFrame.getPanelContainer().getSelectedComponent();
            tabbedPanel.getMainPanelActivity().removeAll();
            tabbedPanel.getMainPanelActivity().repaint();
            tabbedPanel.getMainPanelActivity().revalidate();
        });
    }

    /**
     * <p>Adds the events listeners for the buttons in clients panel</p>
     */
    private void addClientEventListeners() {
        Tabs tabbedPanel = (Tabs) mainFrame.getPanelContainer().getComponentAt(0);
        ClientPanel clientPanel = (ClientPanel) tabbedPanel.getMainPanelActivity();
        ClientBLL clientBLL = new ClientBLL();

        tabbedPanel.addCreateButtonListener(e -> {
            clientPanel.clearFields();
            clientPanel.removeAllEventListeners();
            clientPanel.showCreateObjectPanel();
            clientPanel.addPerformOperationBtnListener(new ObjectListener<>(clientPanel, clientBLL));
        });
        clientPanel.addPerformOperationBtnListener(new ObjectListener<>(clientPanel, clientBLL));

        tabbedPanel.addEditButtonListener(e -> {
            clientPanel.clearFields();
            clientPanel.removeAllEventListeners();
            clientPanel.showObjectByIdPanel();
            clientPanel.addNextStepBtnListener(new NextStep<>(clientPanel, clientBLL, "client", "edit"));
            clientPanel.addPerformOperationBtnListener(new EditObject<>(clientPanel, clientBLL));
        });

        tabbedPanel.addRemoveButtonListener(e -> {
            clientPanel.clearFields();
            clientPanel.removeAllEventListeners();
            clientPanel.showObjectByIdPanel();
            clientPanel.addNextStepBtnListener(new NextStep<>(clientPanel, clientBLL, "client", "remove"));
            clientPanel.addPerformOperationBtnListener(new RemoveObject<>(clientPanel, clientBLL, "client"));
        });

        tabbedPanel.addSearchButtonListener(e -> {
            clientPanel.clearFields();
            clientPanel.removeAllEventListeners();
            clientPanel.showObjectByIdPanel();
            clientPanel.addNextStepBtnListener(new NextStep<>(clientPanel, clientBLL, "client", "search"));
            clientPanel.addPerformOperationBtnListener(new SearchObject<>(clientPanel, clientBLL, "client"));
        });

        tabbedPanel.addViewButtonListener(e -> {
            clientPanel.clearFields();
            clientPanel.removeAllEventListeners();
            clientPanel.showViewObjectsPanel();
        });
        tabbedPanel.addViewButtonListener(new ViewObject<>(clientPanel, clientBLL, "client"));
    }



    /**
     * <p>Adds the events listeners for the buttons in orders panel</p>
     */
    private void addOrderEventListeners() {
        Tabs tabbedPanel = (Tabs) mainFrame.getPanelContainer().getComponentAt(2);
        OrdersPanel orderPanel = (OrdersPanel) tabbedPanel.getMainPanelActivity();
        OrdersBLL orderBLL = new OrdersBLL();

        tabbedPanel.addCreateButtonListener(e -> {
            orderPanel.clearFields();
            orderPanel.removeAllEventListeners();
            orderPanel.showCreateObjectPanel();
            orderPanel.addPerformOperationBtnListener(new ObjectListener<>(orderPanel, orderBLL));
        });
        orderPanel.addPerformOperationBtnListener(new ObjectListener<>(orderPanel, orderBLL));

        tabbedPanel.addViewButtonListener(e -> {
            orderPanel.clearFields();
            orderPanel.removeAllEventListeners();
            orderPanel.showViewObjectsPanel();
        });
        tabbedPanel.addViewButtonListener(new ViewObject<>(orderPanel, orderBLL, "order"));
    }
    /**
     * <p>Adds the events listeners for the buttons in products panel</p>
     */
    private void addProductEventListeners() {
        Tabs tabbedPanel = (Tabs) mainFrame.getPanelContainer().getComponentAt(1);
        ProductsPanel productPanel = (ProductsPanel) tabbedPanel.getMainPanelActivity();
        ProductsBLL productBLL = new ProductsBLL();

        tabbedPanel.addCreateButtonListener(e -> {
            productPanel.clearFields();
            productPanel.removeAllEventListeners();
            productPanel.showCreateObjectPanel();
            productPanel.addPerformOperationBtnListener(new ObjectListener<>(productPanel, productBLL));
        });
        productPanel.addPerformOperationBtnListener(new ObjectListener<>(productPanel, productBLL));

        tabbedPanel.addEditButtonListener(e -> {
            productPanel.clearFields();
            productPanel.removeAllEventListeners();
            productPanel.showObjectByIdPanel();
            productPanel.addNextStepBtnListener(new NextStep<>(productPanel, productBLL, "product", "edit"));
            productPanel.addPerformOperationBtnListener(new EditObject<>(productPanel, productBLL));
        });

        tabbedPanel.addRemoveButtonListener(e -> {
            productPanel.clearFields();
            productPanel.removeAllEventListeners();
            productPanel.showObjectByIdPanel();
            productPanel.addNextStepBtnListener(new NextStep<>(productPanel, productBLL, "product", "remove"));
            productPanel.addPerformOperationBtnListener(new RemoveObject<>(productPanel, productBLL, "product"));
        });

        tabbedPanel.addSearchButtonListener(e -> {
            productPanel.clearFields();
            productPanel.removeAllEventListeners();
            productPanel.showObjectByIdPanel();
            productPanel.addNextStepBtnListener(new NextStep<>(productPanel, productBLL, "product", "search"));
            productPanel.addPerformOperationBtnListener(new SearchObject<>(productPanel, productBLL, "product"));
        });

        tabbedPanel.addViewButtonListener(e -> {
            productPanel.clearFields();
            productPanel.removeAllEventListeners();
            productPanel.showViewObjectsPanel();
        });
        tabbedPanel.addViewButtonListener(new ViewObject<>(productPanel, productBLL, "product"));
    }

}


