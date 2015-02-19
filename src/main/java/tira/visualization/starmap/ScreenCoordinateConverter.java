package tira.visualization.starmap;

import tira.domain.Coordinates;
import tira.navigation.BoundingCube;

/**
 * A utility for converting star map coordinates to &quot;screen coordinates&quot;.
 */
public class ScreenCoordinateConverter {
    
    private final BoundingCube boundingCube;
    final float scalingFactor;
    final int xOffset;
    final int yOffset;
    
    /**
     * Creates a new screen coordinate converter.
     * 
     * @param screenWidth   width of the available screen area (pixels).
     * @param screenHeight  height of the available screen area (pixels).
     * @param boundingCube  bounding cube for the star map.
     */
    public ScreenCoordinateConverter(int screenWidth, int screenHeight, BoundingCube boundingCube) {
        this.boundingCube = boundingCube;
        float xScaleFactor = (float) screenWidth / boundingCube.width();
        float yScaleFactor = (float) screenHeight / boundingCube.height();
        scalingFactor = Math.min(xScaleFactor, yScaleFactor);
        if (xScaleFactor > yScaleFactor) {
            xOffset = Math.round((screenWidth - (scalingFactor * boundingCube.width())) / 2);
            yOffset = 0;
        } else if (yScaleFactor > xScaleFactor) {
            xOffset = 0;
            yOffset = Math.round((screenHeight - (scalingFactor * boundingCube.height())) / 2);
        } else {
            xOffset = 0;
            yOffset = 0;
        }
    }
    
    public Coordinates screenCoordinates(Coordinates mapCoordinates) {
        Coordinates topLeftOrigo = convertToTopLeftOrigo(mapCoordinates);
        int x = Math.round(scalingFactor * topLeftOrigo.x + xOffset);
        int y = Math.round(scalingFactor * topLeftOrigo.y + yOffset);
        int z = Math.round(scalingFactor * topLeftOrigo.z);
        return new Coordinates(x, y, z);
    }
    
    Coordinates convertToTopLeftOrigo(Coordinates fromCenterOrigo) {
        int x = fromCenterOrigo.x - boundingCube.nearTopLeft.x;
        int y = boundingCube.nearTopLeft.y - fromCenterOrigo.y;
        int z = boundingCube.nearTopLeft.z - fromCenterOrigo.z;
        return new Coordinates(x, y, z);
    }

}
