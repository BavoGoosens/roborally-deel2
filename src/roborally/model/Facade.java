package roborally.model;

import roborally.IFacade;
import roborally.basics.*;

import java.util.HashSet;
import java.util.Set;


public class Facade implements IFacade<Board, Robot, Wall, Battery>{


	public Facade(){}
	//TODO: alle exceptions catchen en volledig nakijken - dit doen we beter samen

	@Override
	public Board createBoard(long width, long height) {
		Board board = new Board(width,height);
		return board;
	}

	@Override
	public void merge(Board board1, Board board2) {
		board1.merge(board2);	
	}

	@Override
	public Battery createBattery(double initialEnergy, int weight){
		Battery battery = new Battery(new Energy((int)initialEnergy), new Weight(weight));
		return battery;
	}

	@Override
	public void putBattery(Board board, long x, long y, Battery battery) {
		Position pos = new Position(x,y);
		battery.putOnBoard(board, pos);
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

	public static Orientation getOrientationEnum(int i ){
		int tmp = Math.abs(i % 4);
		if(i < 0){
			if(tmp == 1){
				return Orientation.LEFT;
			}else if(tmp == 3){
				return Orientation.RIGHT;
			}
		}
		switch(tmp){
		case 0:
			return Orientation.UP;
		case 1:
			return Orientation.RIGHT;
		case 2:
			return Orientation.DOWN;
		case 3:
			return Orientation.LEFT;
		}
		return null;
	}

	@Override
	public Robot createRobot(int orientation, double initialEnergy) {
		Robot robot = new Robot(getOrientationEnum(orientation),new Energy((int)initialEnergy));
		return robot;
	}

	@Override
	public void putRobot(Board board, long x, long y, Robot robot) {
		Position pos = new Position(x , y);
		robot.putOnBoard(board, pos);

	}

	@Override
	public long getRobotX(Robot robot) throws IllegalStateException {
		try {return robot.getPosition().getX();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}

	@Override
	public long getRobotY(Robot robot) throws IllegalStateException {
		try {return robot.getPosition().getY();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}

	@Override
	public int getOrientation(Robot robot) {
		return robot.getOrientation().ordinal();
	}

	@Override
	public double getEnergy(Robot robot) {
		return robot.getEnergy().getEnergy();
	}

	@Override
	public void move(Robot robot) {
		robot.move();	
	}

	@Override
	public void turn(Robot robot) {
		robot.turnClockWise();
	}

	@Override
	public Set<Battery> getPossessions(Robot robot) {
		return robot.getPossessions();
	}

	@Override
	public void pickUp(Robot robot, Battery battery) {
		robot.pickUp(battery);
	}

	@Override
	public void use(Robot robot, Battery battery) {
		robot.use(battery);
	}

	@Override
	public void drop(Robot robot, Battery battery) {
		robot.drop(battery);
	}

	@Override
	public int isMinimalCostToReach17Plus() {
		return 1;
	}

	@Override
	public double getMinimalCostToReach(Robot robot, long x, long y) {
		double result = robot.getEnergyRequiredToReach(new Position(x, y)).getEnergy();
		if(robot.getEnergy().getEnergy() < result)
			return -1;
		return result;
	}

	@Override
	public int isMoveNextTo18Plus() {
		// TODO einde
		return 0;
	}

	@Override
	public void moveNextTo(Robot robot, Robot other) {
		robot.moveNextTo(other);
	}

	@Override
	public void shoot(Robot robot) throws UnsupportedOperationException {
		robot.shoot();
	}

	@Override
	public Wall createWall() throws UnsupportedOperationException {
		return new Wall();
	}

	@Override
	public void putWall(Board board, long x, long y, Wall wall)
			throws UnsupportedOperationException {
		Position pos = new Position(x , y);
		wall.putOnBoard(board, pos);
	}

	@Override
	public long getWallX(Wall wall) throws IllegalStateException,
	UnsupportedOperationException {
		try {return wall.getPosition().getX();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}

	@Override
	public long getWallY(Wall wall) throws IllegalStateException,
	UnsupportedOperationException {
		try {return wall.getPosition().getY();} catch (IllegalArgumentException esc) {
			throw new IllegalStateException("not on a board");
		}
	}

	@Override
	public Set<Robot> getRobots(Board board) {
		return board.getRobots();
	}

	@Override
	public Set<Battery> getBatteries(Board board) {
		return board.getBatteries();
	}

	@Override
	public Set<Wall> getWalls(Board board) throws UnsupportedOperationException {
		return board.getWalls();
	}
}


