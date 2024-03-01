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
		super("YourGameName!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		
		Obstacle[] obstacles = new Obstacle[10];
		Platform[] platforms = new Platform[10];
		
		for (int i = 0; i < 10; i++) {
			obstacles[i] = new Obstacle(new Point[] {new Point(20, 0), new Point(0, 20), new Point(40, 20)});
			platforms[i] = new Platform(0, 0, 100, 50, 1300);
		}

		Point[] obstaclePointsBlueprint = new Point[30], platPointsBlueprint = new Point[30];
		
		for (int i = 0; i < 30; i++) {
			obstaclePointsBlueprint[i] = new Point(1000 + i * 100, 100 + i * 10);
			platPointsBlueprint[i] = new Point(1000 + i * 100, 100 + i - 10);
		}
		level = new Level(obstacles, platforms, obstaclePointsBlueprint, platPointsBlueprint);
		
		this.addKeyListener(level.getPlayer());
		this.addKeyListener(this);
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
			level.setLevel();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}