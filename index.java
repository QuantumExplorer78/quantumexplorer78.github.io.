import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinterWonderland extends JFrame {
    private SnowfallPanel snowfallPanel;

    public WinterWonderland() {
        setTitle("Winter Wonderland");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        snowfallPanel = new SnowfallPanel();
        add(snowfallPanel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snowfallPanel.changeTheme();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WinterWonderland().setVisible(true);
            }
        });
    }
}

class SnowfallPanel extends JPanel {
    private int flakes = 100;
    private Snowflake[] snowflakes;

    public SnowfallPanel() {
        snowflakes = new Snowflake[flakes];
        for (int i = 0; i < flakes; i++) {
            snowflakes[i] = new Snowflake();
        }

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSnowflakes();
                repaint();
            }
        });
        timer.start();
    }

    private void moveSnowflakes() {
        for (Snowflake flake : snowflakes) {
            flake.move();
        }
    }

    public void changeTheme() {
        setBackground(new Color(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Snowflake flake : snowflakes) {
            flake.draw(g);
        }
    }
}

class Snowflake {
    private int x;
    private int y;
    private int speed;

    public Snowflake() {
        this.x = (int) (Math.random() * 800);
        this.y = (int) (Math.random() * 600);
        this.speed = (int) (Math.random() * 3) + 1;
    }

    public void move() {
        y += speed;

        if (y > 600) {
            y = 0;
            x = (int) (Math.random() * 800);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 5, 5);
    }
}
