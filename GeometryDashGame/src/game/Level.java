package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Level{
	Player player;
	Ground ground;
	Obstacle[] obstacles;
	Platform[] platforms;
	ArrayList<Point> obstacleCoords;
	ArrayList<Point> platformCoords;
	boolean moreObstacles;
	boolean morePlats;
	boolean gameOver;

    public Level(Obstacle[] obstacles, Platform[] platforms, ArrayList<Point> obstacleCoords, ArrayList<Point> platformCoords) {
    	this.obstacles = obstacles;
    	this.platforms = platforms;
    	this.obstacleCoords = obstacleCoords;
    	this.platformCoords = platformCoords;
    	this.moreObstacles = true;
    	this.morePlats = true;
    	this.player = new Player();
    	this.ground = new Ground();
    	this.gameOver = false;
    }
    
    public void paintLevel(Graphics brush) {
    	if (!gameOver) {
    		player.paint(brush);
        	ground.paint(brush);
        	if (moreObstacles) {
        		for (Obstacle obstacle : obstacles) {
        			if (player.collides(obstacle)) {
        				this.gameOver = true;
        			}
            		if (obstacle.findRightmostPoint() < 0) {
            			reassignPoint(obstacle, obstacleCoords);
            		}
            		obstacle.paint(brush);
            	}
        	}
        	if (morePlats) {
        		for (Platform plat : platforms) {
        			if (!player.getIsFalling() && !player.isWithinPlatformWidth(plat) && player.position.y < Player.BASE_HEIGHT) {
        				player.setIsFalling(true);
        			}
        			if (player.collides(plat)) {
        				if(player.isAbove(plat)) {
        					player.placeOn(plat);
        					player.setIsFalling(false);
        					player.setVel(0);
        				} else {
        					this.gameOver = true;
        				}
        			}
            		if (plat.findRightmostPoint() < 0) {
            			reassignPoint(plat, platformCoords);
            		}
            		plat.paint(brush);
            	}
        	}
    	} else {
    		Font currentFont = brush.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 8.5F);
			brush.setFont(newFont);
			brush.drawString("GAME OVER!!!", 50, 300);
    	}
    	
    }
    
    private void reassignPoint(Polygon poly, ArrayList<Point> points) {
    	if (points.size() <= 0) {
    		if (poly instanceof Obstacle) {
    			moreObstacles = false;
    		} else {
    			morePlats = false;
    		}
    	} else {
    		poly.position = points.get(0);
        	points.remove(0);
    	}
    }
    
}
