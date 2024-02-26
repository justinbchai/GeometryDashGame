package game;

import java.awt.Graphics;

public class Ground extends Polygon {

	public final static int GROUND_HEIGHT = 415;
	
	public Ground() {
		super(new Point[] {new Point(0,0), new Point(800,0), new Point(800,1), new Point(0,1)}, new Point(190, GROUND_HEIGHT), 0);
	}
	
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
