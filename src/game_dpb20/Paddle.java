package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle{
	private static Color myColor = Color.CRIMSON;
	private static final int width = 50;
	private static final int height = 5;
	
	
	
	public Paddle() {
		super(width, height, myColor);
	}
}
