package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class GeometryDash extends Game {
	static int counter = 0;
	private Player player;
	private Ground ground;
	private Obstacle obstacle;
	private Platform platform;
	private boolean gameOver;
	
	public GeometryDash() {
		super("YourGameName!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		this.gameOver = false;
		player = new Player();
		ground = new Ground();
		platform = new Platform(0, 0, 20, 20, 1300);
		obstacle = new Obstacle(new Point[] {new Point(20, 0), new Point(0, 20), new Point(40, 20)});
		
		this.addKeyListener(player);
	}

	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		brush.setColor(Color.white);
		if (!gameOver) {
			counter++;
			brush.drawString("Counter is " + counter, 10, 10);
			player.paint(brush);
			ground.paint(brush);
			obstacle.paint(brush);
			platform.paint(brush);
			if (player.collides(obstacle)) {
				this.gameOver = true;
			}
			if (player.getIsFalling()) {
				if (player.collides(platform)) {
					if(player.findLowestPoint() - 5 <= platform.findHighestPoint()) {
						player.placeOn(platform);
						player.setIsFalling(false);
					} else {
						this.gameOver = true;
					}
				}
			}
			if (!player.getIsFalling() && !player.collides(platform) && player.position.y != Player.BASE_HEIGHT) {
				player.setIsFalling(true);
			}

		} else {
			Font currentFont = brush.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 8.5F);
			brush.setFont(newFont);
			brush.drawString("GAME OVER!!!", 50, 300);
		}
		
	}

	public static void main(String[] args) {
		GeometryDash a = new GeometryDash();
		a.repaint();
	}
}