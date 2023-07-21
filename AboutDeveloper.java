import javax.swing.*;
import java.awt.*;

public class AboutDeveloper extends JFrame {

    public AboutDeveloper() {
        setTitle("About Developer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Ankit Raj");
        JLabel regNoLabel = new JLabel("Reg No: 12107224");
        JLabel universityLabel = new JLabel("Lovely Professional University");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(nameLabel, gbc);

        gbc.gridy = 1;
        panel.add(regNoLabel, gbc);

        gbc.gridy = 2;
        panel.add(universityLabel, gbc);

        setContentPane(panel);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AboutDeveloper aboutDeveloper = new AboutDeveloper();
                aboutDeveloper.setVisible(true);
            }
        });
    }
}
