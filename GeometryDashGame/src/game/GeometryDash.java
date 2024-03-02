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
		

		Point[] obstaclePointsBlueprint, platPointsBlueprint;
		
		obstaclePointsBlueprint = new Point[]{
			new Point(600, 400),
			new Point(1100, 400),
			new Point(1150, 400),
			new Point(1700, 400),
			new Point(1750, 400),
		};
		platPointsBlueprint = new Point[]{
			new Point(1800, 400),
			new Point(2000, 350),
			new Point(2000, 400),
			new Point(2200, 400),
			new Point(2200, 350),
			new Point(2200, 300),
		};
		
		level = new Level(10, 15, obstaclePointsBlueprint, platPointsBlueprint);
		
		this.addKeyListener(level.player);
		this.addKeyListener(this);
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