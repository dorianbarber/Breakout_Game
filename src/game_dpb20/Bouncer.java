package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bouncer extends Circle{
	private static Color myColor = Color.AQUA;
	private static int myRadius = 8;
	
	private int xvel = 5;
	private int yvel = 5;
	
	public Bouncer() {
		super(myRadius, myColor);
	}
	
	public void bounceX() {
		xvel = -xvel;
	}
	
	public void bounceY() {
		yvel = -yvel;
	}
	
	public boolean checkYBounds() {
		return (this.getCenterY()-myRadius) >= 0;
	}
	
	public boolean checkXBounds(int border) {
		return (this.getCenterX() - myRadius) >= 0 
				&& (this.getCenterX() + myRadius) <= border; 
	}
	
}
