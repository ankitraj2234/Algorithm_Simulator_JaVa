import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Searching extends JFrame {

    private JTextField inputField;
    private JTextArea resultArea;

    public Searching() {
        setTitle("Searching Algorithms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        UIManager.put("OptionPane.messageForeground", Color.GREEN);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());

        inputPanel.add(new JLabel("Enter Elements: "));
        inputPanel.add(inputField);
        inputPanel.add(clearButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel searchOptionsPanel = new JPanel();
        searchOptionsPanel.setLayout(new BoxLayout(searchOptionsPanel, BoxLayout.Y_AXIS));

        JButton linearSearchButton = new JButton("Linear Search");
        linearSearchButton.addActionListener(new LinearSearchButtonListener());
        JButton binarySearchButton = new JButton("Binary Search");
        binarySearchButton.addActionListener(new BinarySearchButtonListener());

        searchOptionsPanel.add(linearSearchButton);
        searchOptionsPanel.add(Box.createVerticalStrut(10));
        searchOptionsPanel.add(binarySearchButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(searchOptionsPanel, BorderLayout.EAST);

        add(panel);
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            inputField.setText("");
            resultArea.setText("");
        }
    }

    private class LinearSearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resultArea.setText("");
            String input = inputField.getText().trim();
            try {
                String[] elements = input.split("\\s+");
                int[] array = new int[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    array[i] = Integer.parseInt(elements[i]);
                }

                int key = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Enter the element to search:", "Linear Search", JOptionPane.PLAIN_MESSAGE));

                resultArea.append("Linear Search:\n");
                resultArea.append("Linear search is a simple searching algorithm that sequentially checks each element of the array until it finds the target element.\n\n");

                int index = linearSearch(array, key);

                if (index != -1) {
                    JOptionPane.showMessageDialog(null, "Element " + key + " found at index " + index,
                            "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    resultArea.append("Element " + key + " found at index " + index);
                } else {
                    JOptionPane.showMessageDialog(null, "Element not found in the array",
                            "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    resultArea.append("Element not found in the array");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter integers separated by spaces.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class BinarySearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resultArea.setText("");
            String input = inputField.getText().trim();
            try {
                String[] elements = input.split("\\s+");
                int[] array = new int[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    array[i] = Integer.parseInt(elements[i]);
                }

                if (!isSorted(array)) {
                    JOptionPane.showMessageDialog(null, "Array must be sorted for Binary Search!",
                            "Sorting Required", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int key = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Enter the element to search:", "Binary Search", JOptionPane.PLAIN_MESSAGE));

                resultArea.append("Binary Search:\n");
                resultArea.append("Binary search is an efficient searching algorithm that works on sorted arrays. It repeatedly divides the search range in half by comparing the middle element with the target element.\n\n");

                int index = binarySearch(array, key);

                if (index != -1) {
                    JOptionPane.showMessageDialog(null, "Element " + key + " found at index " + index,
                            "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    resultArea.append("Element " + key + " found at index " + index);
                } else {
                    JOptionPane.showMessageDialog(null, "Element not found in the array",
                            "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    resultArea.append("Element not found in the array");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter integers separated by spaces.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            resultArea.append("Comparing " + key + " with " + array[i] + "\n");
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;

        int step = 1; // Step counter

        while (low <= high) {
            int mid = (low + high) / 2;

            resultArea.append("Step " + step + ": ");
            resultArea.append("Search range: [" + low + " - " + high + "]\n");
            resultArea.append("Comparing " + key + " with " + array[mid] + "\n");
            step++;

            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Searching searching = new Searching();
                searching.setVisible(true);
            }
        });
    }
}
