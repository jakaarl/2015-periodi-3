package tira.domain;

/**
 * Coordinates indicating a location in 3D space.
 */
public class Coordinates implements Comparable<Coordinates> {
	
	public final int x;
	public final int y;
	public final int z;
	
	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int compareTo(Coordinates o) {
		if (this.x == o.x && this.y == o.y && this.z == o.y) {
			return 0;
		} else {
			int thissum = this.x + this.y + this.z;
			int othersum = o.x + o.y + o.z;
			return (thissum > othersum ? 1 : -1);
		}
	}
	
	@Override
	public int hashCode() {
		int hash =
				3 * this.x +
				11 * this.y +
				19 * this.z;
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Coordinates) {
			return equals((Coordinates) o);
		} else {
			return false;
		}
	}
	
	public boolean equals(Coordinates o) {
		return (this.x == o.x && this.y == o.y && this.z == o.z);
	}

}
