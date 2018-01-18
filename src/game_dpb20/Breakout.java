package game_dpb20;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Breakout extends Application{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final int SIZE = 500;
	
	public static final Paint BACKGROUND = Color.BURLYWOOD;
	public static final Paint BALL_COLOR = Color.CRIMSON;
	public static final Paint PADDLE_COLOR = Color.AQUA;
	
	private Scene myScene;
	private Rectangle myPaddle;
	private Bouncer myBall;
	
	
	public void start(Stage stage) {
		myScene = setupGame(SIZE, SIZE, BACKGROUND);
		stage.setScene(myScene);
		stage.show();
	}
	
	private Scene setupGame(int width, int height, Paint background) {
		Group root = new Group();
		Scene scene = new Scene(root, width, height, background);
		myBall = new Bouncer();
		root.getChildren().add(myBall);
		return scene;
	}
	
	
	
	
	
	public static void main (String[] args) {
		launch(args);
	}
}
