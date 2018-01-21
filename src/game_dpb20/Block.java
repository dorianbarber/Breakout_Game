package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Block extends Rectangle{
	private static final int width = 50;
	private static final int height = 30;
	private static Color borderColor = Color.BLACK;
	
	private int hitNumber;
	private boolean isBroke;
	private boolean isPermanent = false;
	private boolean hasPowerup = false;
	private Powerup pow;
	
	
	public Block(int numb) {
		super(width, height);
		hitNumber = numb;
		isBroke = false;
		this.typeOfBlock();
		this.setFill(getColor());
		this.setStroke(borderColor);
	}
	
	
	public static int getBlockHeight() {
		return height;
	}
	
	public static int getBlockWidth() {
		return width;
	}
	public void hit() {
		hitNumber -= 1;
		this.setFill(getColor());
	}
	
	
	//Color of the block is dependent on the number of hits
	public Color getColor() {
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
		return null;
	}
	
	public boolean checkBroke() {
		if(hitNumber == 0) {
			isBroke = true;
		}
		return isBroke;
	}
	
	public void deadBlock() {
		this.setFill(Color.BURLYWOOD);
		this.setStroke(Color.BURLYWOOD);
	}
	
	public boolean isPerm() {
		return isPermanent;
	}
	
	//uses a random functon to decide whether the block is permanent
	// 20% chance it will be permanent
	public void typeOfBlock() {
		int rand = (int)(Math.random()*10);
		if(rand == 1 || rand == 2) {
			isPermanent = true;
			hitNumber = -1;
		}
		else if(rand >= 3 && rand <= 5) {
			hasPowerup = true;
		}
	}
}
