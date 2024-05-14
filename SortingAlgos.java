import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingAlgos extends JFrame {

    private JTextField inputField;
    private JTextArea stepsArea;

    public SortingAlgos() {
        setTitle("Sorting Algorithms");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JButton bubbleSortButton = createButton("Bubble Sort", Color.CYAN, new BubbleSortButtonListener());
        JButton insertionSortButton = createButton("Insertion Sort", Color.ORANGE, new InsertionSortButtonListener());
        JButton selectionSortButton = createButton("Selection Sort", Color.GREEN, new SelectionSortButtonListener());

        JLabel inputLabel = new JLabel("Input:");

        inputField = new JTextField(20);

        JButton sortButton = createButton("Sort", Color.YELLOW, new SortButtonListener());
        JButton clearButton = createButton("Clear", Color.LIGHT_GRAY, new ClearButtonListener());

        stepsArea = new JTextArea(15, 40);
        stepsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepsArea);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(bubbleSortButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(insertionSortButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(selectionSortButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(inputLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(inputField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sortButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        panel.add(clearButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        panel.add(scrollPane, gbc);

        setContentPane(panel);
        pack();
    }

    private JButton createButton(String text, Color color, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.addActionListener(listener);
        return button;
    }

    private class BubbleSortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            int[] array = parseArray(inputText);

            if (array != null) {
                bubbleSort(array);
            }
        }
    }

    private class InsertionSortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            int[] array = parseArray(inputText);

            if (array != null) {
                insertionSort(array);
            }
        }
    }

    private class SelectionSortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            int[] array = parseArray(inputText);

            if (array != null) {
                selectionSort(array);
            }
        }
    }

    private class SortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            int[] array = parseArray(inputText);

            if (array != null) {
                bubbleSort(array);
                insertionSort(array);
                selectionSort(array);
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            inputField.setText("");
            stepsArea.setText("");
        }
    }

    private int[] parseArray(String inputText) {
        if (inputText.isEmpty()) {
            showErrorMessage("Please enter an array.");
            return null;
        }

        String[] values = inputText.split("\\s+");
        int[] array = new int[values.length];

        try {
            for (int i = 0; i < values.length; i++) {
                array[i] = Integer.parseInt(values[i]);
            }
        } catch (NumberFormatException ex) {
            showErrorMessage("Invalid array format. Please enter integers separated by spaces.");
            return null;
        }

        return array;
    }

    private void bubbleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        int n = sortedArray.length;
        StringBuilder sb = new StringBuilder();

        sb.append("Bubble Sort Steps:\n");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                sb.append("  - Comparing elements: ").append(sortedArray[j]).append(" and ").append(sortedArray[j + 1]).append("\n");

                if (sortedArray[j] > sortedArray[j + 1]) {
                    // Swap elements if they are in the wrong order
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;

                    sb.append("    - Swapped ").append(sortedArray[j]).append(" with ").append(sortedArray[j + 1]).append("\n");

                    swapped = true;
                }
            }

            if (!swapped) {
                sb.append("No more swaps required, the array is already sorted.\n");
                break;
            }

            sb.append("  - Current Array: ").append(Arrays.toString(sortedArray)).append("\n");
        }

        showResult("Bubble Sort Result:", sortedArray, sb.toString());
    }


    private void insertionSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        int n = sortedArray.length;
        StringBuilder sb = new StringBuilder();

        sb.append("Insertion Sort Steps:\n");

        for (int i = 1; i < n; ++i) {
            int key = sortedArray[i];
            int j = i - 1;

            sb.append("  - Element ").append(key).append(" at index ").append(i).append(" is selected for insertion.\n");

            while (j >= 0 && sortedArray[j] > key) {
                sortedArray[j + 1] = sortedArray[j];
                j = j - 1;

                sb.append("    - Shifting element ").append(sortedArray[j + 1]).append(" at index ").append(j + 1).append(" to the right.\n");
            }

            sortedArray[j + 1] = key;

            sb.append("  - Element ").append(key).append(" is inserted at index ").append(j + 1).append(".\n");
            sb.append("  - Current Array: ").append(Arrays.toString(sortedArray)).append("\n");
        }

        showResult("Insertion Sort Result:", sortedArray, sb.toString());
    }


    private void selectionSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        int n = sortedArray.length;
        StringBuilder sb = new StringBuilder();

        sb.append("Selection Sort Steps:\n");

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            sb.append("  - Selected minimum element: ").append(sortedArray[minIndex]).append(" at index ").append(minIndex).append("\n");

            for (int j = i + 1; j < n; j++) {
                if (sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = sortedArray[minIndex];
                sortedArray[minIndex] = sortedArray[i];
                sortedArray[i] = temp;

                sb.append("  - Swapped element ").append(sortedArray[i]).append(" at index ").append(i)
                        .append(" with minimum element ").append(sortedArray[minIndex]).append(" at index ").append(minIndex)
                        .append(" within the range [").append(i).append(", ").append(n - 1).append("]\n");
            } else {
                sb.append("  - Minimum element ").append(sortedArray[minIndex]).append(" at index ").append(minIndex)
                        .append(" is already in the correct position within the range [").append(i).append(", ").append(n - 1).append("]\n");
            }

            sb.append("  - Current Array: ").append(Arrays.toString(sortedArray)).append("\n");
        }

        showResult("Selection Sort Result:", sortedArray, sb.toString());
    }


    private void showResult(String title, int[] sortedArray, String steps) {
        JOptionPane.showMessageDialog(SortingAlgos.this, Arrays.toString(sortedArray), title, JOptionPane.INFORMATION_MESSAGE);
        stepsArea.setText(steps);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(SortingAlgos.this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SortingAlgos sortingAlgos = new SortingAlgos();
                sortingAlgos.setVisible(true);
            }
        });
    }
}
