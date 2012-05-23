package roborally.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import roborally.exception.EntityNotOnBoardException;
import roborally.exception.IllegalPositionException;
import roborally.model.Battery;
import roborally.model.Board;
import roborally.model.RepairKit;
import roborally.model.Robot;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;
import roborally.property.Weight;

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
	public void testIsValidRobotEnergyEnergyRobot() {
		assertTrue(Robot.isValidRobotEnergy(new Energy(1000), robot_down_1000));
		assertFalse(Robot.isValidRobotEnergy(new Energy(20000.1), robot_down_1000));
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
		assertEquals(new Energy(500), Robot.moveCost(robot_down_1000));
		Battery batt = new Battery(new Energy(1), new Weight(2000));
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertEquals(new Energy(600), Robot.moveCost(robot_onBoard_20_20_down_1000));
	}

	@Test
	public void testGetPossessions() {
		assertEquals(0, robot_onBoard_20_20_down_1000.getPossessions().size());
		Battery batt = new Battery(new Energy(10), new Weight(555));
		RepairKit rk = new RepairKit(new Energy(10), new Weight(666));
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		rk.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertEquals(1, robot_onBoard_20_20_down_1000.getPossessions().size());
		robot_onBoard_20_20_down_1000.pickUp(rk);
		assertEquals(2, robot_onBoard_20_20_down_1000.getPossessions().size());
	}

	@Test
	public void testTransferPossessions() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickUp() {
		assertEquals(0, robot_onBoard_20_20_down_1000.getPossessions().size());
		Battery batt = new Battery(new Energy(1), new Weight(2000));
		RepairKit rk = new RepairKit(new Energy(1), new Weight(2000));
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		rk.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertEquals(1, robot_onBoard_20_20_down_1000.getPossessions().size());
		robot_onBoard_20_20_down_1000.pickUp(rk);
		assertEquals(2, robot_onBoard_20_20_down_1000.getPossessions().size());
	}

	@Test
	public void testUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrop() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadProgramFromFile() {
		assertNull(robot_onBoard_20_20_down_1000.getProgram());
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Test mislukt of testprogramma bestaat niet.");
		}
		assertNotNull(robot_onBoard_20_20_down_1000.getProgram());
	}

	@Test
	public void testGetProgram() {
		assertNull(robot_onBoard_20_20_down_1000.getProgram());
		// Het is noodzakelijk om een correct programma op te geven.
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Opgegeven testprogramma ongeldig.");
		}
		assertNotNull(robot_onBoard_20_20_down_1000.getProgram());
	}

	@Test
	/**
	 * Deze test mislukt ook wanneer een ander voorbeeld dan example.prog wordt gebruikt om te testen.
	 */
	public void testSaveProgramToFile() {
		// Het is noodzakelijk om een correct programma op te geven.
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Opgegeven testprogramma ongeldig.");
		}
		assertEquals(1, robot_onBoard_20_20_down_1000.saveProgramToFile("exampleCopy.prog"));
		assertTrue((new File("exampleCopy.prog")).exists());
		try {
			robot_onBoard_20_20_down_10000.loadProgramFromFile("exampleCopy.prog");
		} catch (FileNotFoundException e) {
			fail("Inladen van nieuw programma lukt niet.");
		}
		robot_onBoard_20_20_down_10000.setPosition(new Position(3, 3));
		robot_onBoard_20_20_down_10000.stepn(2);
		assertEquals(Orientation.LEFT, robot_onBoard_20_20_down_10000.getOrientation());
	}

	@Test
	public void testStepn() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		assertNull(robot_down_1000.getPosition());
		robot_down_1000.putOnBoard(board_20_20, new Position(5, 5));
		assertEquals(new Position(5, 5), robot_down_1000.getPosition());
		robot_down_1000.setPosition(new Position(6, 6));
		assertEquals(new Position(6, 6), robot_down_1000.getPosition());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testSetPositionIllegalStateException(){
		robot_onBoard_20_20_down_1000.destroy();
		robot_onBoard_20_20_down_1000.setPosition(new Position(0, 0));
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testSetPositionEntityNotOnBoardException(){
		robot_down_1000.setPosition(new Position(0, 0));
	}
	
	@Test(expected = IllegalPositionException.class)
	public void testSetPositionIllegalPositionException(){
		robot_onBoard_20_20_down_1000.setPosition(new Position(21, 0));
	}

	@Test
	public void testPutOnBoard() {
		assertFalse(robot_down_1000.isOnBoard());
		robot_down_1000.putOnBoard(board_20_20, new Position(3, 3));
		assertTrue(robot_down_1000.isOnBoard());
		assertEquals(new Position(3, 3), robot_down_1000.getPosition());
		assertEquals(board_20_20, robot_down_1000.getBoard());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testPutOnBoardIllegalStateException(){
		robot_onBoard_20_20_down_1000.putOnBoard(board_40_40, new Position(3, 3));
	}
	
	@Test
	public void testRemoveFromBoard() {
		assertTrue(robot_onBoard_20_20_down_1000.isOnBoard());
		robot_onBoard_20_20_down_1000.removeFromBoard();
		assertFalse(robot_onBoard_20_20_down_1000.isOnBoard());
		assertNull(robot_onBoard_20_20_down_1000.getPosition());
		assertNull(robot_onBoard_20_20_down_1000.getBoard());
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testRemoveFromBoardEntityNotOnBoardException(){
		robot_down_1000.removeFromBoard();
	}

	@Test
	public void testDestroy() {
		assertFalse(robot_down_1000.isTerminated());
		robot_down_1000.destroy();
		assertTrue(robot_down_1000.isTerminated());
		Battery batt = new Battery(new Energy(1), new Weight(1));
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		assertFalse(batt.isTerminated());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.destroy();
		assertEquals(robot_onBoard_20_20_down_1000.getPossessions().size(), 0);
		assertTrue(batt.isTerminated());
	}

	@Test
	public void testToString() {
		assertEquals("positie: 5, 1, energie: 1000.0 WS, maximale energie: 20000.0 WS, oriëntatie: UP, gewicht: 0 gr", robot_onBoard_20_20_up_1000.toString());
		assertEquals("positie: staat niet op een bord, energie: 1000.0 WS, maximale energie: 20000.0 WS, oriëntatie: UP, gewicht: 0 gr", robot_up_1000.toString());
	}

	@Test
	public void testGetOrientation() {
		assertEquals(Orientation.UP, robot_up_1000.getOrientation());
		assertEquals(Orientation.DOWN, robot_down_1000.getOrientation());
		assertEquals(Orientation.LEFT, robot_left_1000.getOrientation());
		assertEquals(Orientation.RIGHT, robot_right_1000.getOrientation());
	}

	@Test
	public void testGetEnergy() {
		assertEquals(new Energy(1000), robot_down_1000.getEnergy());
		assertEquals(new Energy(10000), robot_down_10000.getEnergy());
	}

	@Test
	public void testIsValidRobotEnergyEnergy() {
		assertTrue(Robot.isValidRobotEnergy(new Energy(1000)));
		assertFalse(Robot.isValidRobotEnergy(new Energy(20000.1)));
	}

	@Test
	public void testGetEnergyFraction() {
		assertEquals(0.5,1, robot_down_10000.getEnergyFraction());
	}

	@Test
	public void testGetBoard() {
		assertEquals(board_20_20, robot_onBoard_20_20_down_1000.getBoard());
	}

	@Test
	public void testIsOnBoard() {
		assertTrue(robot_onBoard_20_20_down_1000.isOnBoard());
		assertFalse(robot_down_1000.isOnBoard());
	}

	@Test
	public void testGetPosition() {
		assertEquals(new Position(1, 1), robot_onBoard_20_20_up_10000.getPosition());
	}

	@Test
	public void testIsTerminated() {
		assertFalse(robot_down_1000.isTerminated());
		robot_down_1000.destroy();
		assertTrue(robot_down_1000.isTerminated());
	}

	@Test
	public void testIsValidEntity() {
		assertTrue(robot_down_1000.isValidEntity());
		assertTrue(robot_onBoard_20_20_down_1000.isValidEntity());
		robot_onBoard_20_20_down_1000.setPosition(null);
		assertFalse(robot_onBoard_20_20_down_1000.isValidEntity());
	}

}