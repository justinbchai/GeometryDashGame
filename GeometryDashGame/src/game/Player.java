package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Polygon implements KeyListener {
	
	private int vel;
	public final static int BASE_HEIGHT = 400;
	private boolean isFalling;
	
	public Player() {
		super(new Point[] {new Point(0,0), new Point(20,0), new Point(20,20), new Point(0, 20)}, new Point(100, BASE_HEIGHT), 0);
		vel = 0;
		isFalling = false;
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
	
	public void move() {
		if (isFalling) {
			this.position.setY(this.position.getY() - vel);
			vel--;
			this.rotate(5);
			if (this.position.getY() >= BASE_HEIGHT) {
				this.placeOnGround();
				vel = 0;
				isFalling = false;
				this.rotation = 0;
			}
		}
	}
	
	public void setIsFalling(boolean falling) {
		this.isFalling = falling;
	}
	
	public boolean getIsFalling() {
		return isFalling;
	}
	
	public boolean isWithinPlatformWidth(Platform plat) {
		return (plat.findLeftMostPoint() <= this.findRightmostPoint() && plat.findRightmostPoint() >= this.findLeftMostPoint());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// left empty
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			if (!isFalling) {
				isFalling = true;
				vel = 18;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// this is also left empty for this class
		
	}
	
	
}
