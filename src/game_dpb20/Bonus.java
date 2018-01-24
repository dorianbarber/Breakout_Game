package game_dpb20;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;

/**
 * Bonus class which extends Powerup
 * @author Dorian
 * 
 * Depends on the Paddle class
 */
public class Bonus extends Powerup{
	
	/**
	 * For constructing an instance of the Bonus class
	 * Assumption that this is the only Powerup subclass with the color GREEN
	 */
	public Bonus() {
		super(Color.GREEN);
	}

	/**
	 * The implemented powered method from the super class Powerup
	 * 
	 * Gives the paddle the powerup to get double points per block hit
	 * No null pointer check on @param paddle
	 */
	@Override
	public void powered(Paddle paddle) {
		paddle.bonusTrue();
		Timer doubleTimer = new Timer();				
		doubleTimer.schedule(new TimerTask() {
		@Override
		public void run() {
			paddle.bonusFalse();
			}
		}, 6*1000);			
	}
}
