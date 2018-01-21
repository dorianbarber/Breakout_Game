package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle{
	private static final Color myColor = Color.CRIMSON;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 5;
	private static final int SPEED = 10;
	
	private int lives = 3;
	
	
	
	public Paddle() {
		super(WIDTH, HEIGHT, myColor);
	}
	
	
	public int getSpeed() {
		return SPEED;
	}
	
	public void loseLife() {
		lives -= 1;
	}
	
	public void gainLife() {
		lives += 1;
	}
	
	public int getLives() {
		return lives;
	}
}
