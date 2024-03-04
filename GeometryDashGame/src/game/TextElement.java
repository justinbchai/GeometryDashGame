package game;

import java.awt.Graphics;

/**
 * <code>TextElement</code> is a functional interface used to display screens containing
 * text (level complete/game over).
 * @author Justin Chai
 *
 */
public interface TextElement {
	/**
	 * Displays the text desired on the canvas.
	 * @param string The text to display.
	 * @param xcoord The x coordinate of the text.
	 * @param ycoord The y coordinate of the text.
	 * @param brush The <code>Graphics</code> object which draws the text.
	 * @param fontSize The font size of the text.
	 */
	public void displayText(String string, int xcoord, int ycoord, Graphics brush, float fontSize);
}
