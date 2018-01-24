package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * This Powerup class acts as a skeleton for all powerups and 
 * the abstraction makes it easy to create new powerups to add
 * 
 * There is an assumption that the subclasses will have different
 * colors so that the player can differentiate the powerups
 * @author Dorian
 *
 */
public abstract class Powerup extends Circle{
	public static final int RADIUS = 6;
	public static final int Y_VELOCITY = 60;

	private boolean used = false;
	
	/**
	 * Constructs a new powerup with a specific color
	 * 
	 * @param color
	 */
	public Powerup(Color color) {
		super(RADIUS, color);
	}
	
	/**
	 * Means that the power up has been used
	 */
	public void powUsed() {
		used = true;
	}
	
	/**
	 * To find out whether or not the powerup has been used yet
	 * 
	 * @return the used field variable
	 */
	public boolean isUsed() {
		return used;
	}
	
	/**
	 * Initializes the position of the powerup when the block breaks
	 * 
	 * No null pointer check on @param brokeBlock
	 */
	public void setPos(Block brokeBlock) {
		setCenterX(brokeBlock.getX() + brokeBlock.getWidth()/2);
		setCenterY(brokeBlock.getY() + brokeBlock.getHeight()/2);
	}
	
	/**
	 * To be implemented by each power up
	 * This is what gets called when the paddle hits a powerup
	 * in each subclass paddle is a required variable to have the
	 * powerup actually affect it
	 * 
	 * @param paddle referenced in Breakout.java as myPaddle
	 */
	public abstract void powered(Paddle paddle); 
}
