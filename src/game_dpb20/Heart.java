package game_dpb20;

import javafx.scene.paint.Color;

/**
 * Heart class which extends Powerup
 * @author Dorian
 *
 * Depends on the Paddle class
 */
public class Heart extends Powerup{
	
	/**
	 * For constructing an instance of the Heart class
	 * Assumption that this is the only Powerup class with the color CRIMZON
	 */
	public Heart() {
		super(Color.CRIMSON);	
	}
	
	/**
	 * The implemented powered method from the super class Powerup
	 * 
	 * Gives the paddle another life
	 * No null pointer check on @param paddle
	 */
	@Override
	public void powered(Paddle paddle) {
		paddle.gainLife();
	}
}
