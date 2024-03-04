package game;

import java.awt.Graphics;

/**
 * <code>Ground</code> is the class for the ground in each <code>Level</code>
 * @author Justin Chai
 *
 */

public class Ground extends Polygon {
	
	/**
	 * The height at which all <code>Ground</code> objects are constructed.
	 */
	public final static int GROUND_HEIGHT = 415;
	
	/**
	 * The sole constructor of <code>Ground</code>. Invokes the <code>Polygon</code>
	 * constructor to construct a rectangle the length of the canvas and one pixel
	 * tall at <code>GROUND_HEIGHT</code>.
	 */
	public Ground() {
		super(new Point[] {new Point(0,0), new Point(800,0), new Point(800,1), new Point(0,1)}, new Point(190, GROUND_HEIGHT), 0);
	}
	
	/**
	 * Draws the current object each frame.
	 * @param brush The <code>Graphics</code> object with which the ground is drawn.
	 */
	public void paint(Graphics brush) {
		Point[] points = this.getPoints();
		int[] xPoints = new int[points.length], yPoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int)points[i].getX();
			yPoints[i] = (int)points[i].getY();
		}
		brush.fillPolygon(xPoints, yPoints, points.length);
	}
	
}
