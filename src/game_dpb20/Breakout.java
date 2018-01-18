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
	
	public static final int paddleX = SIZE/2;
	public static final int paddleY = SIZE - 50;
	
	private Scene myScene;
	private Paddle myPaddle;
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
		myBall.setCenterX(paddleX);
		myBall.setCenterY(paddleY - myBall.getRadius());
		
		
		myPaddle = new Paddle();
		myPaddle.setX(paddleX - myPaddle.getWidth()/2);
		myPaddle.setY(paddleY);
		
		root.getChildren().add(myBall);
		root.getChildren().add(myPaddle);
		return scene;
	}
	
	
	
	
	
	public static void main (String[] args) {
		launch(args);
	}
}
