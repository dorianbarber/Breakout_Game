package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Block extends Rectangle{
	private static final int width = 50;
	private static final int height = 30;
	private static Color blockColor = Color.AQUA;
	private static Color borderColor = Color.BLACK;
	
	private int hitNumber;
	private boolean isBroke;
	
	
	public Block(int numb) {
		super(width, height, blockColor);
		blockColor = Color.AQUA;
		borderColor = Color.BLACK;
		this.setStroke(borderColor);
		hitNumber = numb;
		isBroke = false;
	}
	
	
	public static int getBlockHeight() {
		return height;
	}
	
	public static int getBlockWidth() {
		return width;
	}
	public void hit() {
		hitNumber -= 1;
	}
	
	public boolean checkBroke() {
		if(hitNumber <= 0) {
			isBroke = true;
		}
		return isBroke;
	}
	
	public void deadBlock() {
		blockColor = Color.BURLYWOOD;
		borderColor = Color.BURLYWOOD;
	}
}
