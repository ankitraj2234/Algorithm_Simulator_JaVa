import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Iad extends JFrame {

    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextArea allContactsArea;
    private List<Contact> contacts;

    public Iad() {
        setTitle("Insertion and Deletion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");

        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);

        JButton addButton = new JButton("Add Contact");
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(new AddButtonListener());

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.setBackground(Color.RED);
        deleteButton.addActionListener(new DeleteButtonListener());

        JButton basicIadButton = new JButton("BasicIaD");
        basicIadButton.setBackground(Color.RED);
        basicIadButton.setForeground(Color.WHITE);
        basicIadButton.addActionListener(new BasicIaDButtonListener());

        allContactsArea = new JTextArea(15, 40);
        allContactsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(allContactsArea);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(basicIadButton, gbc);

        contacts = new ArrayList<>();

        setContentPane(panel);
        pack();
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();

            if (name.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(Iad.this, "Please enter both name and phone number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(Iad.this, "Please enter a valid phone number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    JOptionPane.showMessageDialog(Iad.this, "A contact with the same phone number already exists.",
                            "Duplicate Contact", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Contact newContact = new Contact(name, phoneNumber);
            contacts.add(newContact);

            updateAllContactsArea();

            nameField.setText("");
            phoneNumberField.setText("");
        }
    }

    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (contacts.isEmpty()) {
                JOptionPane.showMessageDialog(Iad.this, "No contacts to delete.",
                        "Empty Contact List", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String phoneNumber = JOptionPane.showInputDialog(Iad.this,
                    "Enter the phone number of the contact to delete:", "Delete Contact", JOptionPane.PLAIN_MESSAGE);

            if (phoneNumber != null) {
                boolean contactFound = false;

                for (Contact contact : contacts) {
                    if (contact.getPhoneNumber().equals(phoneNumber)) {
                        contacts.remove(contact);
                        contactFound = true;
                        break;
                    }
                }

                if (contactFound) {
                    updateAllContactsArea();
                    JOptionPane.showMessageDialog(Iad.this, "Contact deleted successfully.",
                            "Contact Deleted", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Iad.this, "Contact not found.",
                            "Contact Not Found", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    private class BasicIaDButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicIaD basicIaD = new BasicIaD();
            basicIaD.setVisible(true);
        }
    }


    private void updateAllContactsArea() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append(contact.getName()).append(" - ").append(contact.getPhoneNumber()).append("\n");
        }
        allContactsArea.setText(sb.toString());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}"); // Example: 10 digits
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Iad iad = new Iad();
                iad.setVisible(true);
            }
        });
    }

    private class Contact {
        private String name;
        private String phoneNumber;

        public Contact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }

}
