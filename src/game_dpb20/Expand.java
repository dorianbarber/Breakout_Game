package game_dpb20;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;

/**
 * Expand class which extends Powerup
 * @author Dorian
 *
 * Depends on the Paddle class
 */
public class Expand extends Powerup{
	
	/**
	 * For constructing an instance of the Expand class
	 * Assumption that this is the only Powerup subclass with the color DARKBLUE
	 */
	public Expand() {
		super(Color.DARKBLUE);
	}
	
	/**
	 * The implemented powered method from the super class Powerup
	 * 
	 * Temporarily makes the paddle wider
	 * No null pointer check on @param paddle
	 */
	@Override
	public void powered(Paddle paddle) {
		Paddle rect = paddle;
		rect.setWidth(paddle.getWidth() * 1.5);
		Timer expandTimer = new Timer();
		expandTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				rect.setWidth(paddle.getWidth());
			}
		}, 6*1000);
	}
}
