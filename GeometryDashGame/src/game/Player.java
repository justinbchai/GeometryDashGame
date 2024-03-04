package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The <code>Player</code> class extends <code>Polygon</code> and is the only 
 * element of Geometry Dash which the player has control of. It is a 20x20 pixel
 * square and the only input is to jump.
 * There is only one <code>Player</code> object for every <code>Level</code> object.
 * 
 * @author Justin Chai
 * @see		Polygon
 */

public class Player extends Polygon implements KeyListener {
	
	protected int vel;
	public final static int BASE_HEIGHT = 400;
	protected boolean isFalling;
	protected boolean canJump;
	
	/**
	 * Sole constructor. Invokes the <code>Polygon</code> constructor to make a
	 * 20x20 square on the on the ground at x = 100. vel = 0, isFalling = false, and
	 * canJump = true.
	 */
	public Player() {
		super(new Point[] {new Point(0,0), new Point(20,0), new Point(20,20), new Point(0, 20)}, new Point(100, BASE_HEIGHT), 0);
		vel = 0;
		isFalling = false;
		canJump = true;
	}
	
	/**
	 * Draws the <code>Player</code> object in the <code>GeometryDash</code> canvas.
	 * Uses the <code>Polygon</code> method <code>getPoints()</code> and the <code>Graphics</code>
	 * object parameter to draw the player. After drawing the object, it then calls the
	 * <code>move</code> method.
	 * 
	 * @see Graphics#fillPolygon(int[], int[], int)
	 * @see #move()
	 * @param brush <code>Graphics</code> object with which the current object is drawn.
	 */
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
	
	/**
	 * Sets the the current object's <code>isFalling</code> value.
	 * @param falling The value to set <code>isFalling</code> to.
	 */
	
	public void setIsFalling(boolean falling) {
		this.isFalling = falling;
	}
	
	/**
	 * Returns the current object's <code>isFalling</code> value.
	 * @return true if current object is in the process of jumping, false otherwise.
	 */
	
	public boolean getIsFalling() {
		return isFalling;
	}
	
	/**
	 * Return true if any part of the current object is within the <code>plat</code>
	 * parameter along the x-axis and false otherwise.
	 * @param plat The platform to check if the current object is within.
	 * @return true if current object is within the platform, false otherwise.
	 */
	public boolean isWithinPlatformWidth(Level.Platform plat) {
		// return this.findCenter().x > plat.findLeftMostPoint() && this.findCenter().x < plat.findRightmostPoint();
		// return (plat.findLeftMostPoint() < this.findRightmostPoint() && plat.findRightmostPoint() > this.findLeftMostPoint());
		double playerLeft = this.findLeftMostPoint(), playerRight = this.findRightmostPoint();
		double platLeft = plat.findLeftMostPoint(), platRight = plat.findRightmostPoint();
		return (playerLeft > platLeft && playerLeft < platRight) || (playerRight > platLeft && playerRight < platRight);
	}
	
	/**
	 * Sets the current object's vertical velocity (negative is up, positive is down).
	 * @param vel The new <code>vel</code> value.
	 */
	public void setVel(int vel) {
		this.vel = vel;
	}
	
	/**
	 * Implementation required because of the <code>KeyListener</code> interface
	 * but is not used for this class so it is left empty
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// left empty
		
	}

	/**
	 * If the current object is able to jump and the user presses <code>SPACE</code>
	 * , <code>UP</code>, or <code>W</code>, the current object's velocity is set to 18,
	 * isFalling is set to true, and canJump is set to false
	 */
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

	/**
	 * Implementation required because of the <code>KeyListener</code> interface
	 * but is not used for this class so it is left empty
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// this is also left empty for this class
		
	}
	
	
}
