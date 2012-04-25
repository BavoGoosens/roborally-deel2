package roborally.model;

import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Weight;
import roborally.interfaces.IFacade;

import java.util.Set;


public class Facade implements IFacade<Board, Robot, Wall, Battery>{
	
		
	public Facade(){}

	@Override
	public Board createBoard(long width, long height) {
		Board board = new Board(width,height);
		return board;
	}

	@Override
	public void merge(Board board1, Board board2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Battery createBattery(double initialEnergy, int weight) {
		Battery battery = new Battery(new Energy((int)initialEnergy), new Weight(weight));
		return battery;
	}

	@Override
	public void putBattery(Board board, long x, long y, Battery battery) {
		board.putBattery(x,y,battery);
		
	}

	@Override
	public long getBatteryX(Battery battery) throws IllegalStateException {
		try {return battery.getPosition().getX();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}

	@Override
	public long getBatteryY(Battery battery) throws IllegalStateException {
		try {return battery.getPosition().getY();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}
	
	public Orientation getOrientationEnum(int i ){
		if(i < 0){
			int tmp = Math.abs(i % 4);
			if(tmp == 1){
				return Orientation.LEFT;
			}else if(tmp == 3){
				return Orientation.RIGHT;
			}
		}
		int tmp = (i % 4);
		if (tmp == 0)
			return Orientation.UP;
		else if (tmp == 1)
			return Orientation.RIGHT;
		else if (tmp == 2)
			return Orientation.DOWN;
		else 
			return Orientation.LEFT;
	}
	
	@Override
	public Robot createRobot(int orientation, double initialEnergy) {
		Robot robot = new Robot(new Orientation(getOrientationEnum(orientation)),new Energy((int)initialEnergy));
	}

	@Override
	public void putRobot(Board board, long x, long y, Robot robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRobotX(Robot robot) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getRobotY(Robot robot) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOrientation(Robot robot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEnergy(Robot robot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(Robot robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Robot robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Battery> getPossessions(Robot robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pickUp(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isMinimalCostToReach17Plus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinimalCostToReach(Robot robot, long x, long y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isMoveNextTo18Plus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveNextTo(Robot robot, Robot other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot(Robot robot) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wall createWall() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putWall(Board board, long x, long y, Wall wall)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getWallX(Wall wall) throws IllegalStateException,
			UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getWallY(Wall wall) throws IllegalStateException,
			UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Robot> getRobots(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Battery> getBatteries(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Wall> getWalls(Board board) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}
}

	
