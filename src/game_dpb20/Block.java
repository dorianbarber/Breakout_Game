package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Block extends Rectangle{
	private static final int width = 50;
	private static final int height = 30;
	private static Color borderColor = Color.BLACK;
	private static Paint backGround;
	
	private int hitNumber;
	private boolean isBroke;
	private boolean isPermanent = false;
	private boolean hasPowerup = false;
	private Powerup pow;
	
	
	public Block(int numb, Paint back) {
		super(width, height);
		hitNumber = numb;
		isBroke = false;
		this.typeOfBlock();
		this.setFill(getColor());
		this.setStroke(borderColor);
		backGround = back;
	}
	
	
	public static int getBlockHeight() {
		return height;
	}
	
	public static int getBlockWidth() {
		return width;
	}
	
	//Color of the block is dependent on the number of hits
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
		this.setFill(backGround);
		this.setStroke(backGround);
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
	
	//uses a random functon to decide whether the block is permanent
	// 20% chance it will be permanent
	// 30% chance it will have a powerup
	// 50% chance it will be normal
	private void typeOfBlock() {
		int rand = (int)(Math.random()*10);
		if(rand == 4 || rand == 5) {
			isPermanent = true;
			hitNumber = -1;
		}
		else if(rand >= 1 && rand <= 3) {
			hasPowerup = true;
			pow = new Powerup(rand);
		}
	}
}
