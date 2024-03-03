package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import game.Level.Obstacle;
import game.Level.Platform;

public class Level {
	Player player;
	private Ground ground;
	Obstacle[] obstacles;
	Platform[] platforms;
	private ArrayList<Point> obstacleCoords, platformCoords;
	boolean moreObstacles, morePlats, gameOver, gameWon;
	 Point[] obstacleCoordsBlueprint, platCoordsBlueprint;
	private TextElement endScreen;

	class Obstacle extends Polygon {

		public Obstacle(Point[] points) {
			super(points, new Point(600, 400), 0);
			this.placeOnGround();
		}

		public void paint(Graphics brush) {
			Point[] points = this.getPoints();
			int[] xPoints = new int[points.length], yPoints = new int[points.length];
			for (int i = 0; i < points.length; i++) {
				xPoints[i] = (int) points[i].getX();
				yPoints[i] = (int) points[i].getY();
			}
			brush.fillPolygon(xPoints, yPoints, points.length);
			move();
		}
	}

	class Platform extends Polygon {
		// public Platform(double x1, double y1, double x2, double y2, double xOffset) {
		// 	super(new Point[] { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) },
		// 			new Point(xOffset, 0), 0);
		// 	this.placeOnGround();
		// }

		// public Platform(double x1, double y1, double x2, double y2, Point position) {
		// 	super(new Point[] { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) }, position,
		// 			0);
		// }

		public Platform(double x1, double y1, double x2, double y2) {
			super(new Point[] { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) }, new Point(1800, 400),
					0);
		}

		public void paint(Graphics brush) {
			Point[] points = this.getPoints();
			int[] xPoints = new int[points.length], yPoints = new int[points.length];
			for (int i = 0; i < points.length; i++) {
				xPoints[i] = (int) points[i].getX();
				yPoints[i] = (int) points[i].getY();
			}
			brush.fillPolygon(xPoints, yPoints, points.length);
			move();
		}

	}

	public Level(int numObstacles, int numPlatforms, Point[] obstacleCoordsBlueprint, Point[] platCoordsBlueprint) {
		obstacles = new Obstacle[numObstacles];
		platforms = new Platform[numPlatforms];

		for (int i = 0; i < numObstacles; i++) {
			obstacles[i] = new Obstacle(new Point[] {new Point(20, 0), new Point(0, 20), new Point(40, 20)});
			
		}
		for(int i = 0; i < numPlatforms; i++){
			platforms[i] = new Platform(0, 0, 50, 50); //change back later
		}

		this.obstacleCoordsBlueprint = obstacleCoordsBlueprint;
		this.platCoordsBlueprint = platCoordsBlueprint;


		this.endScreen = (String str, int xcoord, int ycoord, Graphics brush, float fontSize) -> {
			brush.setFont(brush.getFont().deriveFont(fontSize));
			brush.drawString(str, xcoord, ycoord);
			brush.setFont(brush.getFont().deriveFont(50F));
			brush.drawString("Press ENTER to play again.", 80, 400);
		};

		obstacleCoords = new ArrayList<>();
		platformCoords = new ArrayList<>();
		moreObstacles = true;
		morePlats = true;
		player = new Player() {
			@Override
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
		};
		ground = new Ground();
		gameOver = false;
		clearLevel();
		setLevel();
	}

	public void setLevel() {
		player.position = new Point(100, 400);

		for (int i = 0; i < obstacleCoordsBlueprint.length; i++) {
			obstacleCoords.add(new Point(obstacleCoordsBlueprint[i].x, obstacleCoordsBlueprint[i].y));
		}
		for (int i = 0; i < platCoordsBlueprint.length; i++) {
			platformCoords.add(new Point(platCoordsBlueprint[i].x, platCoordsBlueprint[i].y));
		}
		for (int i = 0; i < obstacles.length; i++) {
			if(obstacleCoords.size() > 0){
				obstacles[i].position = obstacleCoords.remove(0);
			} /*else {
				moreObstacles = false;
			}*/
		}
		for (int i = 0; i < platforms.length; i++) {
			if(platformCoords.size() > 0){
				platforms[i].position = platformCoords.remove(0);
			} /*else {
				morePlats = false;
			}*/
			
		}
		//System.out.println(obstacleCoords.toString());
	}

	public void clearLevel(){
		obstacleCoords.clear();
		platformCoords.clear();
		/* for(Platform p : platforms){
			p.position = new Point(1000,1000);
		}
		for(Obstacle o : obstacles){
			o.position = new Point(1000,1000);
		}*/
		
	}

	public void paintLevel(Graphics brush) {

		if (!gameOver) {
			player.paint(brush);
			ground.paint(brush);
			if(player.collides(ground)){
				player.canJump = true;
			}
			for (Obstacle obstacle : obstacles) {
				if (player.collides(obstacle)) {
					this.gameOver = true;
					break;
				}
				if (obstacle.findRightmostPoint() <= 0) {
					if (obstacleCoords.size() > 0) {
						obstacle.position = obstacleCoords.remove(0);
					} else {
						moreObstacles = false;
					}
				}
				obstacle.paint(brush);
			}
			for (Platform plat : platforms) {
				
				if (player.collides(plat)) {
					//System.out.println(player.isFalling);
					if (isAbove(player, plat)) {
						player.placeOn(plat);
						player.position.y--;
						System.out.println(player.collides(plat));
						player.canJump = true;
						player.vel = 0;
						player.isFalling = false;
						player.rotation = 0;
					} else {
						this.gameOver = true;
						break;
					}
				}
				if (plat.findRightmostPoint() <= 0) {
					if (platformCoords.size() > 0) {
						plat.position = platformCoords.remove(0);
					} else {
						morePlats = false;
					}
				}
				
				if (!player.getIsFalling() && !player.isWithinPlatformWidth(plat)
						&& player.position.y < Player.BASE_HEIGHT) {
					System.out.println("Plat left: " + plat.findLeftMostPoint() + "Plat right: " + plat.findRightmostPoint() + "Player x: " + player.position.x);
					player.setIsFalling(true);
				}
				plat.paint(brush);
			}
			if (!moreObstacles && !morePlats && !elementsOnScreen() && !gameOver) {
				gameOver = true;
				gameWon = true;
			}
		} else {
			/*
			 * Font currentFont = brush.getFont(); Font newFont =
			 * currentFont.deriveFont(currentFont.getSize() * 8.5F); brush.setFont(newFont);
			 */
			if (gameWon) {
				endScreen.displayText("LEVEL COMPLETE!!!", 25, 300, brush, 75F);
			} else {
				endScreen.displayText("GAME OVER!!!", 50, 300, brush, 100F);
			}
			clearLevel();

		}
	}


	private boolean elementsOnScreen() {
		for (Obstacle ob : obstacles) {
			if (ob.findRightmostPoint() > 0) {
				return true;
			}
		}
		for (Platform pl : platforms) {
			if (pl.findRightmostPoint() > 0) {
				return true;
			}
		}
		return false;
	}
	
	//student written
	private static boolean isAbove(Polygon poly, Polygon other) {
		return (poly.position.y < other.position.y);
	}
	
}
