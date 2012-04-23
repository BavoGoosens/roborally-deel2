package Interface;



/**
 * Implement this interface to connect your code to the user interface.
 * 
 * <ul>
 * <li>Your class for representing robots should implement the interface <code>Robot</code>. For example, if your class is named <code>IRobotImpl</code>, 
 *     then the header of that class should look as follows:
 *     
 *     <p><code>class RobotImpl implements IRobot { ... }</code></p>
 *     
 *    Consult the <a href="http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">Java tutorial</a> for more information on interfaces.</li>
 * <li>Connect your class to the user interface by creating a class named <code>Facade</code> that implements <code>IFacade</code>. The header of that class 
 *     should look as follows:
 *     <p><code>class Facade implements IFacade { ... }</code></p>
 *     Each method defined in the interface <code>IFacade</code> must be implemented by the class <code>Facade</code>. For example, the implementation of <code>getX</code>
 *     should call a method of the given <code>robot</code> to retrieve its x-coordinate.
 * <li>Modify the code between <code>&ltbegin&gt</code> and <code>&ltend&gt</code> in RoboRally.java: replace <code>new roborally.model.Facade()</code> with <code>new yourpackage.Facade()</code>.</li>
 * <li>You may assume that only non-null objects returned by <code>createRobot</code> are passed to <code>getX</code>, <code>getY</code>, <code>move</code>, etc. This means
 *   that you can safely cast <code>Robot</code> to your own class that represents robots.</li>
 * <li>The methods in this interface should not throw exceptions. Prevent precondition violations for nominal methods (by checking before calling a method that its precondition holds)
 *   and catch exceptions for defensive methods. If a problem occurs (e.g. insufficient energy to move), print an error message on standard error (<code>System.err</code>).</li>
 * <li>The rules described above and the documentation described below for each method apply only to the class implementing IFacade. Your class for representing robots should follow the rules described in the assignment.</li>
 * <li>Do not modify the signatures of the methods defined in this interface. You can however add additional methods, as long as these additional methods do not overload the existing ones. Each additional method should of course
 *     be implemented in your class <code>Facade</code>.</li>
 * </ul> 
 */
public interface IFacade{

	/** 
	 * Create a new Robot at coordinate (<code>x</code>, <code>y</code>) looking at <code>orientation</code> with <code>energy</code> watt-second.
	 * 
	 * This method must return <code>null</code> if the given parameters are invalid (e.g. negative energy). 
	 *  
	 * <p>0, 1, 2, 3 respectively represent up, right, down and left.</p>
	 */
	public abstract IRobot createRobot(long x, long y, int orientation, double energy);
	
	/**
	 * Return the x-coordinate of <code>robot</code>.
	 */
	public abstract long getX(IRobot robot);

	/**
	 * Return the y-coordinate of <code>robot</code>.
	 */
	public abstract long getY(IRobot robot);

	/**
	 * Return the orientation (either 0, 1, 2 or 3) of <code>robot</code>. 
	 * 
	 * <p>0, 1, 2, 3 respectively represent up, right, down and left.</p>
	 */
	public abstract int getOrientation(IRobot robot);

	/**
	 * Move <code>robot</code> one step in its current direction if the robot has sufficient energy. Do not modify the state of the robot
	 * if it has insufficient energy.
	 */
	public abstract void move(IRobot robot);

	/**
	 * Turn <code>robot</code> 90 degrees in clockwise direction if the IRobothas sufficient energy. Do not modify the state of the robot
	 * if it has insufficient energy.
	 */
	public abstract void turnClockwise(IRobot robot);

	/**
	 * Return the current energy in watt-second of <code>robot</code>.
	 */
	public double getEnergy(IRobot robot);
	
	/**
	 * Add <code>energyAmount</code> (expressed in watt-second) to <code>robot</code>. If <code>energyAmount</code> is negative or if
	 * adding <code>energyAmount</code> would cause the IRobotto exceed its maximum energy level, do not modify the state of the robot.
	 */
	public void recharge(IRobot robot, double energyAmount);

	/**
	 * Return whether your implementations of <code>getEnergyRequiredToReach</code> and <code>moveNextTo</code> take into account the fact that
	 * turning consumes energy (required to score 16+).  The return value of this method determines the expected return value of 
	 * <code>getEnergyRequiredToReach</code> and the expected effect of <code>moveNextTo</code> in the test suite.
	 * 
	 * This method must return either 0 or 1.
	 */
	public int isGetEnergyRequiredToReachAndMoveNextTo16Plus();
	
	/**
	 * Return the minimum amount of energy required by <code>IRobot</code> to reach coordinate (<code>x</code>, <code>y</code>). 
	 * 
	 * <ul>
	 * <li>If <code>isGetEnergyRequiredToReachAndMoveNextTo16Plus</code> returns 0, the result of this method should not include the 
	 * energy required for turning. That is, the result of this method must only be optimal in the number of moves (not in the total energy required).</li>
	 * <li>If <code>isGetEnergyRequiredToReachAndMoveNextTo16Plus</code> returns 1, the result of this method must include the 
	 * energy required for turning. That is, the result must both be optimal in the energy required by both turns and moves.</li> 
	 * </ul>
	 */
	public abstract double getEnergyRequiredToReach(IRobot robot, long x, long y);
	
	/**
	 * Move <code>robot</code> as close as possible to <code>robot2</code> consuming as little energy as possible.
	 * 
	 * <ul>
	 * <li>If <code>isGetEnergyRequiredToReachAndMoveNextTo16Plus</code> returns 0, this method should not take into account the
	 * fact that turning requires energy. That is, the total number of moves performed by <code>robot</code> and <code>robot2</code> must be 
	 * minimized.</li>
	 * <li>If <code>isGetEnergyRequiredToReachAndMoveNextTo16Plus</code> returns 1, this method must take into account the fact
	 * that turning requires energy. That is, the total amount of energy consumed by <code>robot</code> and <code>robot2</code> must be 
	 * minimal.</li> 
	 * </ul>
	 */
	public abstract void moveNextTo(IRobot robot, IRobot robot2);
}