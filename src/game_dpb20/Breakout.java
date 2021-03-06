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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * JavaFX program that represents a spin off of the classic Atari Breakout game
 * 
 * Depends on Paddle, Bouncer, Bonus, and Powerup classes
 * @author Dorian
 *
 */
public class Breakout extends Application{
	public static final String TITLE = "Breakout Game - dpb20";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int SIZE = 500;

	private static final Paint BACKGROUND = Color.BURLYWOOD;

	private static final int paddleX = SIZE/2;
	private static final int paddleY = SIZE - 50;

	private Stage st;
	private Scene myScene;
	private Group root;

	private Paddle myPaddle;
	private Bouncer myBall;
	private Bouncer myTarget;

	private int level;
	private int streak;
	private int longestStreak = 0;

	private boolean gameStart;
	private boolean loadScreen = true;
	private boolean unlimitedLives = false;

	//keeps track of the blocks in the game
	private ArrayList<Block> gameBlocks;
	//keeps track of the powerups in the game
	private ArrayList<Powerup> powerups;

	//instance variables for creating the canvas
	private int score;
	private Canvas canvas = new Canvas(SIZE,SIZE);
	private GraphicsContext gc = canvas.getGraphicsContext2D();

	/**
	 * Starts the actual game or sends it to the instructional splash screen
	 */
	public void start(Stage stage) {
		st = stage;
		score = 0;
		level = 1;

		if(loadScreen) {
			splashScreen();
		}
		else {
			//sets up scenes and font for GraphicsContext
			myPaddle = new Paddle();
			myPaddle.setX(paddleX - myPaddle.getWidth()/2);
			myPaddle.setY(paddleY);
			sceneSetUp();

			//sets up game loop
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
			Timeline animation = new Timeline(); 
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}

	}

	/**
	 * Sets up the scene and runs an algorithm which generates the 
	 * block set up depending on the level the player is on
	 */
	private Scene setupGame(int width, int height, Paint background, int levelNumb) {
		root = new Group();
		Scene scene = new Scene(root, width, height, background);

		gameBlocks = new ArrayList<>();
		gameBlocks.clear();

		powerups = new ArrayList<>();
		powerups.clear();

		myBall = new Bouncer();
		myBall.reset(myPaddle);

		myTarget = new Bouncer();
		myTarget.setCenterX(SIZE/2);
		myTarget.setCenterY(SIZE/6);
		myTarget.changeToTarget();
		//Canvas for scoreboard, lives, streak, and level number
		root.getChildren().add(canvas);

		//sets up the blocks depending on the level
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
		root.getChildren().add(myTarget);

		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return scene;
	}

	/**
	 * Game loop:
	 * 		checks the score, moves the ball, checks for contact on blocks, paddle, and target
	 * 		updates the necessary variables according to the event
	 */
	private void step (double elapsedTime) {
		//updates the score by filling over the previous scoreboard
		if(myBall.getCenterY() >= SIZE && myPaddle.getLives() > 0) {
			myBall.reset(myPaddle); 
			if(!unlimitedLives) {
				myPaddle.loseLife();
			}
			gameStart = false;
		}
		
		setText();
		
		//checks to see if the player has started the game
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

		if(myPaddle.paddleBallContact(myBall)){
			streak = 0;
		}
		//checks if the ball makes contact with any of the blocks
		//must loop through all the blocks
		for(Block b: gameBlocks) {
			Shape blockAndBall = Shape.intersect(myBall, b);
			if(blockAndBall.getBoundsInLocal().getWidth() != -1 && !b.checkBroke()) {

				b.ballContact(myBall);

				if(!b.isPerm()) {
					streak += 1;
					if(streak > longestStreak) {
						longestStreak = streak;
					}
					if(myPaddle.isBonus()) {
						score += streak*2;
					}
					else {
						score += streak;
					}
				}
				if(b.isPowed()) {
					Powerup faller = b.getPowerup();
					faller.setPos(b);
					root.getChildren().add(faller);
					powerups.add(faller);
				}
			}
		}
		for(Powerup p : powerups) {
			if(!p.isUsed()) {
				p.setCenterY(p.getCenterY() + Powerup.Y_VELOCITY*elapsedTime);
				Shape powAndBall = Shape.intersect(myPaddle, p);
				//if it makes contact with the paddle
				if(powAndBall.getBoundsInLocal().getWidth() != -1) {
					p.powUsed();
					p.powered(myPaddle);
					root.getChildren().remove(p);
					if(powerups.isEmpty()) {
						break;
					}
				} else if(p.getCenterY() >= SIZE) {
					root.getChildren().remove(p);
					if(powerups.isEmpty()) {
						break;
					}
				}
			}
		}
		if(myPaddle.getLives() == 0) {
			gameOverScreen();
			//to prevent the game from continuously cycling to this spot
			myPaddle.setLives(-1);
		}

		// lets the user move on to the next level
		if(myTarget.targetBallContact(myBall)){
			level += 1;
			if(level <= 3) {
				sceneSetUp();
			}
			else {
				endScreen();
			}
		}
	}

