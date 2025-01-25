package clinicApp.data;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class AvailableViewsUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Available Views UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center on screen

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a list of views
        String[] views = {"View 1", "View 2", "View 3"};
        JList<String> viewList = new JList<>(views);
        viewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        viewList.setSelectedIndex(0);

        // Create a scroll pane for the list
        JScrollPane listScrollPane = new JScrollPane(viewList);
        listScrollPane.setPreferredSize(new Dimension(100, 0));

        // Create a panel to display selected view content
        JPanel viewPanel = new JPanel(new CardLayout());
        JLabel view1Content = new JLabel("Content of View 1", JLabel.CENTER);
        JLabel view2Content = new JLabel("Content of View 2", JLabel.CENTER);
        JLabel view3Content = new JLabel("Content of View 3", JLabel.CENTER);
        viewPanel.add(view1Content, "View 1");
        viewPanel.add(view2Content, "View 2");
        viewPanel.add(view3Content, "View 3");

        // Add an event listener to switch views based on selection
        viewList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CardLayout cardLayout = (CardLayout) viewPanel.getLayout();
                cardLayout.show(viewPanel, viewList.getSelectedValue());
            }
        });

        // Add components to the main panel
        mainPanel.add(listScrollPane, BorderLayout.EAST);
        mainPanel.add(viewPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
