package roborally.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import roborally.model.Battery;
import roborally.model.Board;
import roborally.model.Entity;
import roborally.model.Robot;
import roborally.model.Wall;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;
import roborally.property.Weight;

public class BoardTest {

	private Board board1;
	private Board board2;
	private Robot robot1;
	private Battery battery1;


	@Before
	public void setUp() {
		this.board1 = new Board (200,400);
		this.board2 = new Board (700,1400);
		this.robot1 = new Robot(Orientation.LEFT, new Energy(12000));
		this.battery1 = new Battery(new Energy(500), new Weight(5));
		this.battery1.putOnBoard(this.board1, new Position( 5, 5));
		this.robot1.putOnBoard(this.board1, new Position( 5, 5));
		this.board1.putEntity(new Position( 5, 5), this.battery1);
		this.board1.putEntity(new Position( 5, 5), this.robot1);
	}

	@Test
	public void boardConstructorTest(){
		this.board1 = new Board ( 566 , 5876);
		assertEquals(this.board1.getHeight() , 5876);
		assertEquals(this.board1.getWidth() , 566);
	}

	@Test
	public void getEntityOnPositionTester(){
		HashSet<Entity> ents = this.board1.getEntityOnPosition(new Position(5,5));
		assertTrue(ents.contains(this.battery1) && ents.contains(this.robot1));
		ents = this.board1.getEntityOnPosition(new Position( 4, 6 ));
		assertTrue(ents == null);
	}

	@Test
	public void removeEntityTest(){
		this.board1.removeEntity(this.battery1);
		HashSet<Entity> ents = this.board1.getEntityOnPosition(new Position(5,5));
		assertTrue(!ents.contains(this.battery1));
	}

	@Test
	public void cleanBoardPositionTest(){
		this.battery1.putOnBoard(this.board1, new Position( 5, 5));
		this.robot1.putOnBoard(this.board1, new Position( 5, 5));
		this.board1.removeEntity(this.battery1);
		this.board1.removeEntity(this.robot1);
		this.board1.cleanBoardPosition(new Position(5,5));
		assertEquals(this.board1.getEntityOnPosition(new Position(5,5)), null);
	}
	
	@Test 
	public void isPlacableOnPositionTest(){
		this.robot1 = new Robot(Orientation.UP,new Energy (5000));
		this.battery1 = new Battery(new Energy (4000),new  Weight(455));
		assertTrue(this.board1.isPlacableOnPosition(new Position( 5, 5), this.battery1));
		assertTrue(this.board1.isPlacableOnPosition(new Position( 5, 5), this.robot1));
		this.robot1.putOnBoard(this.board1, new Position( 5, 5));
		assertTrue(this.board1.isPlacableOnPosition(new Position( 5, 5), this.battery1));
		Wall wall = new Wall();
		wall.putOnBoard(this.board1, new Position( 1 , 1 ));
		assertFalse(this.board1.isPlacableOnPosition(new Position( 5, 5), wall));
		this.battery1.putOnBoard(this.board1, new Position (6,5));
		assertTrue(this.board1.isPlacableOnPosition(new Position( 6, 5), this.robot1));
		assertTrue(this.board1.isPlacableOnPosition(new Position( 10,2 ), wall));
		assertFalse(this.board1.isPlacableOnPosition(new Position( 1 , 1 ), this.robot1));
		assertTrue(this.board1.isPlacableOnPosition(new Position (10,10), this.robot1));
	}
}
