package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Powerup extends Circle{
	public static final int RADIUS = 6;
	public static final int Y_VELOCITY = 60;

	private enum options {HEART, EXPAND, DOUBLE};
	private String thePowerup;
	private Color powColor;	
	
	public Powerup(int numb) {
		super(RADIUS);
		//Gives a heart
		if(numb == 1) {
			powColor = Color.CRIMSON;
			thePowerup = options.HEART.toString();
		}
		//Expands the paddle temporarily
		else if(numb == 2) {
			powColor = Color.DARKBLUE;
			thePowerup = options.EXPAND.toString();
		}
		//Gives a powerball
		else if(numb == 3) {
			powColor = Color.GREEN;
			thePowerup = options.DOUBLE.toString();
		}
		this.setFill(powColor);
	}
	
//	public int getVel() {
//		return yvel;
//	}
	
	public String getPow() {
		return thePowerup;
	}
}
