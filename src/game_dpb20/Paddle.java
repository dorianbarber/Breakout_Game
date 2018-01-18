package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle{
	private static Color myColor = Color.CRIMSON;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 5;
	
	private static final int SPEED = 5;
	
	
	public Paddle() {
		super(WIDTH, HEIGHT, myColor);
	}
	
	public int getSpeed() {
		return SPEED;
	}
}
