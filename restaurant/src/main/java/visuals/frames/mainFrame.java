package visuals.frames;

import javax.swing.*;

public class mainFrame extends JFrame {

    mainFrame(String username){

        setTitle("Witaj, " + username);
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Zalogowany jako: " + username, SwingConstants.CENTER);
        add(label);
    }
}
