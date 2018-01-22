package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Paddle extends Rectangle{
	public static final Color myColor = Color.CRIMSON;
	public static final int WIDTH = 50;
	public static final int HEIGHT = 5;
	public static final int SPEED = 10;
	
	private int lives = 3;
	private boolean bonusPoints = false;
	
	public Paddle() {
		super(WIDTH, HEIGHT, myColor);
	}
	
	public boolean isBonus() {
		return bonusPoints;
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
	
	public void setLives(int numb) {
		lives = numb;
	}
	
	public void powered(Powerup p) {
		switch(p.getPow()) {
			case "EXPAND":
				//expand the paddle width temporarily
				Paddle rect = this;
				rect.setWidth(WIDTH * 1.5);
				Timer expandTimer = new Timer();
				expandTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						rect.setWidth(WIDTH);
					}
				}, 6*1000);
				break;
			case "DOUBLE":
				//double score temporarily
				bonusPoints = true;
				Timer doubleTimer = new Timer();
				doubleTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						bonusPoints = false;
					}
				}, 6*1000);
				break;
			case "HEART":
				this.gainLife();
				break;
			default:
				break;
		}
	}
}
