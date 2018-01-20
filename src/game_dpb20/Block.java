package game_dpb20;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Block extends Rectangle{
	private static final int width = 50;
	private static final int height = 30;
	private static final Color blockColor = Color.AQUA;
	
	private int hitNumber;
	private boolean isBroke;
	
	public Block(int numb) {
		hitNumber = numb;
		isBroke = false;
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
}
