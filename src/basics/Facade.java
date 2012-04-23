package basics;

import Interface.IFacade;
import Interface.IRobot;

public class Facade implements IFacade{
	
		
	public Facade(){}

	@Override
	public IRobot createRobot(long x, long y, int orientation, double energy){
		try {
			Robot result = new Robot(x,y,orientation,energy);
			return result;
		} 
		catch (IllegalArgumentException exc) {
		System.err.println(exc.getMessage());
		return null;
		}
	}

	@Override
	public long getX(IRobot robot) {
		Robot robot1 = (Robot) robot;
		long result = robot1.getX();		
		return result;
	}

	@Override
	public long getY(IRobot robot) {
		Robot robot1 = (Robot) robot;
		long result = robot1.getY();		
		return result;
	}

	@Override
	public int getOrientation(IRobot robot) {
		Robot robot1 = (Robot) robot;
		int result = robot1.getDirection();		
		return result;
	}

	@Override
	public void move(IRobot robot) {
		try{
			Robot robot1 = (Robot) robot;
			robot1.move();
		}catch (IllegalStateException exc) {
			System.err.println(exc.getMessage());	
		}
	}

	@Override
	public void turnClockwise(IRobot robot) {
		Robot robot1 = (Robot) robot;
		robot1.turnClockWise();
		
	}

	@Override
	public double getEnergy(IRobot robot) {
		Robot robot1 = (Robot) robot;
		double result = robot1.getEnergy();
		return result;
	}

	@Override
	public void recharge(IRobot robot, double energyAmount) {
		Robot robot1 = (Robot) robot;
		robot1.recharge(energyAmount);
		
	}

	@Override
	public int isGetEnergyRequiredToReachAndMoveNextTo16Plus(){
		return 1;
	}

	@Override
	public double getEnergyRequiredToReach(IRobot robot, long x, long y) {
		Robot robot1 = (Robot) robot;
		double result = robot1.getEnergyRequiredToReach(x, y);
		return result;
	}

	@Override
	public void moveNextTo(IRobot robot, IRobot robot2) {
		try{
			Robot robot1 = (Robot) robot;
			Robot robot3 = (Robot) robot2;
			robot1.moveNextTo(robot3);	
		}catch (IllegalArgumentException exc){
			System.err.println(exc.getMessage());	
		}
	}
}
