import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class ScrollingBackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;
    private BufferedImage machineImage;
    private int xOffset = 0;
    private int scrollSpeed = 1;
    private int direction = -1;

    public ScrollingBackgroundPanel() {
        try {
            backgroundImage = ImageIO.read(new File("menu_background.png"));
            machineImage = ImageIO.read(new File("64.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Simbatech");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title, gbc);

        gbc.gridy++;
        JLabel subtitle = new JLabel("A vadon a te játszótered - építsd, védd meg, urald");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        add(subtitle, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());

        JButton easyButton = new JButton("Könnyű");
        JButton mediumButton = new JButton("Közepes");
        JButton hardButton = new JButton("Nehéz");

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        add(buttonPanel, gbc);

        easyButton.addActionListener(e -> System.out.println("Könnyű mód kiválasztva"));
        mediumButton.addActionListener(e -> System.out.println("Közepes mód kiválasztva"));
        hardButton.addActionListener(e -> System.out.println("Nehéz mód kiválasztva"));

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveBackground();
            }
        }, 1000, 20);
    }

    private void moveBackground() {
        if (backgroundImage == null) return;

        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * frameHeight);

        if (xOffset <= -(newWidth - frameWidth)) {
            direction = 1;
        } else if (xOffset >= 0) {
            direction = -1;
        }

        xOffset += direction * scrollSpeed;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            int frameHeight = getHeight();
            int newWidth = (int) ((double) backgroundImage.getWidth() / backgroundImage.getHeight() * frameHeight);
            g.drawImage(backgroundImage, xOffset, 0, newWidth, frameHeight, this);
        }

        if (machineImage != null) {
            int imageWidth = machineImage.getWidth();
            int imageHeight = machineImage.getHeight();
            int x = (getWidth() - imageWidth) / 2;
            int y = getHeight() / 4;
            g.drawImage(machineImage, x, y, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Oda-vissza mozgó háttér");
            ScrollingBackgroundPanel menuPanel = new ScrollingBackgroundPanel();

            frame.add(menuPanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            frame.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent e) {
                    menuPanel.repaint();
                }
            });
        });
    }
}
