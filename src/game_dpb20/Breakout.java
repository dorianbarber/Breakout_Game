package game_dpb20;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Breakout extends Application{
	public static final String TITLE = "Breakout Game - dpb20";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int SIZE = 500;
	
	public static final Paint BACKGROUND = Color.BURLYWOOD;
	
	public static final int paddleX = SIZE/2;
	public static final int paddleY = SIZE - 50;
	
	private Stage st;
	private Scene myScene;
	private Group root;
	
	private Paddle myPaddle;
	private Bouncer myBall;
	private int level = 1;
	private int streak;
	private boolean gameStart;
	
	//keeps track of the blocks in the game
	private ArrayList<Block> gameBlocks;
	//keeps track of the powerups in the game
	private ArrayList<Powerup> powerups;
	
	//instance variables for creating the canvas
	private int score;
	private Canvas canvas = new Canvas(SIZE,SIZE);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public void start(Stage stage) {
		st = stage;
		score = 0;
		
		//sets up scenes and font for GraphicsContext
		sceneSetUp();
		
		//sets up game loop
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline(); 
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private Scene setupGame(int width, int height, Paint background, int levelNumb) {
		root = new Group();
		Scene scene = new Scene(root, width, height, background);
		
		gameBlocks = new ArrayList<>();
		gameBlocks.clear();
		
		powerups = new ArrayList<>();
		powerups.clear();
		
		myPaddle = new Paddle();
		myPaddle.setX(paddleX - myPaddle.getWidth()/2);
		myPaddle.setY(paddleY);
		
		myBall = new Bouncer();
		resetBall();
		
		//Canvas for scoreboard
		root.getChildren().add(canvas);
		//Sets the level number on the bottom
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(BACKGROUND);
		gc.fillRect(0, 0, 500, 500);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(20));
		gc.fillText("Level: " +level, SIZE/2, SIZE - gc.getFont().getSize());
		gc.setTextAlign(TextAlignment.LEFT);
		
		//sets up the blocks
		for(int row = 1; row <= levelNumb; row++) {
			int ypos = SIZE*5/8 - row*2*Block.getBlockHeight();
			for(int xpos = Block.getBlockWidth()/2; xpos < SIZE; xpos += 2*Block.getBlockWidth()) {
				Block b = new Block(row, BACKGROUND);
				b.setX(xpos);
				b.setY(ypos);
				gameBlocks.add(b);
				root.getChildren().add(b);
			}
		}
		
		root.getChildren().add(myBall);
		root.getChildren().add(myPaddle);
		
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return scene;
	}
	
	private void step (double elapsedTime) {
		//updates the score by filling over the previous scoreboard
		if(myBall.getCenterY() >= SIZE && myPaddle.getLives() > 0) {
			resetBall(); 
			myPaddle.loseLife();
			gameStart = false;
		}
		gc.setFill(BACKGROUND);
		gc.fillRect(0, 0, 300, 200);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score + "    Streak: " + streak 
				+"    Lives: " +myPaddle.getLives(), 10, 30);
		
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
			//resets the streak count
			streak = 0;
			//checks if the ball hits the side of the paddle
			myBall.bounceY();
			if(myBall.getCenterY() >= myPaddle.getY()
					&& myBall.getCenterY() <= myPaddle.getY()+myPaddle.getHeight()) {
				myBall.bounceX();
			}
		}
		
		//checks if the ball makes contact with any of the blocks
		//must loop through all the blocks
		for(Block b: gameBlocks) {
			Shape blockAndBall = Shape.intersect(myBall, b);
			if(blockAndBall.getBoundsInLocal().getWidth() != -1 && !b.checkBroke()) {
				b.hit();
				if(makesSideContact(b)) {
					myBall.bounceX();
				}
				else {
					myBall.bounceY();
				}
				if(b.checkBroke()) {
					b.deadBlock();
				}
				if(!b.isPerm()) {
					streak += 1;
					score += streak;
				}
				if(b.isPowed()) {
					Powerup faller = b.getPowerup();
					faller.setCenterX(b.getX() + b.getWidth()/2);
					faller.setCenterY(b.getY() + b.getHeight()/2);
					root.getChildren().add(faller);
					powerups.add(faller);
				}
			}
		}
		if(!powerups.isEmpty()) {
			for(Powerup p : powerups) {
				p.setCenterY(p.getCenterY() + p.getVel()*elapsedTime);
				Shape powAndBall = Shape.intersect(myPaddle, p);
				//if it makes contact with the paddle or goes off the screen
				if(powAndBall.getBoundsInLocal().getWidth() != -1) {
					myPaddle.powered(p);
					removePow(p);
					if(powerups.isEmpty()) {
						break;
					}
				} else if(p.getCenterY() >= SIZE) {
					removePow(p);
					if(powerups.isEmpty()) {
						break;
					}
				}
			}
		}
		
	}
	
	private void handleKeyInput(KeyCode code) {
		//the additional boolean checks for if the paddle is about to go off the screen
		if(code == KeyCode.RIGHT
				&& myPaddle.getX() + myPaddle.getWidth() <= SIZE) {
			
			myPaddle.setX(myPaddle.getX() + myPaddle.getSpeed());
			
			//the ball moves with the paddle if the game has not started
			if(!gameStart) {
				myBall.setCenterX(myBall.getCenterX() + myPaddle.getSpeed());
			}
		}
		else if(code == KeyCode.LEFT 
				&& myPaddle.getX() >= 0) {
			
			myPaddle.setX(myPaddle.getX() - myPaddle.getSpeed());
			
			//the ball moves with the paddle if the game has not started
			if(!gameStart) {
				myBall.setCenterX(myBall.getCenterX() - myPaddle.getSpeed());
			}
			
		}
		//checks to see if the player has started the game
		else if(code == KeyCode.UP) {
			//checks if the ball is off the screen and then resets the ball if it is
			//starts the ball motion if it is not
			if(myBall.getCenterY() >= SIZE) {
				gameStart = false;
				resetBall();
			}
			else {
				gameStart = true;
			}
			//resets the streak count
			streak = 0;
		}
		//resets the level
		else if(code == KeyCode.R) {
			sceneSetUp();
		}
		//these change the level to the corresponding digit
		else if(code == KeyCode.DIGIT1) {
			level = 1;
			sceneSetUp();
		}
		else if(code == KeyCode.DIGIT2) {
			level = 2;
			sceneSetUp();
		}
		else if(code == KeyCode.DIGIT3) {
			level = 3;
			sceneSetUp();
		}
	}
	
	public void sceneSetUp() {
		gameStart = false;
		myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
		st.setScene(myScene);
		st.setTitle(TITLE);
		st.show();
		streak = 0;
	}
	
	//Checks to see if the ball made contact with the sides of the Block
	private boolean makesSideContact(Block rect) {
		return 	(myBall.getCenterY() >= rect.getY()
				&& myBall.getCenterY() <= rect.getY() + rect.getHeight());
	}
	
	//removes a powerup from the powerups arraylist
	private void removePow(Powerup p) {
		root.getChildren().remove(p);
		powerups.remove(p);
	}
	
	//sets the ball on top of the paddle
	private void resetBall() {
		myBall.setCenterX(myPaddle.getX() + myPaddle.getWidth()/2);
		myBall.setCenterY(myPaddle.getY() - myBall.getRadius());
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}
