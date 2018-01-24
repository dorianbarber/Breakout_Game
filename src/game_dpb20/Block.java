package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Purpose is to represent blocks in the Breakout game
 * Assumption that the constructor is used otherwise the 
 * blocks would not show up in the game
 * 
 * @author Dorian
 *
 */
public class Block extends Rectangle{
	public static final int WIDTH = 50;
	public static final int HEIGHT = 30;
	public static final Color BORDER_COLOR = Color.BLACK;
	
	private Paint deadColor;
	private int hitNumber;
	private boolean isBroke;
	private boolean isPermanent = false;
	private boolean hasPowerup = false;
	private Powerup pow;
	private Powerup[] possiblePows ={
		new Heart(),
		new Bonus(),
		new Expand()
	};
	
	/**
	 * Construct block with WIDTH and HEIGHT and 
	 * number of hits equal to @param numb as well as
	 * the color it turns when the block dies --> (assumes it will be the background color of the Breakout scene)
	 * @param back
	 */
	public Block(int numb, Paint back) {
		super(WIDTH, HEIGHT);
		hitNumber = numb;
		isBroke = false;
		this.typeOfBlock();
		this.setFill(getColor());
		this.setStroke(BORDER_COLOR);
		deadColor = back;
	}
	
	/**
	 * @return HEIGHT of the block
	 */
	public static int getBlockHeight() {
		return HEIGHT;
	}
	
	/**
	 * @return WIDTH of the block
	 */
	public static int getBlockWidth() {
		return WIDTH;
	}
	
	/**
	 * Changes the color of the block depending on how many hits it has left
	 * The reason why this is not abstracted is because the blocks
	 * change color as they get hit.
	 * @return the color of the block
	 */
	private Color getColor() {
		if(hitNumber == 1) {
			return Color.AQUA;
		}
		else if(hitNumber == 2) {
			return Color.AQUAMARINE;
		}
		else if(hitNumber == 3) {
			return Color.DARKMAGENTA;
		}
		else if(hitNumber <= -1) {
			return Color.BLACK;
		}
		return Color.DARKSALMON;
	}
	
	/**
	 * Checks if the block is broken and
	 * @return isBroke
	 */
	public boolean checkBroke() {
		if(hitNumber == 0) {
			isBroke = true;
		}
		return isBroke;
	}
	
	/**
	 * Sets the block to the presumed background color of the
	 * Breakout.java scene game
	 */
	private void deadBlock() {
		this.setFill(deadColor);
		this.setStroke(deadColor);
	}
	
	/**
	 * used to figure out if the ball can break the block
	 * @return whether the boolean isPermanent
	 */
	public boolean isPerm() {
		return isPermanent;
	}
	
	/**
	 * @return true if it contains a powerup and gets broken
	 * @return false if it is only one or neither
	 */
	public boolean isPowed() {
		return hasPowerup && isBroke;
	}
	
	/**
	 * @return the Powerup attached to the block if it exists
	 */
	public Powerup getPowerup() {
		return pow;
	}
	
	/**
	 * Checks which side of the block gets hit and makes the 
	 * ball bounce in the corresponding way
	 * Updates hitNumber, the color, and checks whether the block has broken
	 * @param ball 
	 */
	public void ballContact(Bouncer ball) {
		//checks if the ball has hit the top or bottom of the block
		if(ball.getCenterX() > this.getX() && ball.getCenterX() < this.getX() + this.getWidth()) {
			ball.bounceY();
		}
		//checks if the ball has hit the sides of the block
		else if(ball.getCenterY() > this.getY() && ball.getCenterY() < this.getY() + this.getHeight()) {
			ball.bounceX();
		}
		else {
			ball.bounceX();
			ball.bounceY();
		}
		hitNumber -= 1;
		this.setFill(getColor());
		if(checkBroke()) {
			deadBlock();
		}
	}
	
	/**
	 * Selects the type of block during instantiation (called in the constructor)
	 * There is a 20% chance it will be permanent
	 * 			  30% chance it will have a powerup
	 * 			  50% chance it will be a normal block
	 */
	private void typeOfBlock() {
		int rand = (int)(Math.random()*10);
		if(rand == 4 || rand == 5) {
			isPermanent = true;
			hitNumber = -1;
		}
		if(rand >= 1 && rand <= 3) {
			int numb = (int) (Math.random()*possiblePows.length);
			System.out.println(possiblePows.length);
			hasPowerup = true;
			pow = possiblePows[numb];
		}
	}
}
