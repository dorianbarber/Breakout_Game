package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

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
	
	public void bonusTrue() {
		bonusPoints = true;
	}
	
	public void bonusFalse() {
		bonusPoints = false;
	}
	
	public boolean paddleBallContact(Bouncer ball) {
		//checks to see if the ball intersects the paddle
		Shape intersect = Shape.intersect(this, ball);
		if(intersect.getBoundsInLocal().getWidth() != -1) {
			//checks if the ball hits the side of the paddle
			ball.bounceY();
			if(cornerTest(ball)) {
				ball.bounceX();
			} else if(makesSideContact(ball)) {
				ball.bounceX();
			}
			return true;
		}
		return false;
	}
	
	private boolean cornerTest(Bouncer ball) {
		int dToTopLeftCorner = (int) Math.hypot(ball.getCenterX()-this.getX(), ball.getCenterY()-this.getY());
		if(dToTopLeftCorner == ball.getRadius()) {
			return true;
		}
		int dToTopRightCorner = (int) Math.hypot(ball.getCenterX()-this.getX()+this.getWidth(), ball.getCenterY()-this.getY());
		if(dToTopRightCorner == ball.getRadius()) {
			return true;
		}
		int dToBotLeftCorner = (int) Math.hypot(ball.getCenterX()-this.getX(), ball.getCenterY()-this.getY()+this.getHeight());
		if(dToBotLeftCorner == ball.getRadius()) {
			return true;
		}
		int dToBotRightCorner = (int) Math.hypot(ball.getCenterX()-this.getX()+this.getWidth(), ball.getCenterY()-this.getY()+this.getHeight());
		if(dToBotRightCorner == ball.getRadius()) {
			return true; 
		}
		return false;
	}
	
	private boolean makesSideContact(Bouncer ball) {
		double lowerBound = (this.getY() - ball.getCenterY());
		double upperBound = (this.getY() + ball.getCenterY());
		return (ball.getCenterY() > lowerBound && ball.getCenterY() < upperBound)
				&& (ball.getCenterX() - ball.getRadius() < this.getX() 
						|| ball.getCenterX() + ball.getRadius() > this.getX() + this.getWidth());
	}
}
