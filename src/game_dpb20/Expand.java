package game_dpb20;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Expand extends Powerup{
	
	public Expand() {
		super(Color.DARKBLUE, "expand");
	}
	
	public void powered(Expand p, Paddle paddle) {
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
