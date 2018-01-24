package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Block extends Rectangle{
	public static final int WIDTH = 50;
	public static final int HEIGHT = 30;
	public static final Color BORDER_COLOR = Color.BLACK;
	
	private Paint background;
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
	
	
	public Block(int numb, Paint back) {
		super(WIDTH, HEIGHT);
		hitNumber = numb;
		isBroke = false;
		this.typeOfBlock();
		this.setFill(getColor());
		this.setStroke(BORDER_COLOR);
		background = back;
	}
	
	
	public static int getBlockHeight() {
		return HEIGHT;
	}
	
	public static int getBlockWidth() {
		return WIDTH;
	}
	
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
	
	public void hit() {
		hitNumber -= 1;
		this.setFill(getColor());
	}
	
	public boolean checkBroke() {
		if(hitNumber == 0) {
			isBroke = true;
		}
		return isBroke;
	}
	
	public void deadBlock() {
		this.setFill(background);
		this.setStroke(background);
	}
	
	public boolean isPerm() {
		return isPermanent;
	}
	
	public boolean isPowed() {
		return hasPowerup && isBroke;
	}
	
	public Powerup getPowerup() {
		return pow;
	}
	
	//uses a random functon to decide what kind of block
	// 20% chance it will be permanent
	// 30% chance it will have a powerup
	// 50% chance it will be normal
	private void typeOfBlock() {
		int rand = (int)(Math.random()*10);
		if(rand == 4 || rand == 5) {
			isPermanent = true;
			hitNumber = -1;
		}
		if(rand >= 1 && rand <= 3) {
			int numb = (int) Math.random()*possiblePows.length;
			hasPowerup = true;
			pow = possiblePows[numb];
		}
	}
}
