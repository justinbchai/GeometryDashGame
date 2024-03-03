package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Polygon implements KeyListener {
	
	protected int vel;
	public final static int BASE_HEIGHT = 400;
	protected boolean isFalling;
	protected boolean canJump;
	public Player() {
		super(new Point[] {new Point(0,0), new Point(20,0), new Point(20,20), new Point(0, 20)}, new Point(100, BASE_HEIGHT), 0);
		vel = 0;
		isFalling = false;
		canJump = true;
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
	
	public void setIsFalling(boolean falling) {
		this.isFalling = falling;
	}
	
	public boolean getIsFalling() {
		return isFalling;
	}
	
	public boolean isWithinPlatformWidth(Level.Platform plat) {
		// return this.findCenter().x > plat.findLeftMostPoint() && this.findCenter().x < plat.findRightmostPoint();
		// return (plat.findLeftMostPoint() < this.findRightmostPoint() && plat.findRightmostPoint() > this.findLeftMostPoint());
		double playerLeft = this.findLeftMostPoint(), playerRight = this.findRightmostPoint();
		double platLeft = plat.findLeftMostPoint(), platRight = plat.findRightmostPoint();
		return (playerLeft > platLeft && playerLeft < platRight) || (playerRight > platLeft && playerRight < platRight);
	}
	
	public void setVel(int vel) {
		this.vel = vel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// left empty
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			if (canJump) {
				isFalling = true;
				vel = 18;
				canJump = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// this is also left empty for this class
		
	}
	
	
}
