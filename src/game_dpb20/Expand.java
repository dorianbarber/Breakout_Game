package game_dpb20;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;

public class Expand extends Powerup{
	
	public Expand() {
		super(Color.DARKBLUE);
	}
	
	@Override
	public void powered(Paddle paddle) {
		Paddle rect = paddle;
		rect.setWidth(paddle.getWidth() * 1.5);
		Timer expandTimer = new Timer();
		expandTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				rect.setWidth(paddle.getWidth());
			}
		}, 6*1000);
	}
}
