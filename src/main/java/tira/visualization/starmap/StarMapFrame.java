package tira.visualization.starmap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tira.domain.Star;
import tira.navigation.NavigationNode;
import tira.navigation.StarMap;

@SuppressWarnings("serial")
public class StarMapFrame extends JFrame {
    
    private static final String TITLE = "Star Map";
    
    public StarMapFrame(StarMap starMap) {
        super();
        setTitle(TITLE);
        setExtendedState(MAXIMIZED_BOTH);
        add(new StarMapPanel(starMap));
    }
    
    private static class StarMapPanel extends JPanel {
        
        private StarMap starMap;
        private int xOffset;
        private int yOffset;
        
        public StarMapPanel(StarMap starMap) {
            super(true);
            this.starMap = starMap;
            setBackground(Color.BLACK);
        }
        
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Dimension size = getSize();
            Insets insets = getInsets();
            xOffset = (size.width - (insets.left + insets.right)) / 2;
            yOffset = (size.height - (insets.top + insets.bottom)) / 2;
            paintStars(graphics);
        }
        
        private void paintStars(Graphics graphics) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setColor(Color.WHITE);
            for (NavigationNode node : starMap.stars) {
                paintStar(node.star, g2d);
            }
        }
        
        private void paintStar(Star star, Graphics2D g2d) {
            int radius = 10;
            int x = star.location.x + xOffset - (radius / 2);
            int y = star.location.y + yOffset - (radius / 2);
            Shape circle = new Ellipse2D.Float(x, y, radius, radius);
            g2d.draw(circle);
            g2d.fillOval(x, y, radius, radius);
        }
        
    }

}
