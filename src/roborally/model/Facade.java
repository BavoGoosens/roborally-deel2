package roborally.model;

import roborally.interfaces.IFacade;

import java.util.Set;


public class Facade implements IFacade{
	
		
	public Facade(){}

	@Override
	public Object createBoard(long width, long height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void merge(Object board1, Object board2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createBattery(double initialEnergy, int weight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putBattery(Object board, long x, long y, Object battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getBatteryX(Object battery) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getBatteryY(Object battery) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object createRobot(int orientation, double initialEnergy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putRobot(Object board, long x, long y, Object robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRobotX(Object robot) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getRobotY(Object robot) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOrientation(Object robot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEnergy(Object robot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(Object robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Object robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set getPossessions(Object robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pickUp(Object robot, Object battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(Object robot, Object battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Object robot, Object battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isMinimalCostToReach17Plus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinimalCostToReach(Object robot, long x, long y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isMoveNextTo18Plus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveNextTo(Object robot, Object other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot(Object robot) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createWall() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putWall(Object board, long x, long y, Object wall)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getWallX(Object wall) throws IllegalStateException,
			UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getWallY(Object wall) throws IllegalStateException,
			UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set getRobots(Object board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getBatteries(Object board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getWalls(Object board) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