	/**
	 * Handles key inputs during the actual game 
	 * This inputs are only valid if in the Breakout game
	 * They are not valid in any of the splash screens(instructional, game over, or congratulations)
	 */
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
				myBall.reset(myPaddle);
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
		else if(code == KeyCode.U) {
			if(unlimitedLives) {
				unlimitedLives = false;
			}
			else {
				unlimitedLives = true;
			}
		}
	}

	/**
	 * The only key that can be pressed in any of the splash screens
	 */
	private void handleSplashScreenInput(KeyCode code) {
		if(code == KeyCode.SPACE) {
			if(loadScreen) {
				loadScreen = false;
				start(st);
			}
			else {
				level = 1;
				score = 0;
				myPaddle.setLives(3);
				myPaddle.setX(paddleX - myPaddle.getWidth()/2);
				myPaddle.setY(paddleY);
				sceneSetUp();
			}

		}
	}

	/**
	 * Extracted code for setting up the scene and adjusting the proper variables 
	 */
	public void sceneSetUp() {
		gameStart = false;
		myScene = setupGame(SIZE, SIZE, BACKGROUND, level);
		st.setScene(myScene);
		st.setTitle(TITLE);
		st.show();
		streak = 0;
	}

	//Message for the instructional splash screen
	public void splashScreen() {
		String message = ("Welcome to Breakout!\n"
				+ "Use the right and left arrow keys to move the paddle.\n"
				+ "Press the up arrow to release the ball.\n"
				+ "\n"
				+ "Break blocks to score points.\n"
				+ "Create a streak of hits to score bonus points!!!\n"
				+ "Aim for the target to move onto the next level.\n"
				+ "\n"
				+ "Look for special powerups to give you bonuses!\n"
				+ "Red   --> gives a life\n"
				+ "Blue  --> temporarily makes the paddle bigger\n"
				+ "Green--> temporarily gives double score\n"
				+ "\n"
				+ "When you're ready press the\nspace bar to start the game!");
		changeToMessageScreen(message);
	}

	//message for the player when they have completed all three levels
	public void endScreen() {
		String message = ("Congratulations you have won!!!!\n"
				+ "Your score was: " + score +"\n"
				+ "Your highest streak: " + longestStreak + "\n"
				+ "\n"
				+ "Press the space bar to play again!\n");
		root.getChildren().remove(myBall);
		changeToMessageScreen(message);
	}

	//message for the player when they have died
	public void gameOverScreen() {
		String message = ("GAME OVER\n"
				+ "Awww shucks it looks like you've\n"
				+ "just run out of lives.\n"
				+ "\n"
				+ "Press the space bar to play again");
		root.getChildren().remove(myBall);
		changeToMessageScreen(message);
	}

	//Sets the level number and the score, streak, and lives
	private void setText() {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(BACKGROUND);
		gc.fillRect(0, 0, 500, 500);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(20));
		gc.fillText("Level: " +level, SIZE/2, SIZE - gc.getFont().getSize());
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFill(BACKGROUND);
		gc.fillRect(0, 0, 300, 200);
		gc.setFill(Color.BLACK);
		gc.fillText("Score: " + score + "    Streak: " + streak 
				+"    Lives: " +myPaddle.getLives(), 10, 30);
	}

	/**
	 * Extracted code which implements the three splash screens depending
	 * on the message passed
	 */
	private void changeToMessageScreen(String message) {
		Text t = new Text();
		t.setFont(new Font(17));
		t.setText(message);

		t.setX(50);
		t.setY(50);
		root = new Group(t);
		Scene scene = new Scene(root, SIZE, SIZE, BACKGROUND);

		st.setScene(scene);
		st.setTitle(TITLE);
		st.show();

		scene.setOnKeyPressed(e -> handleSplashScreenInput(e.getCode()));
	}

	public static void main (String[] args) {
		launch(args);
	}
}