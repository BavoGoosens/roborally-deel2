package roborally.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import roborally.model.Board;
import roborally.model.Robot;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;

public class RobotTest {
	
	Robot robot_up_10000;
	Robot robot_right_10000;
	Robot robot_left_10000;
	Robot robot_down_10000;
	Robot robot_up_1000;
	Robot robot_right_1000;
	Robot robot_left_1000;
	Robot robot_down_1000;
	Robot robot_onBoard_20_20_up_10000;
	Robot robot_onBoard_20_20_right_10000;
	Robot robot_onBoard_20_20_left_10000;
	Robot robot_onBoard_20_20_down_10000;
	Robot robot_onBoard_20_20_up_1000;
	Robot robot_onBoard_20_20_right_1000;
	Robot robot_onBoard_20_20_left_1000;
	Robot robot_onBoard_20_20_down_1000;
	Robot robot_onBoard_40_40_up_10000;
	Robot robot_onBoard_40_40_right_10000;
	Robot robot_onBoard_40_40_left_10000;
	Robot robot_onBoard_40_40_down_10000;
	Robot robot_onBoard_40_40_up_1000;
	Robot robot_onBoard_40_40_right_1000;
	Robot robot_onBoard_40_40_left_1000;
	Robot robot_onBoard_40_40_down_1000;
	Board board_20_20 = new Board(20, 20);
	Board board_40_40 = new Board(40, 40);
	
	@Before
	public void setUp(){
		robot_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_20_20_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_onBoard_20_20_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_onBoard_20_20_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_onBoard_20_20_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_onBoard_20_20_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_onBoard_20_20_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_onBoard_20_20_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_onBoard_20_20_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_20_20_up_10000.putOnBoard(board_20_20, new Position(1, 1));
		robot_onBoard_20_20_right_10000.putOnBoard(board_20_20, new Position(2, 1));
		robot_onBoard_20_20_left_10000.putOnBoard(board_20_20, new Position(3, 1));
		robot_onBoard_20_20_down_10000.putOnBoard(board_20_20, new Position(4, 1));
		robot_onBoard_20_20_up_1000.putOnBoard(board_20_20, new Position(5, 1));
		robot_onBoard_20_20_right_1000.putOnBoard(board_20_20, new Position(6, 1));
		robot_onBoard_20_20_left_1000.putOnBoard(board_20_20, new Position(7, 1));
		robot_onBoard_20_20_down_1000.putOnBoard(board_20_20, new Position(8, 1));
		robot_onBoard_40_40_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_onBoard_40_40_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_onBoard_40_40_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_onBoard_40_40_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_onBoard_40_40_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_onBoard_40_40_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_onBoard_40_40_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_onBoard_40_40_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_40_40_up_10000.putOnBoard(board_40_40, new Position(1, 1));
		robot_onBoard_40_40_right_10000.putOnBoard(board_40_40, new Position(2, 1));
		robot_onBoard_40_40_left_10000.putOnBoard(board_40_40, new Position(3, 1));
		robot_onBoard_40_40_down_10000.putOnBoard(board_40_40, new Position(4, 1));
		robot_onBoard_40_40_up_1000.putOnBoard(board_40_40, new Position(5, 1));
		robot_onBoard_40_40_right_1000.putOnBoard(board_40_40, new Position(6, 1));
		robot_onBoard_40_40_left_1000.putOnBoard(board_40_40, new Position(7, 1));
		robot_onBoard_40_40_down_1000.putOnBoard(board_40_40, new Position(8, 1));
	}
	
	@Test
	public void testDestroy() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		assertEquals("positie: 5, 1, energie: 1000.0 WS, maximale energie: 20000.0 WS, oriëntatie: UP, gewicht: 0 gr", robot_onBoard_20_20_up_1000.toString());
	}

	@Test
	public void testGetOrientation() {
		assertEquals(Orientation.UP, robot_up_1000.getOrientation());
		assertEquals(Orientation.DOWN, robot_down_1000.getOrientation());
		assertEquals(Orientation.LEFT, robot_left_1000.getOrientation());
		assertEquals(Orientation.RIGHT, robot_right_1000.getOrientation());
	}

	@Test
	public void testSetMaxEnergy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxEnergy() {
		robot_up_1000.setMaxEnergy(new Energy(5000));
		assertEquals(new Energy(5000), robot_up_1000.getMaxEnergy());
		assertEquals(Robot.MAX_ENERGY, robot_up_10000.getMaxEnergy());
	}

	@Test
	public void testGetEnergy() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidRobotEnergyEnergyRobot() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidRobotEnergyEnergy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnergyFraction() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnClockWise() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnCounterClockWise() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnergyRequiredToReach() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveNextTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testShoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalWeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPossessions() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransferPossessions() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoSurpriseBoxAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrop() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadProgramFromFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProgram() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveProgramToFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testStepn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOnBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsTerminated() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutOnBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFromBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidEntity() {
		fail("Not yet implemented");
	}

}
