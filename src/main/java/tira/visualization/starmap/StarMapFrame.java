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

import tira.domain.Coordinates;
import tira.domain.Star;
import tira.navigation.NavigationNode;
import tira.navigation.StarMap;

@SuppressWarnings("serial")
public class StarMapFrame extends JFrame {
    
    private static final String TITLE = "Star Map";
    private static final int SCREEN_MARGIN = 10;
    
    public StarMapFrame(StarMap starMap) {
        super();
        setTitle(TITLE);
        setExtendedState(MAXIMIZED_BOTH);
        add(new StarMapPanel(starMap));
    }
    
    private static class StarMapPanel extends JPanel {
        
        private final StarMap starMap;
        
        public StarMapPanel(StarMap starMap) {
            super(true);
            this.starMap = starMap;
            setBackground(Color.BLACK);
        }
        
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Dimension size = getSize();
            Insets insets = getInsets();
            int screenWidth = size.width - (insets.left + insets.right) - SCREEN_MARGIN * 2;
            int screenHeight = size.height - (insets.top + insets.bottom) - SCREEN_MARGIN * 2;
            ScreenCoordinateConverter coordinateConverter =
                    new ScreenCoordinateConverter(screenWidth, screenHeight, starMap.calculateBoundingCube());
            paintStars(graphics, coordinateConverter);
        }
        
        private void paintStars(Graphics graphics, ScreenCoordinateConverter coordinateConverter) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setColor(Color.WHITE);
            for (NavigationNode node : starMap.stars) {
                paintStar(node.star, g2d, coordinateConverter);
            }
        }
        
        private void paintStar(Star star, Graphics2D g2d, ScreenCoordinateConverter coordinateConverter) {
            Coordinates coordinates = coordinateConverter.screenCoordinates(star.location);
            int radius = 10; // TODO: scale depending on Z coordinate
            int x = coordinates.x - (radius / 2) + SCREEN_MARGIN;
            int y = coordinates.y - (radius / 2) + SCREEN_MARGIN;
            Shape circle = new Ellipse2D.Float(x, y, radius, radius);
            g2d.draw(circle);
            g2d.fillOval(x, y, radius, radius);
        }
        
    }

}
