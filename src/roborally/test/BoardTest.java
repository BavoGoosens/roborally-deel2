package roborally.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.basics.Weight;
import roborally.model.Battery;
import roborally.model.Board;
import roborally.model.Entity;
import roborally.model.Robot;

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
		assertTrue(ents.contains(this.battery1) && ents.contains(this.battery1));
		try{
			ents = this.board1.getEntityOnPosition(new Position( 4, 6 ));
		}catch(NullPointerException exp){
			
		}
	}
	
	@Test
	public void removeEntityTest(){
		try{
			this.board1.removeEntity(this.battery1);
			HashSet<Entity> ents = this.board1.getEntityOnPosition(new Position(5,5));
			assertTrue(!ents.contains(this.battery1));
		}catch(NullPointerException exp){
			
		}
	}

}
