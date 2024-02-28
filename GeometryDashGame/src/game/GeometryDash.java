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

class GeometryDash extends Game {
	static int counter = 0;
	private Obstacle obstacle;
	private Platform platform;
	private Level level;
	
	public GeometryDash() {
		super("YourGameName!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		/*
		Obstacle[] obstacles = new Obstacle[10];
		Platform[] platforms = new Platform[10];
		*/
		//for (int i = 0; i < 10; i++) {
			obstacle = new Obstacle(new Point[] {new Point(20, 0), new Point(0, 20), new Point(40, 20)});
			platform = new Platform(0, 0, 100, 50, 1300);
		//}

		ArrayList<Point> obstaclePoints = new ArrayList<>(), platPoints = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			obstaclePoints.add(new Point(1000, 400));
			platPoints.add(new Point(1000, 400));
		}
		level = new Level(new Obstacle[] {obstacle}, new Platform[] {platform}, obstaclePoints, platPoints);
		
		this.addKeyListener(level.getPlayer());
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		brush.setColor(Color.white);
		level.paintLevel(brush);
		
	}

	public static void main(String[] args) {
		GeometryDash a = new GeometryDash();
		a.repaint();
	}
}