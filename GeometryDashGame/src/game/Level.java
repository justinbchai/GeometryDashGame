package game;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * <code>Level</code> is the class that contains the information for each level of
 * Geometry Dash.
 * @author Caleb Chang
 * @author Justin Chai
 *
 */
public class Level {
	/**
	 * The <code>Player</code> object in the level. The move() method is overwritten
	 * when it is constructed.
	 */
	Player player;
	/**
	 * The <code>Ground</code> object in the level.
	 */
	private Ground ground;
	/**
	 * An array of obstacle objects to be used in the level.
	 */
	Obstacle[] obstacles;
	/**
	 * An array of platform objects to be used in the level.
	 */
	Platform[] platforms;
	/**
	 * An <code>ArrayList</code> of type <code>Point</code> specifying coordinates
	 * where there should be a platform.
	 */
	private ArrayList<Point> obstacleCoords;
	/**
	 * An <code>ArrayList</code> of type <code>Point</code> specifying coordinates
	 * where there should be an obstacle.
	 */
	private ArrayList<Point> platformCoords;
	private boolean moreObstacles;
	private boolean morePlats;
	boolean gameOver;
	boolean gameWon;
	private Point[] obstacleCoordsBlueprint, platCoordsBlueprint;
	private TextElement endScreen;

	/**
	 * <code>Obstacle</code> is an inner class of <code>Level</code> that extends
	 * <code>Polygon</code>. If the player touches and obstacle, the game is over.
	 * @author Justin Chai
	 *
	 */
	class Obstacle extends Polygon {
		/**
		 * Constructs an <code>Obstacle</code> object by invoking the super constructor
		 * using the <code>points</code> parameter.
		 * @param points An array of points specifying the shape of the obstacle.
		 */
		public Obstacle(Point[] points) {
			super(points, new Point(600, 400), 0);
			this.placeOnGround();
		}

		/**
		 * Draws the obstacle object on the canvas and calls the {@link #move()} method.
		 * @param brush The <code>Graphics</code> object with which the obstacle is drawn.
		 * @see	  #move()
		 */
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
	
	/**
	 * <code>Platform</code> is an inner class of <code>Level</code> that extends
	 * <code>Polygon</code>. If the player touches and obstacle from the sides 
	 * or the bottom, the game is over. If it touches it from the top, it lands on the platform.
	 * All platforms are rectangular.
	 * @author Justin Chai
	 *
	 */
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

		/**
		 * Constructs a new <code>Platform</code> object.
		 * @param x1 x-value of the left side of the platform.
		 * @param y1 y-value of the top side of the platform.
		 * @param x2 x-value of the right side of the platform.
		 * @param y2 y-value of the bottome side of the platform.
		 */
		public Platform(double x1, double y1, double x2, double y2) {
			super(new Point[] { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) }, new Point(1800, 400),
					0);
		}

		/**
		 * Draws the current object.
		 * @param brush The <code>Graphics</code> object with which the platform is drawn
		 */
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

	/**
	 * Constructor for <code>Level</code>. Initializes the obstacles, platforms,
	 * player, ground, obstacle coordinates, and platform coordinates. The {@link #move()}
	 * method for player is overwritten using an anonymous class to provide functionality
	 * for jumping. 
	 * @param numObstacles The number of obstacles.
	 * @param numPlatforms The number of platforms.
	 * @param obstacleCoordsBlueprint The shape of the obstacles.
	 * @param platCoordsBlueprint The shape of the platforms.
	 */
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
					canJump = false;
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

	/**
	 * Places player at initial position. Places all obstacles and platforms at
	 * their initial points
	 * @see #clearLevel()
	 */
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

	/**
	 * Clears the <code>obstacleCoords</code> and <code>platformCoords</code> 
	 * ArrayLists to prepare the level to be reset. Usually called right before
	 * {@link #setLevel()}.
	 * @see #setLevel()
	 */
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

	/**
	 * Contains all the logic for interactions between the elements.
	 * <p>
	 * If the game is not over, all the elements are painted with the 
	 * <code>brush</code> parameter. Otherwise, display the appropriate end 
	 * screen depending on if the level was passed or not. If the player collides
	 *  with the ground or with the top of any platform, it is able to jump. If
	 *  the player collides with any obstacles or any other side of a platform,
	 *  the game ends. If all obstacles and platforms are no longer on the screen,
	 *  the level is complete.
	 * @param brush
	 */
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
					if (player.isAbove(plat)) {
						player.placeOn(plat);
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

	/**
	 * Checks to see if there are any more obstacles or platforms on the screen.
	 * Used by {@link #paintLevel(Graphics)} when checking if the level is complete.
	 * @return True if there are elements still on screen. False otherwise.
	 * @see #paintLevel(Graphics)
	 */
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
	
	
	
}
