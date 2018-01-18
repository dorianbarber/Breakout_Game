package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle{
	private static Color myColor = Color.LIGHTCORAL;
	private static final int width = 30;
	private static final int height = 5;
	
	private int xPos;
	private int yPos;
	
	
	public Paddle() {
		super(width, height, myColor);
	}
}
