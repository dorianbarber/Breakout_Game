package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Powerup extends Circle{
	public static final int RADIUS = 6;
	public static final int Y_VELOCITY = 60;

	private boolean used = false;
	
	/**
	 * Constructs a new powerup with a specific color and name
	 * 
	 * @param color
	 * @param powName
	 */
	public Powerup(Color color) {
		super(RADIUS, color);
	}
	
	public void powUsed() {
		used = true;
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public abstract void powered(Paddle paddle); 
}
