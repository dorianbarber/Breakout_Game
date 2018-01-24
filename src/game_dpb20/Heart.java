package game_dpb20;

import javafx.scene.paint.Color;

public class Heart extends Powerup{
	
	public Heart() {
		super(Color.CRIMSON);	
	}
	
	@Override
	public void powered(Paddle paddle) {
		paddle.gainLife();
	}
}
