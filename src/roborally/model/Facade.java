package roborally.model;

import java.io.Writer;
import java.util.Set;

import roborally.IFacade;
import roborally.basics.*;


public class Facade implements IFacade<Board, Robot<Item> , Wall, Battery, RepairKit, SurpriseBox>{

	@Override
	public Board createBoard(long width, long height) {
		Board result = new Board(width,height);
		return result;
	}

	@Override
	public void merge(Board board1, Board board2) {
		// TODO merge maken zodat dingen ernaast uitkomen
		board1.merge(board2);		
	}

	@Override
	public Battery createBattery(double initialEnergy, int weight) {
		Battery battery = new Battery(new Energy(initialEnergy), new Weight(weight));
		return battery;
	}

	@Override
	public void putBattery(Board board, long x, long y, Battery battery) {
		board.putEntity(new Position(x,y), battery);
	}

	@Override
	public long getBatteryX(Battery battery) throws IllegalStateException {
		return battery.getPosition().getX(); 
	}

	@Override
	public long getBatteryY(Battery battery) throws IllegalStateException {
		return battery.getPosition().getY(); 
	}

	@Override
	public RepairKit createRepairKit(double repairAmount, int weight) {
		// TODO repairkit maken
		RepairKit repair = new RepairKit(new Energy(repairAmount),new Weight(weight));
		
	}

	@Override
	public void putRepairKit(Board board, long x, long y, RepairKit repairKit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRepairKitX(RepairKit repairKit) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getRepairKitY(RepairKit repairKit) throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SurpriseBox createSurpriseBox(int weight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putSurpriseBox(Board board, long x, long y,
			SurpriseBox surpriseBox) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getSurpriseBoxX(SurpriseBox surpriseBox)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSurpriseBoxY(SurpriseBox surpriseBox)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Robot createRobot(int orientation, double initialEnergy) {
		// TODO Auto-generated method stub
		return null;
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
	public void pickUpBattery(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useBattery(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropBattery(Robot robot, Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pickUpRepairKit(Robot robot, RepairKit repairKit) {
		// TODO Auto-generated method stub
		
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
	public Set<Robot<Item>> getRobots(Board board) {
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