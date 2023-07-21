import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class BasicIaD extends JFrame {

    private JTextField arrayField;
    private JTextArea stepsArea;
    private int[] array;

    public BasicIaD() {
        setTitle("Basic Insertion and Deletion");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel arrayLabel = new JLabel("Array:");

        arrayField = new JTextField(20);

        JButton okButton = new JButton("OK");
        okButton.setBackground(Color.GRAY);
        okButton.addActionListener(new OKButtonListener());

        JButton insertionButton = new JButton("Insertion");
        insertionButton.setBackground(Color.GREEN);
        insertionButton.addActionListener(new InsertionButtonListener());

        JButton deletionButton = new JButton("Deletion");
        deletionButton.setBackground(Color.RED);
        deletionButton.addActionListener(new DeletionButtonListener());

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.LIGHT_GRAY);
        clearButton.addActionListener(new ClearButtonListener());

        stepsArea = new JTextArea(15, 40);
        stepsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepsArea);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(arrayLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(arrayField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(okButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(insertionButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(deletionButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        panel.add(clearButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        setContentPane(panel);
        pack();
    }

    private class OKButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String arrayText = arrayField.getText().trim();

            if (arrayText.isEmpty()) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Please enter an array.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] values = arrayText.split("\\s+");
            array = new int[values.length];

            try {
                for (int i = 0; i < values.length; i++) {
                    array[i] = Integer.parseInt(values[i]);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Invalid array format. Please enter integers separated by spaces.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            updateStepsArea(true);
        }
    }

    private class InsertionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (array == null || array.length == 0) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Please enter an array first.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String elementText = JOptionPane.showInputDialog(BasicIaD.this, "Enter the element for insertion:", "Insertion Element", JOptionPane.PLAIN_MESSAGE);

            if (elementText == null) {
                return;
            }

            try {
                int element = Integer.parseInt(elementText);

                String indexText = JOptionPane.showInputDialog(BasicIaD.this, "Enter the index for insertion:", "Insertion Index", JOptionPane.PLAIN_MESSAGE);

                if (indexText == null) {
                    return;
                }

                int index = Integer.parseInt(indexText);
                if (index < 0 || index > array.length) {
                    JOptionPane.showMessageDialog(BasicIaD.this, "Invalid index. Please enter a valid index.",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int[] newArray = new int[array.length + 1];
                System.arraycopy(array, 0, newArray, 0, index);
                newArray[index] = element;
                System.arraycopy(array, index, newArray, index + 1, array.length - index);
                array = newArray;

                updateStepsArea(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Invalid value. Please enter a valid integer.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private class DeletionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (array == null || array.length == 0) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Please enter an array first.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String elementText = JOptionPane.showInputDialog(BasicIaD.this, "Enter the element for deletion:", "Deletion Element", JOptionPane.PLAIN_MESSAGE);

            if (elementText == null) {
                return;
            }

            try {
                int element = Integer.parseInt(elementText);
                int[] originalArray = Arrays.copyOf(array, array.length);

                StringBuilder sb = new StringBuilder();
                sb.append("Array: ").append(Arrays.toString(array)).append("\n");
                sb.append("Steps:\n");
                sb.append("- Deletion:\n");

                int index = -1;
                boolean found = false;

                for (int i = 0; i < array.length; i++) {
                    sb.append("  - Step ").append(i + 1).append(": Comparing ").append(array[i]).append(" with ").append(element).append("\n");
                    if (array[i] == element) {
                        found = true;
                        index = i;
                        break;
                    }
                }

                if (!found) {
                    sb.append("  - Element not found in the array.\n");
                    stepsArea.setText(sb.toString());
                    return;
                }

                sb.append("  - Element found at index ").append(index).append(".\n");
                sb.append("  - Step ").append(array.length + 1).append(": Delete ").append(array[index]).append(" at index ").append(index).append("\n");

                int[] newArray = new int[array.length - 1];
                System.arraycopy(array, 0, newArray, 0, index);
                System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
                array = newArray;

                sb.append("Updated Array: ").append(Arrays.toString(array));
                stepsArea.setText(sb.toString());

                JOptionPane.showMessageDialog(BasicIaD.this, "Array after deletion: " + Arrays.toString(array),
                        "Deletion Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BasicIaD.this, "Invalid value. Please enter a valid integer.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            arrayField.setText("");
            stepsArea.setText("");
        }
    }

    private void updateStepsArea(boolean isInsertion) {
        StringBuilder sb = new StringBuilder();

        sb.append("Array: ").append(Arrays.toString(array)).append("\n");
        sb.append("Steps:\n");

        if (isInsertion) {
            // Insertion steps
            sb.append("- Insertion:\n");
            for (int i = 0; i < array.length; i++) {
                sb.append("  - Step ").append(i + 1).append(": Insert ").append(array[i]).append(" at index ").append(i).append("\n");
            }
        } else {
            // Deletion steps
            sb.append("- Deletion:\n");
            for (int i = array.length - 1; i >= 0; i--) {
                sb.append("  - Step ").append(i + 1).append(": Delete ").append(array[i]).append(" at index ").append(i).append("\n");
            }
        }

        stepsArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BasicIaD basicIaD = new BasicIaD();
                basicIaD.setVisible(true);
            }
        });
    }
}
