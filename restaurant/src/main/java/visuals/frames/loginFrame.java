package visuals.frames;

import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.*;

public class loginFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(loginFrame.class);
    
    // JFields
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginFrame(){

        // setup for window
        setTitle("Logowanie");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // adding things to react with
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        // pola
        panel.add(new JLabel("Użytkownik:"));
        usernameField = new JTextField(15);
        panel.add(usernameField);

        panel.add(new JLabel("Hasło:"));
        passwordField = new JPasswordField(15);
        panel.add(passwordField);

        JButton loginButton = new JButton("Zaloguj");
        panel.add(new JLabel()); // puste miejsce w siatce
        panel.add(loginButton);

        add(panel);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("1234")) {
                logger.info("Użytkownik '" + username + "' zalogował się pomyślnie.");
                new mainFrame(username).setVisible(true);
                dispose();
            } else {
                logger.warn("Nieudana próba logowania użytkownika: " + username);
                JOptionPane.showMessageDialog(this, "Błędne dane logowania!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });



    }
}
