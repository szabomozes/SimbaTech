import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwingWindow2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Ablak");
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            
            JPanel menuPanel = new JPanel();
            JButton startButton = new JButton("Start");
            menuPanel.add(startButton);
            
            JPanel gameContainer = new JPanel(new BorderLayout());
            GamePanel gamePanel = new GamePanel("map.png", cardLayout, cardPanel);
            ShopPanel shopPanel = new ShopPanel(gamePanel);
            
            gameContainer.add(gamePanel, BorderLayout.CENTER);
            gameContainer.add(shopPanel, BorderLayout.SOUTH);
            
            cardPanel.add(menuPanel, "menu");
            cardPanel.add(gameContainer, "game");
            
            startButton.addActionListener(e -> {
                cardLayout.show(cardPanel, "game");
                gamePanel.requestFocusInWindow();
            });
            
            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }
}

class GamePanel extends JPanel {
    private BufferedImage image;
    private int offsetX = 0, offsetY = 0;
    private int lastX, lastY;
    private boolean dragging = false;
    private List<MovingImage> movingImages;
    private MovingImage selectedImage = null;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String newImagePath1 = null;
    private String newImagePath2 = null;

    public GamePanel(String imagePath, CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.movingImages = new ArrayList<>();
        
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        add(backButton);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                
                
                if (newImagePath1 != null) {
                    MovingImage tempImage = new MovingImage(newImagePath1, newImagePath2, e.getX() - offsetX, e.getY() - offsetY);
                    movingImages.add(tempImage);
                    newImagePath1 = null;
                    repaint();
                } else {
                    for (MovingImage img : movingImages) {
                        if (img.contains(e.getX() - offsetX, e.getY() - offsetY)) {
                            selectedImage = img;
                            return;
                        }
                    }
                    dragging = true;
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
                if (selectedImage != null) {
                    selectedImage.startMovingTo(e.getX() - offsetX, e.getY() - offsetY, GamePanel.this);
                    selectedImage = null;
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    int dx = e.getX() - lastX;
                    int dy = e.getY() - lastY;
                    
                    offsetX += dx;
                    offsetY += dy;
                    
                    offsetX = Math.max(Math.min(0, offsetX), getWidth() - image.getWidth());
                    offsetY = Math.max(Math.min(0, offsetY), getHeight() - image.getHeight());
                    
                    lastX = e.getX();
                    lastY = e.getY();
                    
                    repaint();
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, offsetX, offsetY, this);
        }
        for (MovingImage img : movingImages) {
            img.draw(g, offsetX, offsetY);
        }
    }
    
    public void setNewImagePath() {
        newImagePath1 = "h1.png";
        newImagePath2 = "h2.png";
    }
}

class ShopPanel extends JPanel {
    public ShopPanel(GamePanel gamePanel) {
        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);
        
        JButton buyButton3 = new JButton("Egyszarvú");
        JButton buyButton4 = new JButton("asdasd");
        JButton buyButton5 = new JButton("adasdasdas");
        buyButton3.addActionListener(e -> gamePanel.setNewImagePath());
        
        add(buyButton3);
        add(buyButton4);
        add(buyButton5);
    }
}


class MovingImage {
    private BufferedImage image1, image2;
    private int x, y;
    private int width, height;
    private Timer timer;
    private int targetX, targetY;
    private int currentImage = 1;
    private int asd = 0;
    
    public MovingImage(String imagePath1, String imagePath2, int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.height = 100; // Fix magasság

        try {
            BufferedImage original1 = ImageIO.read(new File(imagePath1));
            BufferedImage original2 = ImageIO.read(new File(imagePath2));

            this.image1 = resizeImage(original1);
            this.image2 = resizeImage(original2);
            
            this.width = image1.getWidth(); // Mindegyik kép azonos méretű

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage resizeImage(BufferedImage original) {
        int newWidth = (int) ((double) original.getWidth() / original.getHeight() * height); // Arányos szélesség
        Image scaled = original.getScaledInstance(newWidth, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newWidth, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public void startMovingTo(int targetX, int targetY, JPanel panel) {
        this.targetX = targetX - width/2;
        this.targetY = targetY - 4*height/5;

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(10, e -> {  // Lassabb mozgás érdekében 10ms-ra állítottam
            asd += 1;
            if (asd > 10) {
                asd = 0;
                currentImage = currentImage == 1 ? 2 : 1; // Felváltva vált a két mozgásképre
            }

            if (x < this.targetX) x += 2;
            if (x > this.targetX) x -= 2;
            if (y < this.targetY) y += 2;
            if (y > this.targetY) y -= 2;
            
            int difX = Math.max(x, this.targetX) - Math.min(x, this.targetX);
            int difY = Math.max(y, this.targetY) - Math.min(y, this.targetY);
            if (difX == 1 || difX == -1) x += 1;
            if (difY == 1 || difY == -1) y += 1;

            panel.repaint();

            if (x == this.targetX && y == this.targetY) {
                currentImage = 1;
                timer.stop();
                panel.repaint();
            }
        });
        timer.start();
    }

    public boolean contains(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width && clickY >= y && clickY <= y + height;
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        BufferedImage imgToDraw = currentImage == 1 ? image1 : image2;

        if (imgToDraw != null) {
            g.drawImage(imgToDraw, x + offsetX, y + offsetY, null);
        }
    }
}
