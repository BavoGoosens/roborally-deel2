package roborally.model;

import java.io.Writer;
import java.util.Set;

import roborally.IFacade;
import roborally.basics.*;


public class Facade implements IFacade<Board, Robot, Wall, Battery, RepairKit, SurpriseBox>{

	@Override
	public Board createBoard(long width, long height) {
		return new Board(width,height);
	}

	@Override
	public void merge(Board board1, Board board2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Battery createBattery(double initialEnergy, int weight) {
		return new Battery(new Energy(initialEnergy),new Weight(weight));
	}

	@Override
	public void putBattery(Board board, long x, long y, Battery battery) {
		board.putEntity(new Position (x , y), battery);
	}

	@Override
	public long getBatteryX(Battery battery) throws IllegalStateException {
		try{
			return battery.getPosition().getX();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public long getBatteryY(Battery battery) throws IllegalStateException {
		try{
			return battery.getPosition().getY();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public RepairKit createRepairKit(double repairAmount, int weight) {
		return new RepairKit(new Energy(repairAmount), new Weight(weight));
	}

	@Override
	public void putRepairKit(Board board, long x, long y, RepairKit repairKit) {
		board.putEntity(new Position(x, y), repairKit);
	}

	@Override
	public long getRepairKitX(RepairKit repairKit) throws IllegalStateException {
		try{
			return repairKit.getPosition().getX();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public long getRepairKitY(RepairKit repairKit) throws IllegalStateException {
		try{
			return repairKit.getPosition().getY();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public SurpriseBox createSurpriseBox(int weight) {
		return new SurpriseBox(new Weight(weight));
	}

	@Override
	public void putSurpriseBox(Board board, long x, long y,
			SurpriseBox surpriseBox) {
		board.putEntity(new Position(x, y), surpriseBox);
	}

	@Override
	public long getSurpriseBoxX(SurpriseBox surpriseBox)
			throws IllegalStateException {
		try{
			return surpriseBox.getPosition().getX();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public long getSurpriseBoxY(SurpriseBox surpriseBox)
			throws IllegalStateException {
		try{
			return surpriseBox.getPosition().getY();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
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
		return new Robot (getOrientationEnum(orientation),new Energy(initialEnergy));
	}

	@Override
	public void putRobot(Board board, long x, long y, Robot robot) {
		board.putEntity(new Position(x, y), robot);
	}

	@Override
	public long getRobotX(Robot robot) throws IllegalStateException {
		try{
			return robot.getPosition().getX();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public long getRobotY(Robot robot) throws IllegalStateException {
		try{
			return robot.getPosition().getY();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
			//gaat altyd juist returnen
			return -1;
		}
	}

	@Override
	public int getOrientation(Robot robot) {
		Orientation or = robot.getOrientation();
		return or.ordinal();
	}

	@Override
	public double getEnergy(Robot robot) {
		return robot.getEnergy().getEnergy();
	}

	@Override
	public void move(Robot robot) {
		try{
			robot.move();
		}catch (IllegalStateException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void turn(Robot robot) {
		robot.turnClockWise();
	}

	@Override
	public void pickUpBattery(Robot robot, Battery battery) {
		try{
			robot.pickUp(battery);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch(IllegalStateException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void useBattery(Robot robot, Battery battery) {
		try{
			robot.use(battery);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void dropBattery(Robot robot, Battery battery) {
		try{
			robot.drop(battery);
		}catch (IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch (IllegalStateException esc ){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void pickUpRepairKit(Robot robot, RepairKit repairKit) {
		robot.pickUp(repairKit);
	}

	@Override
	public void useRepairKit(Robot robot, RepairKit repairKit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropRepairKit(Robot robot, RepairKit repairKit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pickUpSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void useSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferItems(Robot from, Robot to) {
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
	public Set<Wall> getWalls(Board board) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<RepairKit> getRepairKits(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SurpriseBox> getSurpriseBoxes(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Battery> getBatteries(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int loadProgramFromFile(Robot robot, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveProgramToFile(Robot robot, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void prettyPrintProgram(Robot robot, Writer writer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepn(Robot robot, int n) {
		// TODO Auto-generated method stub

	}

}