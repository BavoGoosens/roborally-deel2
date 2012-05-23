package roborally.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import roborally.IFacade;
import roborally.property.*;
import roborally.exception.IllegalPositionException;
import roborally.exception.TargetNotReachableException;


public class Facade implements IFacade<Board, Robot, Wall, Battery, RepairKit, SurpriseBox>{

	@Override
	public Board createBoard(long width, long height) {
		return new Board(width,height);
	}

	@Override
	public void merge(Board board1, Board board2) {
		board1.merge(board2);

	}

	@Override
	public Battery createBattery(double initialEnergy, int weight) {
		if(!Energy.isValidEnergyAmount(initialEnergy))
			return null;
		Energy initialEn = new Energy(initialEnergy);
		if(!Battery.isValidBatteryEnergy(initialEn))
			return null;
		if(weight < Weight.MINWEIGHT || weight > Weight.MAXWEIGHT)
			return null;
		return new Battery(initialEn ,new Weight(weight));
	}

	@Override
	public void putBattery(Board board, long x, long y, Battery battery) {
		try{
			battery.putOnBoard(board, new Position(x, y));
		}catch(IllegalPositionException e){
			System.err.println(e.getMessage());
		}catch(IllegalStateException e){
			System.err.println(e.getMessage());
		}
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
		if(!Energy.isValidEnergyAmount(repairAmount))
			return null;
		Energy initialEn = new Energy(repairAmount);
		if(!RepairKit.isValidRepairKitEnergy(initialEn))
			return null;
		if(weight < Weight.MINWEIGHT || weight > Weight.MAXWEIGHT)
			return null;
		return new RepairKit(initialEn, new Weight(weight));
	}

