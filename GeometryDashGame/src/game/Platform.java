package game;

import java.awt.Graphics;

public class Platform extends Polygon {
	public Platform(double x1, double y1, double x2, double y2, double xOffset) {
		super(new Point[] {new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2)}, new Point(xOffset, 0), 0);
		this.placeOnGround();
	}
	
	public Platform(double x1, double y1, double x2, double y2, Point position) {
		super(new Point[] {new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2)}, position, 0);
	}
	
	public void move() {
		this.position.x -= 5;
	}
	
	public void paint(Graphics brush) {
		Point[] points = this.getPoints();
		int[] xPoints = new int[points.length], yPoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int)points[i].getX();
			yPoints[i] = (int)points[i].getY();
		}
		brush.fillPolygon(xPoints, yPoints, points.length);
		move();
	}
	
}
