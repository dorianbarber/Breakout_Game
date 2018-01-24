package game_dpb20;

import javafx.scene.paint.Color;

public class Heart extends Powerup{
	
	public Heart() {
		super(Color.CRIMSON, "heart");	
	}
	
	
	public void powered(Heart p, Paddle paddle) {
		paddle.gainLife();
	}
}