	@Override
	public void putRepairKit(Board board, long x, long y, RepairKit repairKit) {
		try{
			repairKit.putOnBoard(board, new Position(x, y));
		}catch(IllegalPositionException e){
			System.err.println(e.getMessage());
		}catch(IllegalStateException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public long getRepairKitX(RepairKit repairKit) throws IllegalStateException {
		return repairKit.getPosition().getX();
	}

	@Override
	public long getRepairKitY(RepairKit repairKit) throws IllegalStateException {
		return repairKit.getPosition().getY();
	}

	@Override
	public SurpriseBox createSurpriseBox(int weight) {
		if(weight < Weight.MINWEIGHT || weight > Weight.MAXWEIGHT)
			return null;
		return new SurpriseBox(new Weight(weight));
	}

	@Override
	public void putSurpriseBox(Board board, long x, long y,
			SurpriseBox surpriseBox) {
		try{
			surpriseBox.putOnBoard(board, new Position(x, y));
		}catch(IllegalPositionException e){
			System.err.println(e.getMessage());
		}catch(IllegalStateException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public long getSurpriseBoxX(SurpriseBox surpriseBox)
			throws IllegalStateException {
		return surpriseBox.getPosition().getX();

	}

	@Override
	public long getSurpriseBoxY(SurpriseBox surpriseBox)
			throws IllegalStateException {
		return surpriseBox.getPosition().getY();
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
		if(!Energy.isValidEnergyAmount(initialEnergy))
			return null;
		Energy initialEn = new Energy(initialEnergy);
		if(!Robot.isValidRobotEnergy(initialEn))
			return null;
		return new Robot (getOrientationEnum(orientation),new Energy(initialEnergy));
	}

	@Override
	public void putRobot(Board board, long x, long y, Robot robot) {
		try{
			robot.putOnBoard(board, new Position(x, y));
		}catch (IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public long getRobotX(Robot robot) throws IllegalStateException {
		return robot.getPosition().getX();
	}

	@Override
	public long getRobotY(Robot robot) throws IllegalStateException {
		return robot.getPosition().getY();
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
		try{
			robot.pickUp(repairKit);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch(IllegalStateException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void useRepairKit(Robot robot, RepairKit repairKit) {
		try{
			robot.use(repairKit);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}	
	}

	@Override
	public void dropRepairKit(Robot robot, RepairKit repairKit) {
		try{
			robot.drop(repairKit);
		}catch (IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch (IllegalStateException esc ){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void pickUpSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		try{
			robot.pickUp(surpriseBox);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch(IllegalStateException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void useSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		try{
			robot.use(surpriseBox);
		}catch(IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void dropSurpriseBox(Robot robot, SurpriseBox surpriseBox) {
		try{
			robot.drop(surpriseBox);
		}catch (IllegalArgumentException esc){
			System.err.println(esc.getMessage());
		}catch (IllegalStateException esc ){
			System.err.println(esc.getMessage());
		}
	}

	@Override
	public void transferItems(Robot from, Robot to) {
		ArrayList<Item> possessions = from.getPossessions();
		for(Item current: possessions)
			to.getPossessions().add(current);
		from.getPossessions().clear();
	}

	@Override
	public int isMinimalCostToReach17Plus() {
		return 1;
	}

	@Override
	public double getMinimalCostToReach(Robot robot, long x, long y) {
		try {
			return robot.getEnergyRequiredToReach(new Position(x, y)).getEnergy();
		}catch (TargetNotReachableException esc){
			System.err.println(esc.getMessage());
			return -1;
		}
	}

	@Override
	public int isMoveNextTo18Plus() {
		return 1;
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
		try {
			wall.putOnBoard(board, new Position(x, y));
		}catch (IllegalArgumentException exc){
			System.err.println(exc.getMessage());
		}
	}

	@Override
	public long getWallX(Wall wall) throws IllegalStateException,
	UnsupportedOperationException {
		return wall.getPosition().getX();
	}

	@Override
	public long getWallY(Wall wall) throws IllegalStateException,
	UnsupportedOperationException {
		return wall.getPosition().getY();
	}

	@Override
	public Set<Robot> getRobots(Board board) {
		Set<Entity> entities = board.getEntitiesOfType(Robot.class);
		Set<Robot> result = new HashSet<Robot>();
		for(Entity ent: entities)
			result.add((Robot) ent);
		return result;
	}

	@Override
	public Set<Wall> getWalls(Board board) throws UnsupportedOperationException {
		Set<Entity> entities = board.getEntitiesOfType(Wall.class);
		Set<Wall> result = new HashSet<Wall>();
		for(Entity ent: entities)
			result.add((Wall) ent);
		return result;
	}

	@Override
	public Set<RepairKit> getRepairKits(Board board) {
		Set<Entity> entities = board.getEntitiesOfType(RepairKit.class);
		Set<RepairKit> result = new HashSet<RepairKit>();
		for(Entity ent: entities)
			result.add((RepairKit) ent);
		return result;
	}

	@Override
	public Set<SurpriseBox> getSurpriseBoxes(Board board) {
		Set<Entity> entities = board.getEntitiesOfType(SurpriseBox.class);
		Set<SurpriseBox> result = new HashSet<SurpriseBox>();
		for(Entity ent: entities)
			result.add((SurpriseBox) ent);
		return result;
	}

	@Override
	public Set<Battery> getBatteries(Board board) {
		Set<Entity> entities = board.getEntitiesOfType(Battery.class);
		Set<Battery> result = new HashSet<Battery>();
		for(Entity ent: entities)
			result.add((Battery) ent);
		return result;
	}

	@Override
	public int loadProgramFromFile(Robot robot, String path) {
		try {
			robot.loadProgramFromFile(path);
			return 0;
		}catch (FileNotFoundException exc){
			System.err.println(exc.getMessage());
			return -1;
		}
	}

	@Override
	public int saveProgramToFile(Robot robot, String path) {
		return robot.saveProgramToFile(path);
	}

	@Override
	public void prettyPrintProgram(Robot robot, Writer writer) {
		try {
			writer.write(robot.getProgram().getPrettyPrint());
		} catch (IOException e) {
			System.err.println("Er is nog geen geldig programma ingelezen.");
		}
	}

	@Override
	public void stepn(Robot robot, int n) {
		robot.stepn(n);
	}

}