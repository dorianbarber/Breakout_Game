package game_dpb20;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;

public class Bonus extends Powerup{
	
	public Bonus() {
		super(Color.GREEN, "double");
	}

	@Override
	public void powered(Paddle paddle) {
		paddle.bonusTrue();
		Timer doubleTimer = new Timer();				doubleTimer.schedule(new TimerTask() {
		@Override
		public void run() {
			paddle.bonusFalse();
			}
		}, 6*1000);			
	}
}
