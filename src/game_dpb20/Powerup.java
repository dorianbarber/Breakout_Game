package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Powerup extends Circle{
	public static final int RADIUS = 6;
	public static final int Y_VELOCITY = 60;

	private String thePowerup;
	private boolean used = false;
	
	public Powerup(Color color, String powName) {
		super(RADIUS, color);
		thePowerup = powName;
	}
	
	public String getPow() {
		return thePowerup;
	}
	
	public void powUsed() {
		used = true;
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public void powered(Powerup p, Paddle paddle) {
	}
}
