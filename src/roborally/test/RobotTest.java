package roborally.test;

import static org.junit.Assert.*;
import org.junit.Test;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.model.Board;
import roborally.model.Robot;

public class RobotTest {
	
	private Robot robot;
	private Robot robot1;
	private Robot robot2;
	private Robot robot3;
	private Robot robot1_0_0;
	private Robot robot2_0_0;
	private Robot robot7;
	private Robot robot6;
	private Robot robot5;
	private Robot robot4;
	private Board board1;
	private Board board2;
	
	@org.junit.Before
	public void setUp() {
		this.board1 = new Board(200,400);
		this.board2 = new Board(600,800);
		this.robot = new Robot(Orientation.UP,new Energy(10000));
		this.robot1 = new Robot(Orientation.RIGHT,new Energy(10000));
		this.robot2 = new Robot(Orientation.DOWN,new Energy(10000));
		this.robot3 = new Robot(Orientation.LEFT,new Energy(10000));
		this.robot4= new Robot(Orientation.UP,new Energy(10000));
		this.robot5 = new Robot(Orientation.RIGHT,new Energy(10000));
		this.robot6 = new Robot(Orientation.DOWN,new Energy(10000));
		this.robot7 = new Robot(Orientation.LEFT,new Energy(10000));
		this.robot1_0_0 = new Robot(Orientation.UP,new Energy(10000));
		this.robot2_0_0 = new Robot(Orientation.LEFT,new Energy(9456));
	}
	
	

}