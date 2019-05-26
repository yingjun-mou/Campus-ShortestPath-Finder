package hw7;

//import java.io.*;
//import java.util.*;

/**
 * Class Entity represent a entity on campus map represented by its name
 * (building) or "Intersection" (road intersection) and its x and y coordinates.
 * 
 * Representation Invariant: id must be string, x/y must be double. Abstract
 * Function: name is the name of entity, (x,y) is the coordinate of the entity.
 */
public class Entity {
	private String name;
	private String id;
	private double x;
	private double y;

	// Constructor
	public Entity(String newname, String newid, double newx, double newy) {
		name = newname;
		id = newid;
		x = newx;
		y = newy;
	}

	// Accessor
	public String getName() {
		String rname = this.name;
		return rname;
	}

	public String getId() {
		String rid = this.id;
		return rid;
	}

	public double getX() {
		double rx = this.x;
		return rx;
	}

	public double getY() {
		double ry = this.y;
		return ry;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Entity)) {
			return false;
		} else {
			Entity obj = (Entity) o;
			return obj.getName() == this.getName() && obj.getId() == this.getId() && obj.getX() == this.getX()
					&& obj.getY() == this.getY();
		}
	}

	@Override
	public String toString() {
		return "Location [name=" + this.name + ", id=" + this.id + ", x=" + this.x + ", y=" + this.y + "]";
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}