package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * The Bouncer class which represents the ball that 
 * bounces around in the Breakout game 
 * 
 * Assumes that the color of the Bouncer does not
 * conflict with any other colors in the Breakout game
 * 
 * Depends on the Paddle class
 * @author Dorian
 *
 */
public class Bouncer extends Circle{
	public static final Color COLOR = Color.LINEN;
	public static final int RADIUS = 8;
	
	private int xvel = -100;
	private int yvel = -100;
	
	/**
	 * Constructs a Circle with RADIUS and COLOR
	 */
	public Bouncer() {
		super(RADIUS, COLOR);
	}
	
	/**
	 * Changes the velocity in the x direction
	 */
	public void bounceX() {
		xvel = -xvel;
	}
	
	/**
	 * Changes the velocity in the y direction
	 */
	public void bounceY() {
		yvel = -yvel;
	}
	
	/**
	 * @return velocity in the x direction
	 */
	public int getXVel() {
		return xvel;
	}
	
	/**
	 * @return velocity in the y direction
	 */
	public int getYVel() {
		return yvel;
	}
	
	/**
	 * Uses @param paddle to position the Bouncer in
	 * the middle of the Paddle object
	 */
	public void reset(Paddle paddle) {
		this.setCenterX(paddle.getX() + paddle.getWidth()/2);
		this.setCenterY(paddle.getY() - this.getRadius());
	}
	
	/**
	 * Checks if it hits the top of the scene
	 * @return false if it comes into contact with the border
	 */
	public boolean checkYBounds() {
		return (this.getCenterY()-RADIUS) >= 0;
	}
	
	/**
	 * Checks if it hits the sides of the screen
	 * @param border dependent on how big the scene is 
	 * @return false if it comes into contact with the sides
	 */
	public boolean checkXBounds(int border) {
		return ((this.getCenterX() - RADIUS) >= 0 
				&& (this.getCenterX() + RADIUS) <= border); 
	}
	

	
	/**
	 * Solely to be used to instantiate the target
	 * Simply the colors change, both velocities are set to 0, and the radius increases
	 */
	public void changeToTarget() {
		yvel = 0;
		xvel = 0;
		this.setStroke(Color.WHITE);
		this.setFill(Color.RED);
		this.setRadius(RADIUS*2);
	}
	
	/**
	 * Decides whether or not the ball comes into contact with the target
	 * @param ball represents the bouncer in the Breakout game
	 * @return whether or not they are in contact
	 */
	public boolean targetBallContact(Bouncer ball) {
		Shape targetAndBall = Shape.intersect(this, ball);
		return targetAndBall.getBoundsInLocal().getWidth() != -1;
	}
	
}
