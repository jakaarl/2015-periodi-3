package tira.visualization.starmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.navigation.BoundingCube;

public class ScreenCoordinateConverterTest {
    
    @Test
    public void shouldScaleAccordingToSmallerScalingFactor() {
        Coordinates nearTopLeft = new Coordinates(-10, 10, 0);
        Coordinates farBottomRight = new Coordinates(20, 0, 0);
        BoundingCube cube = new BoundingCube(nearTopLeft, farBottomRight);
        // cube is 30 wide and 10 high, screen 100 by 100
        int screenWidth = 100;
        int screenHeight = 100;
        ScreenCoordinateConverter converter = new ScreenCoordinateConverter(screenWidth, screenHeight, cube);
        // should scale to fit width
        assertEquals(3.33f, converter.scalingFactor, 0.01f);
        // should have Y offset to center vertically
        assertEquals(0, converter.xOffset);
        assertTrue(converter.yOffset > 0);
    }
    
    @Test
    public void shouldHaveNoOffsetIfSameScalingFactor() {
        Coordinates nearTopLeft = new Coordinates(-25, 25, 0);
        Coordinates farBottomRight = new Coordinates(25, -25, 0);
        BoundingCube cube = new BoundingCube(nearTopLeft, farBottomRight);
        int screenWidth = 100;
        int screenHeight = 100;
        ScreenCoordinateConverter converter = new ScreenCoordinateConverter(screenWidth, screenHeight, cube);
        assertEquals(0, converter.xOffset);
        assertEquals(0, converter.yOffset);
    }
    
    @Test
    public void origoConversionShouldEliminateNegativeCoords() {
        Coordinates nearTopLeft = new Coordinates(-25, 25, 25);
        Coordinates farBottomRight = new Coordinates(25, -25, -25);
        BoundingCube cube = new BoundingCube(nearTopLeft, farBottomRight);
        int screenWidth = 100;
        int screenHeight = 100;
        ScreenCoordinateConverter converter = new ScreenCoordinateConverter(screenWidth, screenHeight, cube);
        Coordinates converted = converter.convertToTopLeftOrigo(nearTopLeft);
        assertTrue(converted.x >= 0);
        assertTrue(converted.y >= 0);
        assertTrue(converted.z >= 0);
        converted = converter.convertToTopLeftOrigo(farBottomRight);
        assertTrue(converted.x >= 0);
        assertTrue(converted.y >= 0);
        assertTrue(converted.z >= 0);
    }

}
