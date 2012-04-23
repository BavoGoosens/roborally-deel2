package test;

import static org.junit.Assert.*;
import org.junit.Test;

import basics.Facade;

import Interface.IFacade;
import Interface.IRobot;


public class FacadeTest {

	private IFacade facade;
	
	@org.junit.Before
	public void setUp() {
		facade = new Facade();
	}
	
	@Test
	public void testCreateRobot() {
		IRobot robot = facade.createRobot(5, 7, 0, 5000);
		assertNotNull(robot);
		assertEquals(5, facade.getX(robot));
		assertEquals(7, facade.getY(robot));
		assertEquals(0, facade.getOrientation(robot));
		assertEquals(5000, facade.getEnergy(robot), 0);
	}
	
	@Test
	public void testMove() {
		IRobot robot = facade.createRobot(5, 7, 1, 5000);
		facade.move(robot);
		assertEquals(6, facade.getX(robot));
		assertEquals(7, facade.getY(robot));
		assertEquals(1, facade.getOrientation(robot));
		assertEquals(5000 - 500, facade.getEnergy(robot), 0.1);
	}
	
	@Test
	public void testMoveInsufficientEnergy() {
		IRobot robot = facade.createRobot(0, 0, 1, 250);
		facade.move(robot);
		assertEquals(0, facade.getX(robot));
		assertEquals(0, facade.getY(robot));
		assertEquals(1, facade.getOrientation(robot));
		assertEquals(250, facade.getEnergy(robot), 0.1);
	}
	
	@Test
	public void testCreateInvalidRobot(){
		IRobot robot = facade.createRobot(-5, 7, 0, 5000);
		assertNull(robot);
	}
}