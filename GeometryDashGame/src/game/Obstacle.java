package game;

import java.awt.Graphics;

public class Obstacle extends Polygon {
	
	public Obstacle(Point[] points) {
		super(points, new Point(850, 400), 0);
		this.placeOnGround();
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
