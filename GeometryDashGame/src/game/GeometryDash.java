package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class GeometryDash extends Game implements KeyListener{
	static int counter = 0;

	private Level level;
	
	public GeometryDash() {
		super("Geometry Dash!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		
		Point[] stereoMadnessObst = new Point[]{
			new Point(600, 400),
			new Point(1100, 400),
			new Point(1150, 400),
			new Point(1700, 400),
			new Point(1750, 400),
			new Point(2000, 400),
			new Point(2200, 400),
			new Point(2200, 350),
			new Point(2950, 375),
			new Point(3150, 400),
			new Point(3200, 400),
		};
		Point[] stereoMadnessPlat = new Point[]{
			new Point(1800, 400),
			new Point(2000, 350),
			new Point(2200, 300),

			new Point(2800, 400),
			new Point(2850, 400),
			new Point(2900, 400),
			new Point(2950, 400),
			new Point(3000, 400),
			new Point(3050, 400),
			new Point(3100, 400), // 10
		
			new Point(3250, 400),
			new Point(3300, 400),
			new Point(3350, 400), 
			new Point(3400, 400),
			new Point(3450, 400),
			new Point(3500, 400),
			new Point(3550, 400), 
			new Point(3600, 400),
			new Point(3650, 400),
			new Point(3700, 400),
			new Point(3750, 400), //21

		};
		
		Point[] testObst = new Point[]{
			new Point(600, 400),
			new Point(700, 400),
			
			new Point(1000, 400),
			new Point(1300, 400),

			new Point(1400, 400),
			new Point(1450, 400),
		};
		Point[] testPlat = new Point[]{
			new Point(1000, 400),
			new Point(1050, 350),
			new Point(1100, 350),

			new Point(1300, 200),
			new Point(1350, 200),
			new Point(1400, 200),
			new Point(1450, 200),

		};
		level = new Level(10, 22, testObst, testPlat);
		
		this.addKeyListener(level.player);
		this.addKeyListener(this);
		level.clearLevel();
		level.setLevel();
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		brush.setColor(Color.white);
		brush.drawString("Conter: " + counter++, 30, 20);
		level.clearLevel();
		level.paintLevel(brush);
		
	}

	public static void main(String[] args) {
		GeometryDash a = new GeometryDash();
		a.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && (level.gameOver|| level.gameWon)){
			level.gameOver = false;
			level.gameWon = false;
			level.clearLevel();
			level.setLevel();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}