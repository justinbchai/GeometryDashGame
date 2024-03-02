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
		
		Level.Obstacle[] obstacles = new Level.Obstacle[10];
		Level.Platform[] platforms = new Level.Platform[10];
		Point[] obstaclePointsBlueprint = new Point[30], platPointsBlueprint = new Point[30];
		
		level = new Level(obstacles, platforms, obstaclePointsBlueprint, platPointsBlueprint);
		
		for (int i = 0; i < 10; i++) {
			level.obstacles[i] = level.new Obstacle(new Point[] {new Point(20, 0), new Point(0, 20), new Point(40, 20)});
			level.platforms[i] = level.new Platform(0, 0, 100, 50, 1300);
		}

		
		
		for (int i = 0; i < 30; i++) {
			level.obstacleCoordsBlueprint[i] = new Point(1000 + i * 100, 100 + i * 10);
			level.platCoordsBlueprint[i] = new Point(1000 + i * 100, 100 + i - 10);
		}
		
		
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			level.gameOver = false;
			level.gameWon = false;
			level.setLevel();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}