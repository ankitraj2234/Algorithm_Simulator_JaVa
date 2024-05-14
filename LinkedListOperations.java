import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkedListOperations extends JFrame {

    private JTextField dataField;
    private JTextArea animationArea;

    private LinkedList linkedList;

    public LinkedListOperations() {
        setTitle("Linked List Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel dataLabel = new JLabel("Data:");
        dataField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton traverseButton = new JButton("Traverse");
        JButton searchButton = new JButton("Search");
        JButton searchElementButton = new JButton("Search Element");

        inputPanel.add(dataLabel);
        inputPanel.add(dataField);
        inputPanel.add(submitButton);
        inputPanel.add(insertButton);
        inputPanel.add(deleteButton);
        inputPanel.add(traverseButton);
        inputPanel.add(searchButton);
        inputPanel.add(searchElementButton);

        animationArea = new JTextArea();
        animationArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(animationArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);

        linkedList = new LinkedList();

        submitButton.addActionListener(new SubmitButtonListener());
        insertButton.addActionListener(new InsertButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        traverseButton.addActionListener(new TraverseButtonListener());
        searchButton.addActionListener(new SearchButtonListener());
        searchElementButton.addActionListener(new SearchElementButtonListener());
    }

    private void updateAnimationArea(String message) {
        animationArea.append(message + "\n");
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String dataStr = dataField.getText().trim();
                if (!dataStr.isEmpty()) {
                    String[] dataArray = dataStr.split("[,\\s]+");
                    for (String data : dataArray) {
                        if (!data.isEmpty()) {
                            linkedList.insert(Integer.parseInt(data));
                            updateAnimationArea("Inserted: " + data);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(LinkedListOperations.this,
                            "No input provided. Please enter data in the input field.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(LinkedListOperations.this,
                        "Invalid input. Please enter valid integers.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String dataStr = dataField.getText().trim();
            if (!dataStr.isEmpty()) {
                String[] dataArray = dataStr.split("[,\\s]+");
                if (dataArray.length > 1) {
                    JOptionPane.showMessageDialog(LinkedListOperations.this,
                            "Only one data can be inserted at a time.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int data = Integer.parseInt(dataStr);
                        if (linkedList.isEmpty()) {
                            // If the list is empty, insert normally
                            linkedList.insert(data);
                            updateAnimationArea("Inserted: " + data);
                        } else {
                            // If the list is not empty, prompt the user where to insert
                            String[] options = {"First", "Last"};
                            int choice = JOptionPane.showOptionDialog(
                                    LinkedListOperations.this,
                                    "Where to insert the element?",
                                    "Insertion Option",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                            if (choice == JOptionPane.YES_OPTION) {
                                // Insert at first
                                linkedList.insertAtFirst(data);
                                updateAnimationArea("Inserted at first: " + data);
                            } else if (choice == JOptionPane.NO_OPTION) {
                                // Insert at last
                                linkedList.insert(data);
                                updateAnimationArea("Inserted at last: " + data);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(LinkedListOperations.this,
                                "Invalid input. Please enter a valid integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(LinkedListOperations.this,
                        "No input provided. Please enter data in the input field.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (linkedList.isEmpty()) {
                JOptionPane.showMessageDialog(LinkedListOperations.this,
                        "Linked list is empty. Nothing to delete.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String dataStr = JOptionPane.showInputDialog(LinkedListOperations.this, "Enter the element to delete:");
                if (dataStr != null && !dataStr.isEmpty()) {
                    try {
                        int data = Integer.parseInt(dataStr);
                        boolean deleted = linkedList.delete(data);
                        if (deleted) {
                            updateAnimationArea("Deleted: " + data);
                        } else {
                            updateAnimationArea("Element not found: " + data);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(LinkedListOperations.this,
                                "Invalid input. Please enter a valid integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private class TraverseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String traversalResult = linkedList.traverse();
            updateAnimationArea("Traversal: " + traversalResult);
        }
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String dataStr = dataField.getText().trim();
            if (dataStr.isEmpty()) {
                JOptionPane.showMessageDialog(LinkedListOperations.this,
                        "No input provided. Please enter data to search.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] dataArray = dataStr.split("[,\\s]+");
                if (dataArray.length != 1) {
                    JOptionPane.showMessageDialog(LinkedListOperations.this,
                            "Please provide only one input to search.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int data = Integer.parseInt(dataStr);
                        boolean found = linkedList.search(data);
                        if (found) {
                            updateAnimationArea("Found: " + data);
                        } else {
                            updateAnimationArea("Not found: " + data);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(LinkedListOperations.this,
                                "Invalid input. Please enter a valid integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private class SearchElementButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String dataStr = JOptionPane.showInputDialog(LinkedListOperations.this, "Enter the element to search:");
            if (dataStr != null && !dataStr.isEmpty()) {
                try {
                    int data = Integer.parseInt(dataStr);
                    boolean found = linkedList.search(data);
                    if (found) {
                        updateAnimationArea("Found: " + data);
                    } else {
                        updateAnimationArea("Not found: " + data);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LinkedListOperations.this,
                            "Invalid input. Please enter a valid integer.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LinkedListOperations linkedListOperations = new LinkedListOperations();
            linkedListOperations.setVisible(true);
        });
    }

    static class LinkedList {
        private Node head;

        public LinkedList() {
            this.head = null;
        }

        public void insert(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }
        }

        public void insertAtFirst(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        public boolean delete(int data) {
            if (head == null) {
                return false;
            }
            if (head.data == data) {
                head = head.next;
                return true;
            }
            Node current = head;
            while (current.next != null) {
                if (current.next.data == data) {
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        public String traverse() {
            StringBuilder result = new StringBuilder();
            Node temp = head;
            while (temp != null) {
                result.append(temp.data).append(" -> ");
                temp = temp.next;
            }
            result.append("null");
            return result.toString();
        }

        public boolean search(int data) {
            Node temp = head;
            while (temp != null) {
                if (temp.data == data) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }

        public boolean isEmpty() {
            return head == null;
        }

        private static class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
    }
}
