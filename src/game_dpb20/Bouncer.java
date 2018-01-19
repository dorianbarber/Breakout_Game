package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bouncer extends Circle{
	private static Color myColor = Color.LINEN;
	private static int myRadius = 8;
	
	private int xvel = -50;
	private int yvel = -50;
	
	public Bouncer() {
		super(myRadius, myColor);
	}
	
	public void bounceX() {
		xvel = -xvel;
	}
	
	public void bounceY() {
		yvel = -yvel;
	}
	
	public int getXVel() {
		return xvel;
	}
	
	public int getYVel() {
		return yvel;
	}
	
	//checks if the ball has hit the y border (top of the screen)
	//if so it returns false
	public boolean checkYBounds() {
		return (this.getCenterY()-myRadius) >= 0;
	}
	
	//checks if the ball has hit either the right or left side of the screen
	//if so returns false
	public boolean checkXBounds(int border) {
		return ((this.getCenterX() - myRadius) >= 0 
				&& (this.getCenterX() + myRadius) <= border); 
	}
	
}
