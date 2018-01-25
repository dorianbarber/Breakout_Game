package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

/**
 * To be used as the paddle in the Breakout game
 * Contains only one speed since it can only move in the x direction
 * 
 * Depends on the Bouncer class
 * Assumes that the background color of the Breakout game is not CRIMZON
 * @author Dorian
 *
 */
public class Paddle extends Rectangle{
	public static final Color COLOR = Color.CRIMSON;
	public static final int WIDTH = 50;
	public static final int HEIGHT = 5;
	public static final int SPEED = 10;
	
	private int lives = 3;
	private boolean bonusPoints = false;
	
	/**
	 * Constructs a rectangle with WIDTH, HEIGHT, COLOR
	 */
	public Paddle() {
		super(WIDTH, HEIGHT, COLOR);
	}
	
	/**
	 * @return SPEED
	 */
	public int getSpeed() {
		return SPEED;
	}
	
	/**
	 * The paddle loses a life
	 */
	public void loseLife() {
		lives -= 1;
	}
	
	/**
	 * The paddle gains a life
	 */
	public void gainLife() {
		lives += 1;
	}
	
	/**
	 * @return the number of lives the paddle has
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Used for reseting the number of lives with the R cheat code
	 * @param numb sets the number of lives
	 */
	public void setLives(int numb) {
		lives = numb;
	}
	
	/**
	 * Used for reseting the the paddle width to its
	 * original size
	 */
	public int getStandardWidth() {
		return WIDTH;
	}
	/**
	 * @return if it has the bonus points powerup
	 */
	public boolean isBonus() {
		return bonusPoints;
	}
	
	/**
	 * Sets the bonus points to on
	 */
	public void bonusTrue() {
		bonusPoints = true;
	}
	
	/**
	 * Sets the bonus points to off
	 */
	public void bonusFalse() {
		bonusPoints = false;
	}
	
	/**
	 * Goes through two different cases where the ball
	 * makes contact with the paddle
	 * either on the top or on the sides/corners 
	 * (ideally behaves the same way when it hits the sides 
	 * as it does with the corners)
	 * 
	 * @param ball represents the ball in the breakout game
	 * @return whether or not the paddle makes contact with the ball
	 */
	public boolean paddleBallContact(Bouncer ball) {
		//checks to see if the ball intersects the paddle
		Shape intersect = Shape.intersect(this, ball);
		if(intersect.getBoundsInLocal().getWidth() != -1) {
			//checks if the ball hits the top of the paddle
			if(ball.getCenterX() > this.getX() && ball.getCenterX() < this.getX() + this.getWidth()) {
				ball.bounceY();
			}
			else {
				ball.bounceX();
				ball.setCenterX(ball.getCenterX() + ball.getXVel()*(1.0/30.));
				ball.bounceY();
			}
			return true;
		}
		return false;
	}
}
