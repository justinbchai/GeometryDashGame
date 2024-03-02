package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Level {
	Player player;
	Ground ground;
	Obstacle[] obstacles;
	Platform[] platforms;
	ArrayList<Point> obstacleCoords, platformCoords;
	boolean moreObstacles, morePlats, gameOver, gameWon;
	Point[] obstacleCoordsBlueprint, platCoordsBlueprint;
	TextElement endScreen;

	public Level(Obstacle[] obstacles, Platform[] platforms, Point[] obstacleCoordsBlueprint,
			Point[] platCoordsBlueprint) {
		this.obstacles = obstacles;
		this.platforms = platforms;
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
		player = new Player();
		ground = new Ground();
		gameOver = false;

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
			obstacles[i].position = obstacleCoords.remove(0);

		}
		for (int i = 0; i < platforms.length; i++) {
			platforms[i].position = platformCoords.remove(0);
		}
		System.out.println(obstacleCoords.toString());
	}

	public void paintLevel(Graphics brush) {

		if (!gameOver) {
			player.paint(brush);
			ground.paint(brush);

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
					if (!player.getIsFalling() && !player.isWithinPlatformWidth(plat)
							&& player.position.y < Player.BASE_HEIGHT) {
						player.setIsFalling(true);
					}
					if (player.collides(plat)) {
						if (player.isAbove(plat)) {
							player.placeOn(plat);
							player.setIsFalling(false);
							player.setVel(0);
						} else {
							this.gameOver = true;
						}
					}
					if (plat.findRightmostPoint() <= 0) {
						if (platformCoords.size() > 0) {
							plat.position = platformCoords.remove(0);
						} else {
							morePlats = false;
						}
					}
					plat.paint(brush);
				}
				if (!moreObstacles && !morePlats && !elementsOnScreen() && !gameOver) {
					gameOver = true;
					gameWon = true;
				}
		} else {
			/*Font currentFont = brush.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 8.5F);
			brush.setFont(newFont);*/
			if (gameWon) {
				endScreen.displayText("LEVEL COMPLETE!!!", 25, 300, brush, 75F);
			} else {
				endScreen.displayText("GAME OVER!!!", 50, 300, brush, 100F);
			}
			obstacleCoords.clear();
			platformCoords.clear();

		}
	}

	public Player getPlayer() {
		return this.player;
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

}
