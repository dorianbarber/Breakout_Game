package game_dpb20;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Breakout extends Application{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int SIZE = 500;
	
	public static final Paint BACKGROUND = Color.BURLYWOOD;
	
	public static final int paddleX = SIZE/2;
	public static final int paddleY = SIZE - 50;
	
	private Scene myScene;
	private Paddle myPaddle;
	private Bouncer myBall;
	
	private boolean gameStart;
	
	
	public void start(Stage stage) {
		//sets up scene
		myScene = setupGame(SIZE, SIZE, BACKGROUND);
		stage.setScene(myScene);
		stage.show();
		
		//sets up game loop
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline(); 
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
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
		
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return scene;
	}
	
	private void step (double elapsedTime) {
		//checks to see if the player has started the game
		//which prompts the ball to start moving
		if(gameStart) {
			if(!myBall.checkXBounds(SIZE)) {
				myBall.bounceX();
			}
			myBall.setCenterX(myBall.getCenterX() + myBall.getXVel() * elapsedTime);
			
			if(!myBall.checkYBounds()) {
				myBall.bounceY();
			}
			myBall.setCenterY(myBall.getCenterY() + myBall.getYVel() * elapsedTime);
		}
		
		//checks to see if the ball intersects the paddle
		Shape intersect = Shape.intersect(myPaddle, myBall);
		if(intersect.getBoundsInLocal().getWidth() != -1) {
			myBall.bounceY();
		}
		
	}
	
	private void handleKeyInput(KeyCode code) {
		//the additional boolean checks for if the paddle is about to go off the screen
		if(code == KeyCode.RIGHT
				&& myPaddle.getX() + myPaddle.getWidth() <= SIZE) {
			myPaddle.setX(myPaddle.getX() + myPaddle.getSpeed());
		}
		else if(code == KeyCode.LEFT 
				&& myPaddle.getX() >= 0) {
			myPaddle.setX(myPaddle.getX() - myPaddle.getSpeed());
		}
		//checks to see if the player has started the game
		else if(code == KeyCode.UP) {
			gameStart = true;
		}
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}
