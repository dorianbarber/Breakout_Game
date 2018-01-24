package game_dpb20;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Bouncer extends Circle{
	public static final Color COLOR = Color.LINEN;
	public static final int RADIUS = 8;
	
	private int xvel = -100;
	private int yvel = -100;
	
	
	public Bouncer() {
		super(RADIUS, COLOR);
	}
	
	public void bounceX() {
		xvel = -xvel;
	}
	
	public void bounceY() {
		yvel = -yvel;
	}
	
	public int getXVel() {
		return xvel;
	}
	
	public int getYVel() {
		return yvel;
	}
	
	//sets the ball on top of the paddle
	public void reset(Paddle paddle) {
		this.setCenterX(paddle.getX() + paddle.getWidth()/2);
		this.setCenterY(paddle.getY() - this.getRadius());
	}
	
	public boolean checkYBounds() {
		return (this.getCenterY()-RADIUS) >= 0;
	}
	
	public boolean checkXBounds(int border) {
		return ((this.getCenterX() - RADIUS) >= 0 
				&& (this.getCenterX() + RADIUS) <= border); 
	}
	

	
	//additional adjustments specifically for the myTarget instance
	// not to be used for the myBall instance
	public void changeToTarget() {
		yvel = 0;
		this.setStroke(Color.WHITE);
		this.setFill(Color.RED);
		this.setRadius(RADIUS*2);
	}
	
	public boolean targetBallContact(Bouncer ball) {
		Shape targetAndBall = Shape.intersect(this, ball);
		return targetAndBall.getBoundsInLocal().getWidth() != -1;
	}
	
}
