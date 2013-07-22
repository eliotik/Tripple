import javax.swing.*;
import ua.triple.game.*;

public class Triple {

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}