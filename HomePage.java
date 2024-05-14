import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Algorithm Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("Bg.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        JButton sortingButton = new JButton("Sorting");
        JButton searchingButton = new JButton("Searching");
        JButton insertionDeletionButton = new JButton("Insertion/Deletion");
        JButton linkedListButton = new JButton("LinkedList Operations");
        JButton aboutDeveloperButton = new JButton("About Developer");

        Dimension buttonSize = new Dimension(120, 30);
        sortingButton.setPreferredSize(buttonSize);
        searchingButton.setPreferredSize(buttonSize);
        insertionDeletionButton.setPreferredSize(buttonSize);
        linkedListButton.setPreferredSize(buttonSize);

        JLabel madeByLabel = new JLabel("Made By Ankit Raj");
        madeByLabel.setHorizontalAlignment(JLabel.CENTER);
        madeByLabel.setForeground(Color.RED);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setOpaque(false);
        labelPanel.add(madeByLabel, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.SOUTH);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(sortingButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(searchingButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(insertionDeletionButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(linkedListButton, gbc);

        sortingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redirectToSortingGUI();
            }
        });

        searchingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redirectToSearchingGUI();
            }
        });

        insertionDeletionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInsertionDeletionGUI();
            }
        });

        linkedListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLinkedListOperationsGUI();
            }
        });

        aboutDeveloperButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAboutDeveloperGUI();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 10, 10, 10);
        panel.add(aboutDeveloperButton, gbc);
    }

    private void redirectToSortingGUI() {
        SortingAlgos sortingAlgos = new SortingAlgos();
        sortingAlgos.setVisible(true);
    }

    private void redirectToSearchingGUI() {
        Searching searching = new Searching();
        searching.setVisible(true);
    }

    private void openInsertionDeletionGUI() {
        Iad iad = new Iad();
        iad.setVisible(true);
    }

    private void openLinkedListOperationsGUI() {
        LinkedListOperations linkedListOperations = new LinkedListOperations();
        linkedListOperations.setVisible(true);
    }

    private void openAboutDeveloperGUI() {
        AboutDeveloper aboutDeveloper = new AboutDeveloper();
        aboutDeveloper.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setSize(400, 200);
                homePage.setVisible(true);
            }
        });
    }
}
