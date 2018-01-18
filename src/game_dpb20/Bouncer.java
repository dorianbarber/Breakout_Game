package game_dpb20;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bouncer extends Circle{
	private static Color myColor = Color.AQUA;
	private static int myRadius = 8;
	
	private int xpos;
	private int ypos;
	
	private int xvel;
	private int yvel;
	
	public Bouncer() {
		super(myRadius, myColor);
	}
	
}
