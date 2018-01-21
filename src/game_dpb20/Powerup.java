package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Powerup extends Circle{
	private static final int myRadius = 5;
	private static final int yvel = 60;
	
	private Color myPowerup;
	
	public Powerup(int numb) {
		super(myRadius);
		//Gives a heart
		if(numb == 0) {
			myPowerup = Color.PINK;
		}
		//Expands the paddle temporarily
		else if(numb == 1) {
			myPowerup = Color.CRIMSON;
		}
		//Gives a powerball
		else if(numb == 2) {
			myPowerup = Color.GREEN;
		}
		this.setFill(myPowerup);
	}
}
