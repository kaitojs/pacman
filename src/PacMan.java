import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends JPanel {
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;

        Block(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.startX = x;
            this.startY = y;
        }
    }
    private int rowCount = 21;
    private int colCount = 19;
    private int titleSize = 32;
    private int boardWidth = colCount * titleSize;
    private int boardHeight = rowCount * titleSize;

    private Image wallImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] titleMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    HashSet<Block> walls;
    HashSet<Block> foods;
    HashSet<Block> ghosts;
    Block pacman;

    PacMan() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);

        wallImage = new ImageIcon(getClass().getResource("/img/wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("/img/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("/img/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/img/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("/img/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("/img/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/img/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/img/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/img/pacmanRight.png")).getImage();

        loadMap();
        System.out.println(foods.size());
        System.out.println(ghosts.size());
        System.out.println(walls.size());
    }

    void loadMap() {
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                String row = titleMap[r];
                char titleMapChar = row.charAt(c);
                int x = c * titleSize;
                int y = r * titleSize;
                if (titleMapChar == 'X') {
                    Block wall = new Block(wallImage, x, y, titleSize, titleSize);
                    walls.add(wall);
                } else if (titleMapChar == 'b') {
                    Block ghost = new Block(blueGhostImage, x, y, titleSize, titleSize);
                    ghosts.add(ghost);
                } else if (titleMapChar == 'o') {
                    Block ghost = new Block(orangeGhostImage, x, y, titleSize, titleSize);
                    ghosts.add(ghost);
                } else if (titleMapChar == 'p') {
                    Block ghost = new Block(pinkGhostImage, x, y, titleSize, titleSize);
                    ghosts.add(ghost);
                } else if (titleMapChar == 'r') {
                    Block ghost = new Block(redGhostImage, x, y, titleSize, titleSize);
                    ghosts.add(ghost);
                } else if (titleMapChar == 'P') {
                    pacman = new Block(pacmanRightImage, x, y, titleSize, titleSize);
                } else if (titleMapChar == ' ') {
                    Block food = new Block(null, x + 14, y + 14, 4, 4);
                }
            }
        }
    }
}
