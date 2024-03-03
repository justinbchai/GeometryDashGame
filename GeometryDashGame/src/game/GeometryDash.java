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

			
			new Point(1975, 400),
			new Point(2175, 400),
			new Point(1975, 400),
			new Point(2050, 400),
			new Point(2100, 400),
			new Point(2175, 400),


			new Point(2950, 375),

			new Point(3150, 400),
			new Point(3200, 400),

			new Point(3500, 375),

			new Point(3950, 400),
			new Point(4650, 400), 

			new Point(4300, 325),

			new Point(4750, 400), 
			new Point(4800, 400), 
			new Point(4850, 400), 
			new Point(4900, 400), 
			new Point(4950, 400), 
			new Point(5000, 400), 
			new Point(5050, 400), 
			new Point(5100, 400), 
			new Point(5150, 400), 
			new Point(5200, 400), 
			new Point(5250, 400), 
			new Point(5300, 400), 
			
			new Point(5350, 400),
			new Point(5400, 400),
			new Point(5450, 400), 
			new Point(5500, 400),
			new Point(5550, 400),
			new Point(5600, 400),
			new Point(5650, 400), 
			
			new Point(5700, 400),
			new Point(5750, 400),
			new Point(5800, 400),
			new Point(5850, 400),
			new Point(5900, 400),
			new Point(5950, 400),
		

			new Point(6150, 325),
			new Point(6200, 325),
			new Point(6250, 325), 
			new Point(6300, 325),

			new Point(6700, 350),
			new Point(6750, 350),
			new Point(6800, 350), 
			new Point(6850, 350),
		};
		Point[] stereoMadnessPlat = new Point[]{
			new Point(1800, 400),
			new Point(1975, 350),
			new Point(2175, 300),

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

			new Point(3950, 350),
			new Point(4000, 350),
			new Point(4050, 350), 
			new Point(4100, 350),
			new Point(4150, 350),
			new Point(4200, 350),
			new Point(4250, 350), 
			new Point(4300, 350),
			new Point(4350, 350),
			new Point(4400, 350),
			new Point(4450, 350), 
			new Point(4500, 350),
			new Point(4550, 350),
			new Point(4600, 350),
			new Point(4650, 350), 

			new Point(4850, 300), 
			new Point(5050, 275), 
			new Point(5250, 250), 
			new Point(5450, 225), 
			new Point(5650, 200), 


			new Point(5850, 350),
			new Point(5900, 350),
			new Point(5950, 350), 
			new Point(6000, 350),
			new Point(6050, 350), 
			new Point(6100, 350),

			
			new Point(6200, 275),
			new Point(6250, 275), 
			

			new Point(6350, 350),
			new Point(6400, 350),
			new Point(6450, 350), 
			new Point(6500, 350),
			new Point(6550, 350),
			new Point(6600, 350),
			new Point(6650, 350),

			new Point(6750, 275),
			new Point(6800, 275), 

			
			new Point(6900, 350),
			new Point(6950, 350), 
			new Point(7000, 350),
			new Point(7050, 350), 
			new Point(7100, 350),
			new Point(7250, 350),
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
		level = new Level(stereoMadnessObst, stereoMadnessPlat);
		
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