package tira.navigation;

import tira.domain.Coordinates;

/**
 * A value class depicting a bounding cube.
 */
public class BoundingCube {
	
	/** Near top left corner: the least x, and most y and z. */
	public final Coordinates nearTopLeft;
	/** Far bottom right corner: the most x, and least y and z. */ 
	public final Coordinates farBottomRight;
	
	public BoundingCube(Coordinates nearTopLeft, Coordinates farBottomRight) {
		this.nearTopLeft = nearTopLeft;
		this.farBottomRight = farBottomRight;
	}
	
	/**
	 * Gets the width of the cube.
	 * 
	 * @return the width of the cube.
	 */
	public int width() {
	    return farBottomRight.x - nearTopLeft.x;
	}
	
	/**
	 * Gets the height of the cube.
	 * 
	 * @return the height of the cube.
	 */
	public int height() {
	    return nearTopLeft.y - farBottomRight.y;
	}
	
	/**
	 * Gets the depth of the cube.
	 * 
	 * @return the depth of the cube.
	 */
	public int depth() {
	    return nearTopLeft.z - farBottomRight.z;
	}
	
	/**
	 * Checks whether the given coordinates fall within this bounding cube.
	 * Edges are inclusive: coordinates located on an edge are considered
	 * contained.
	 * 
	 * @param coordinates	coordinates to check.
	 * 
	 * @return	<code>true</code> if coordinates are contained by the bounding cube.
	 */
	public boolean contains(Coordinates coordinates) {
		return (
				nearTopLeft.x <= coordinates.x &&
				nearTopLeft.y >= coordinates.y &&
				nearTopLeft.z >= coordinates.z &&
				farBottomRight.x >= coordinates.x &&
				farBottomRight.y <= coordinates.y &&
				farBottomRight.z <= coordinates.z);
	}
}