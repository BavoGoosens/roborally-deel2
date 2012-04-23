package roborally.model;

import java.util.ArrayList;
import java.util.Collections;
import be.kuleuven.cs.som.annotate.*;
import roborally.basics.Position;
import roborally.interfaces.IRobot;
import roborally.utils.RouteComparator;
/**
 * Een klasse om robots voor te stellen.
 * 
 * @invar	De energie van een robot kan nooit kleiner zijn dan 0 en niet groter dan 20000.
 * 			|isValidEnergy(new.getEnergy())
 * 
 * @invar 	Een robot heeft altijd een geldige ori�ntatie.
 * 			|isValidDirection(new.getDirection)
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 */

public class Robot implements IRobot{

	private Position position;
	private double energy;
	private int direction;
	private final double maxEnergy = 20000;

	/**
	 * Maakt een nieuwe Robot aan
	 * 
	 * @param 	position
	 * 			De positie van de robot
	 * 
	 * @param 	direction
	 * 			De ori�ntatie van de robot: 0, 1, 2, 3 stellen respectievelijk boven, rechts, onder en links voor.
	 * 
	 * @param 	energy 
	 * 			De hoeveelheid initi�le energie in Ws.
	 * 
	 * @post 	De positie van de nieuwe robot is gelijk aan de gegeven positie.
	 * 			|new.getPosition().getX() == x
	 * 			|new.getPosition().getY() == y
	 * 
	 * @post	De energie van de nieuwe robot is gelijk aan de gegeven energie .
	 * 			|new.getEnergy() == energy
	 * 
	 * @post 	De ori�ntatie van de nieuwe robot is gelijk aan de gegeven ori�ntatie.
	 * 			|new.getDirection() == direction
	 * 
	 */
	public Robot(Position position,  int direction , double energy){
		setPosition(position);
		setEnergy(energy);
		setDirection(direction);
	}

	/**
	 * Methode om de ori�ntatie van een robot te wijzigen.
	 *  
	 * @param 	direction
	 * 			De integer die de ori�ntatie voorstelt die de robot moet krijgen.
	 * 
	 * @post	De direction van de robot is gelijk aan de gegeven parameter.
	 * 			|new.getDirection() == direction
	 * 
	 * @note	Indien de gegeven richting negatief was worden 1 en 3 omgewisseld. Dit is iets logischer wanneer bvb. 1 van de richting wordt afgetrokken en het resultaat -1 is. De implementatie is totaal.
	 */
	private void setDirection(int direction) {
		if(direction < 0){
			direction = Math.abs(direction % 4);
			if(direction == 1){
				direction = 3;
			}else if(direction == 3){
				direction = 1;
			}
		}
		this.direction = (direction % 4);
	}

	/**
	 * Methode om de energie van een robot te wijzigen.
	 * 
	 * @param	newEnergy
	 * 			Integer die de energie voorstelt die de robot moet krijgen.
	 * 
	 * @pre		newEnergy moet tussen 0 en het ingestelde maximum liggen.
	 * 			|isValidEnergy(newEnergy)
	 * 
	 * @post 	De energie van de robot bedraagt newEnergy.
	 * 			|new.getEnergy() == newEnergy
	 */
	private void setEnergy(double newEnergy) {
		assert isValidEnergy(newEnergy);
		this.energy = newEnergy;
	}

	/**
	 * Methode om de positie van een robot te wijzigen.
	 * 
	 * @param 	position
	 * 			|Nieuwe positie van de robot
	 * 
	 * @post 	De positie van de robot verandert naar die gegeven als parameters.
	 * 			|new.getPosition().getX() == x
	 * 			|new.getPosition().getY() == y
	 */
	private void setPosition(Position position){
		this.position = position;
	}


	/**
	 * Methode om de ori�ntatie van de robot te verkrijgen.
	 * 
	 * @return 	integer
	 * 			De ori�ntatie van de robot.
	 */
	@Basic
	public int getDirection(){
		return this.direction;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	double
	 * 			De energie van de robot.
	 */
	@Basic
	public double getEnergy(){
		return this.energy;
	}

	/**
	 * Methode om de positie van de robot te verkrijgen.
	 * 
	 * @return	Position
	 * 			De positie van de robot.
	 */
	@Basic
	public Position getPosition(){
		return this.position;
	}

	/**
	 * Methode om een robot 90 graden te draaien in wijzerszin.
	 * 
	 * @pre		De robot heeft een geldige richting en voldoende energie om te draaien.
	 * 			|this.getEnergy() >= 100
	 * 
	 * @post	De robot is 90 gedraaid in wijzerszin op het vlak en de waarde van direction is met 1 gestegen (of 0 geworden indien deze 3 was). De energie is met 100 gedaald.
	 * 			|new.getEnergy() == this.getEnergy() - 100
	 * 			|if(this.getDirection() == 0){
	 * 			|	new.getDirection() == 1
	 * 			|if(this.getDirection() == 1){
	 * 			|	new.getDirection() == 2
	 * 			|if(this.getDirection() == 2){
	 * 			|	new.getDirection() == 3
	 * 			|if(this.getDirection() == 3){
	 * 			|	new.getDirection() == 0
	 */
	public void turnClockWise(){
		assert isValidDirection(this.getDirection());
		assert isValidEnergy(this.getEnergy());
		if(this.getEnergy() >= 100){
			this.setDirection((this.getDirection() + 1) % 4);
			this.setEnergy(this.getEnergy() - 100);
		}
	}

	/**
	 * Methode om een robot 90 graden te draaien in tegenwijzerszin.
	 * 
	 * @pre		De robot heeft een geldige richting en voldoende energie om te draaien.
	 * 			|new.getEnergy() >= 100
	 * 
	 * @post	De robot is 90 gedraaid in tegenwijzerszin op het vlak en de waarde van direction is met 1 gedaald (of 3 geworden indien deze 0 was). De energie is met 100 gedaald.
	 * 			|new.getEnergy() == this.getEnergy() - 100
	 * 			|if(this.getDirection() == 0){
	 * 			|	new.getDirection() == 3
	 * 			|if(this.getDirection() == 1){
	 * 			|	new.getDirection() == 0
	 * 			|if(this.getDirection() == 2){
	 * 			|	new.getDirection() == 1
	 * 			|if(this.getDirection() == 3){
	 * 			|	new.getDirection() == 2
	 */
	public void turnCounterClockWise(){
		assert isValidDirection(this.getDirection());
		assert isValidEnergy(this.getEnergy());
		if(this.getEnergy() >= 100){
			switch (this.getDirection()){
			case 0:
				this.setDirection(3);
				break;
			case 1:
				this.setDirection(0);
				break;
			case 2:
				this.setDirection(1);
				break;
			case 3:
				this.setDirection(2);
				break;
			}
			this.setEnergy(this.getEnergy() - 100);
		}
	}

	/**
	 * Deze methode berekent de afstand van de robot tot een gegeven positie.
	 * 
	 * @param 	xpos
	 * 			X-co�rdinaat van de gegeven positie.
	 * 
	 * @param 	ypos
	 * 			Y-co�rdinaat van de gegeven positie.
	 * 
	 * @return	long
	 * 			Manhattan-afstand tussen de robot en de gegeven positie.
	 */
	private long calculateManhattanDistance(long xpos, long ypos){
		return calculateManhattanDistance(this.getPosition().getX(), this.getPosition().getY(), xpos, ypos);
	}

	/**
	 * Deze methode berekent de afstand van een gegeven positie tot een andere gegeven positie.
	 * 
	 * @param 	xpos1
	 * 			X-co�rdinaat van de beginpositie.
	 * 
	 * @param 	ypos1
	 * 			Y-co�rdinaat van de beginpositie.
	 * 
	 * @param 	xpos2
	 * 			X-co�rdinaat van de eindpositie.
	 * 
	 * @param 	ypos2
	 * 			Y-co�rdinaat van de eindpositie.
	 * 
	 * @return	long
	 * 			Manhattan-afstand tussen de beginpositie en de eindpositie.
	 */
	private long calculateManhattanDistance(long xpos1, long ypos1, long xpos2, long ypos2){
		return Math.abs(xpos2 - xpos1) + Math.abs(ypos2 - ypos1);
	}

	/**
	 * Methode die berekent hoeveel draaien er gemaakt moeten worden om een positie te bereiken vertrekkende van een beginpositie en vertelt of de volgende draai in wijzerszin of tegenwijzerszin moet.
	 * 
	 * @param	xpos1
	 * 			X-co�rdinaat van de startpositie.
	 * 
	 * @param	ypos1
	 * 			Y-co�rdinaat van de startpositie.
	 * 
	 * @param 	xpos2
	 * 			X-co�rdinaat van de te bereiken positie.
	 * 
	 * @param 	ypos2
	 * 			Y-co�rdinaat van de te bereiken positie.
	 * 
	 * @param	direction
	 * 			De richting van de robot die moet beginnen met bewegen.
	 * 
	 * @return	int[]
	 * 			Array van integers waarbij de eerste integer het aantal draaien voorstelt en de tweede integer gelijk is aan 0 voor wijzerszin en 1 voor tegenwijzerszin.
	 * 
	 * @note	Indien het niet uitmaakt of de eerste draai in wijzerszin of in tegenwijzerszin moet wordt 0 teruggegeven (wijzerszin).
	 */
	private int[] calculateTurnsToPosition(long xpos1, long ypos1, long xpos2, long ypos2, int direction){
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		if(xpos1 == xpos2 && ypos1 == ypos2)
			return result;
		/*
		 * In dit gedeelte kijken we de verhouding van de huidige robot met zijn bestemming na. Hier worden alle gevallen overlopen.
		 * Om dit visueel voor te stellen staan er letters die de posities voorstellen. De hoekpunten zijn in wijzerszin A, B, C en D.
		 * Vervolgens worden de middens van elke rand voorgesteld met E, F, G en H.
		 */
		if(xpos2 == xpos1 && ypos2 < ypos1){
			// E
			switch(direction){
			case 1:
				result[1] = 1;
			case 3:
				result[0] = 1;
				break;
			case 2:
				result[0] = 2;
				break;
			}
		}else if(xpos2 == xpos1 && ypos2 > ypos1){
			// G
			switch(direction){
			case 3:
				result[1] = 1;			
			case 1:
				result[0] = 1;
				break;
			case 0:
				result[0] = 2;
				break;
			}
		}else if(xpos2 > xpos1 && ypos2 == ypos1){
			// F
			switch(direction){
			case 2:
				result[1] = 1;
			case 0:
				result[0] = 1;
				break;
			case 3:
				result[0] = 2;
				break;
			}
		}else if(xpos2 > xpos1 && ypos2 < ypos1){
			// B
			switch(direction){
			case 1:
				result[1] = 1;
			case 0:
				result[0] = 1;
				break;
			case 2:
				result[1] = 1;
			case 3:
				result[0] = 2;
				break;
			}
		}else if(xpos2 > xpos1 && ypos2 > ypos1){
			// C
			switch(direction){
			case 2:
				result[1] = 1;
			case 1:
				result[0] = 1;
				break;
			case 3:
				result[1] = 1;
			case 0:
				result[0] = 2;	
				break;
			}
		}else if(xpos2 < xpos1 && ypos2 == ypos1){
			// H
			switch(direction){
			case 0:
				result[1] = 1;
			case 2:
				result[0] = 1;
				break;
			case 1:
				result[0] = 2;
				break;
			}
		}else if(xpos2 < xpos1 && ypos2 < ypos1){
			// A
			switch(direction){
			case 0:
				result[1] = 1;
			case 3:
				result[0] = 1;
				break;
			case 1:
				result[1] = 1;
			case 2:
				result[0] = 2;
				break;
			}
		}else if(xpos2 < xpos1 && ypos2 > ypos1){
			// D
			switch(direction){
			case 3:
				result[1] = 1;
			case 2:
				result[0] = 1;
				break;
			case 0:
				result[1] = 1;
			case 1:
				result[0] = 2;
				break;
			}
		}
		return result;
	}

	/**
	 * Methode die berekent hoeveel draaien er gemaakt moeten worden om een positie te bereiken en vertelt of de volgende draai in wijzerszin of tegenwijzerszin moet.
	 * 
	 * @param 	xpos
	 * 			X-co�rdinaat van de te bereiken positie.
	 * 
	 * @param 	ypos
	 * 			Y-co�rdinaat van de te bereiken positie..
	 * 
	 * @return	int[]
	 * 			Array van integers waarbij de eerste integer het aantal draaien voorstelt en de tweede integer gelijk is aan 0 voor wijzerszin en 1 voor tegenwijzerszin.
	 * 
	 * @note	Indien het niet uitmaakt of de eerste draai in wijzerszin of in tegenwijzerszin moet wordt 0 teruggegeven (wijzerszin).
	 */
	private int[] calculateTurnsToPosition(long xpos, long ypos){
		int[] result = new int[2];
		result = calculateTurnsToPosition(this.getPosition().getX(), this.getPosition().getY(), xpos, ypos, this.getDirection());
		return result;
	}

	/**
	 * Deze methode verplaatst een robot zo dicht mogelijk bij een andere robot op een zo effici�nt mogelijke manier.
	 * 
	 * @param	target
	 * 			De robot waarnaar de huidige robot verplaatst moet worden.
	 * 
	 * @effect	De huidige robot staat zo dicht mogelijk bij de gegeven robot zonder een ineffici�nte beweging te maken.
	 */
	public void moveNextTo(Robot target){
		if((this.getEnergy() >= 500 || target.getEnergy() >= 500)){
			// Enkel uitvoeren indien een van beide voldoende energie heeft om te bewegen.

			// Berekingen nodig voor de gevallenonderscheiding. ManhattanAlternatives zijn uitzonderingen waarbij het beter is om niet naar de andere robot te bewegen maar beide robots te laten bewegen.
			long[] altpos = new long [2];
			altpos = this.getCloseTargetCo�rdinate(target.getPosition().getX(), target.getPosition().getY());
			double thisNeededEnergy = this.getEnergyBasedOnTurnsAndMoves(altpos[0], altpos[1]);
			altpos = target.getCloseTargetCo�rdinate(this.getPosition().getX(), this.getPosition().getY());
			double targetNeededEnergy = target.getEnergyBasedOnTurnsAndMoves(altpos[0], altpos[1]);
			double ManhattanAlternative1a = this.getEnergyBasedOnTurnsAndMoves(target.getPosition().getX(), this.getPosition().getY());
			altpos =  target.getCloseTargetCo�rdinate(target.getPosition().getX(), this.getPosition().getY());
			double ManhattanAlternative1b = target.getEnergyBasedOnTurnsAndMoves(altpos[0],altpos[1]);
			double ManhattanAlternative2a = this.getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), target.getPosition().getY());
			altpos = target.getCloseTargetCo�rdinate(this.getPosition().getX(), target.getPosition().getY());
			double ManhattanAlternative2b = target.getEnergyBasedOnTurnsAndMoves(altpos[0], altpos[1]);
			double ManhattanAlternative3b = target.getEnergyBasedOnTurnsAndMoves(target.getPosition().getX(), this.getPosition().getY());
			altpos = this.getCloseTargetCo�rdinate(target.getPosition().getX(), this.getPosition().getY());
			double ManhattanAlternative3a = this.getEnergyBasedOnTurnsAndMoves(altpos[0], altpos[1]);
			double ManhattanAlternative4b = target.getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), target.getPosition().getY());
			altpos = this.getCloseTargetCo�rdinate(this.getPosition().getX(), target.getPosition().getY());
			double ManhattanAlternative4a = this.getEnergyBasedOnTurnsAndMoves(altpos[0], altpos[1]);			
			double ManhattanAlternative1 = ManhattanAlternative1a + ManhattanAlternative1b;
			double ManhattanAlternative2 = ManhattanAlternative2a + ManhattanAlternative2b;
			double ManhattanAlternative3 = ManhattanAlternative3a + ManhattanAlternative3b;
			double ManhattanAlternative4 = ManhattanAlternative4a + ManhattanAlternative4b;
			ArrayList<double[]> enoughEnergy = new ArrayList<double[]>();
			if(this.getEnergy() >= thisNeededEnergy){
				double[] addToList = new double[4];
				addToList[0] = 1;
				addToList[1] = thisNeededEnergy;
				addToList[2] = thisNeededEnergy;
				addToList[3] = 0;
				enoughEnergy.add(addToList);
			}
			if(target.getEnergy() >= targetNeededEnergy){
				double[] addToList = new double[4];
				addToList[0] = 2;
				addToList[1] = targetNeededEnergy;
				addToList[2] = 0;
				addToList[3] = targetNeededEnergy;
				enoughEnergy.add(addToList);
			}
			if(this.getEnergy() >= ManhattanAlternative1a && target.getEnergy() >= ManhattanAlternative1b){
				double[] addToList = new double[4];
				addToList[0] = 3;
				addToList[1] = ManhattanAlternative1;
				addToList[2] = ManhattanAlternative1a;
				addToList[3] = ManhattanAlternative1b;
				enoughEnergy.add(addToList);
			}
			if(this.getEnergy() >= ManhattanAlternative2a && target.getEnergy() >= ManhattanAlternative2b){
				double[] addToList = new double[4];
				addToList[0] = 4;
				addToList[1] = ManhattanAlternative2;
				addToList[2] = ManhattanAlternative2a;
				addToList[3] = ManhattanAlternative2b;
				enoughEnergy.add(addToList);
			}
			if(this.getEnergy() >= ManhattanAlternative3a && target.getEnergy() >= ManhattanAlternative3b){
				double[] addToList = new double[4];
				addToList[0] = 5;
				addToList[1] = ManhattanAlternative3;
				addToList[2] = ManhattanAlternative3a;
				addToList[3] = ManhattanAlternative3b;
				enoughEnergy.add(addToList);
			}
			if(this.getEnergy() >= ManhattanAlternative4a && target.getEnergy() >= ManhattanAlternative4b){
				double[] addToList = new double[4];
				addToList[0] = 6;
				addToList[1] = ManhattanAlternative4;
				addToList[2] = ManhattanAlternative4a;
				addToList[3] = ManhattanAlternative4b;
				enoughEnergy.add(addToList);
			}

			Collections.sort(enoughEnergy, new RouteComparator());

			if(this.getPosition().getX() == target.getPosition().getX() && this.getPosition().getY() == target.getPosition().getY()){
				// De robots staan op elkaar.
				this.moveRobotsOnSamePlace(target);
			}else if(enoughEnergy.size() > 0){
				// Er is een mogelijk traject gevonden.
				double[] chosenRoute = new double[2];
				chosenRoute = enoughEnergy.get(0);
				long[] destination2 = new long[2];
				switch((int) chosenRoute[0]){
				case 1:
					// Het ideale traject is this naast target bewegen.
					this.moveTo(target);
					break;
				case 2:
					// Het ideale traject is target naast this bewegen.
					target.moveTo(this);
					break;
				case 3:
					// Het ideale traject is this naar (target.getPosition().getX(), this.getPosition().getY()) bewegen en target ernaast.
					destination2 = target.getCloseTargetCo�rdinate(target.getPosition().getX(), this.getPosition().getY());
					this.moveTo(target.getPosition().getX(), this.getPosition().getY(), false);
					target.moveTo(destination2[0], destination2[1], false);
					break;
				case 4:
					// Het ideale traject is this naar (this.getPosition().getX(), target.getPosition().getY()) bewegen en target ernaast.
					destination2 = target.getCloseTargetCo�rdinate(this.getPosition().getX(), target.getPosition().getY());
					this.moveTo(this.getPosition().getX(), target.getPosition().getY(), false);
					target.moveTo(destination2[0], destination2[1], false);
					break;
				case 5:
					// Het ideale traject is target naar (target.getPosition().getX(), this.getPosition().getY()) bewegen en this ernaast.
					destination2 = this.getCloseTargetCo�rdinate(target.getPosition().getX(), this.getPosition().getY());
					target.moveTo(target.getPosition().getX(), this.getPosition().getY(), false);
					this.moveTo(destination2[0], destination2[1], false);
					break;
				case 6:
					// Het ideale traject is target naar (this.getPosition().getX(), target.getPosition().getY()) bewegen en this ernaast.
					destination2 = this.getCloseTargetCo�rdinate(this.getPosition().getX(), target.getPosition().getY());
					target.moveTo(this.getPosition().getX(), target.getPosition().getY(), false);
					this.moveTo(destination2[0], destination2[1], false);
					break;
				}
			}else{
				// beide robots moeten bewegen want geen van beide heeft voldoende energie om het traject alleen af te leggen
				this.moveAsCloseAsPossibleTo(target);
			}
		}
	}

	/**
	 * Deze methode beweegt 2 robots zo dicht mogelijk bij elkaar. Enkel te gebruiken bij speciale gevallen van moveNextTo().
	 * 
	 * @param 	robot
	 * 			De waarnaar de huidige robot moet bewegen.
	 * 
	 * @effect	De huidige robot en de gegeven robot staan zo dicht mogelijk bij elkaar.
	 */
	private void moveAsCloseAsPossibleTo(Robot robot){
		double thisEnergy = this.getEnergyBasedOnTurnsAndMoves(robot.getPosition().getX(), robot.getPosition().getY());
		double robotEnergy = robot.getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), this.getPosition().getY());
		while ((this.getEnergy() >= 500 || robot.getEnergy() >= 500) && !this.isNextTo(robot)){
			if(((thisEnergy > robotEnergy && robot.getEnergy() >= 500)) || this.getEnergy() < 500){
				//robot zal bewegen, this blijft staan.
				int[] turnCalculations = robot.calculateTurnsToPosition(this.getPosition().getX(), this.getPosition().getY());
				if(turnCalculations[0] > 1 && robot.getEnergy() >= 700){
					if(turnCalculations[1] == 0){
						robot.turnClockWise();
					}else{
						robot.turnCounterClockWise();
					}
				}else if(turnCalculations[0] == 0 || turnCalculations[0] == 1){
					if(turnCalculations[1] == 0){
						robot.turnClockWise();
					}else{
						robot.turnCounterClockWise();
					}
					if(robot.getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), this.getPosition().getY()) >= robotEnergy){
						if(turnCalculations[1] == 1){
							robot.turnClockWise();
						}else{
							robot.turnCounterClockWise();
						}
						robot.recharge(200);
						robot.move();
					}
				}else{
					break;
				}
			}else if(this.getEnergy() >= 500 || robot.getEnergy() < 500){
				//this zal bewegen, robot blijft staan
				int[] turnCalculations = this.calculateTurnsToPosition(robot.getPosition().getX(), robot.getPosition().getY());
				if(turnCalculations[0] > 1 && this.getEnergy() >= 700){
					if(turnCalculations[1] == 0){
						this.turnClockWise();
					}else{
						this.turnCounterClockWise();
					}
				}else if(turnCalculations[0] == 0 || turnCalculations[0] == 1){
					if(turnCalculations[1] == 0){
						this.turnClockWise();
					}else{
						this.turnCounterClockWise();
					}
					if(this.getEnergyBasedOnTurnsAndMoves(robot.getPosition().getX(), robot.getPosition().getY()) >= thisEnergy){
						if(turnCalculations[1] == 1){
							this.turnClockWise();
						}else{
							this.turnCounterClockWise();
						}
						this.recharge(200);
						this.move();
					}
				}else{
					break;
				}
			}else{
				// niet genoeg energie
				break;
			}
			thisEnergy = this.getEnergyBasedOnTurnsAndMoves(robot.getPosition().getX(), robot.getPosition().getY());
			robotEnergy = robot.getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), this.getPosition().getY());
		}
	}

	/**
	 * Deze methode beweegt naar de gegeven co�rdinaten. Met de boolean closeOrNot kan ervoor gezorgd worden dat de robot tot net naast de gegeven positie beweegt.
	 * 
	 * @param	xpos
	 * 			X-co�rdinaat van de te bereiken positie.
	 * 
	 * @param	ypos
	 * 			Y-co�rdinaat van de te bereiken positie.
	 * 
	 * @param	closeOrNot
	 * 			Indien deze true is wordt de robot slechts tot net naast de gegeven positie bewogen. Indien deze false is wordt de robot tot op de gegeven positie bewogen.
	 * 
	 * @pre		De gegeven positie moet een geldige positie zijn.
	 * 			|isValidPosition(xpos, ypos)
	 * 
	 * @note	Alle bewegingen gebeuren zo effici�nt mogelijk.
	 * 
	 * @effect	De nieuwe positie van de robot is (xpos, ypos) (indien closeOrNot false was) of net naast (xpos, ypos) (indien closeOrNot true was).
	 */
	private void moveTo(long xpos, long ypos, boolean closeOrNot){
		long[] pos = new long[2];
		pos[0] = xpos;
		pos[1] = ypos;
		if(closeOrNot)
			pos = this.getCloseTargetCo�rdinate(xpos, ypos);
		int[] turns = this.calculateTurnsToPosition(pos[0], pos[1]);
		this.setEnergy(this.getEnergy() - this.calculateManhattanDistance(pos[0], pos[1]) * 500);
		Position position = new Position(pos[0], pos[1]);
		this.setPosition(position);
		if(turns[0] == 1){
			if(turns[1] == 0){
				this.turnClockWise();
			}else{
				this.turnCounterClockWise();
			}
		}else if(turns[0] == 2){
			this.turnClockWise();
			this.turnClockWise();
		}
	}

	private void moveTo(Robot target){
		moveTo(target.getPosition().getX(), target.getPosition().getY(), true);
	}

	/**
	 * Een methode die robots die op dezelfde plaats staan van elkaar weg beweegt.
	 * 
	 * @param	other
	 * 			De tweede robot op deze positie.
	 * 
	 * @pre		Een van beide robots heeft voldoende energie om te bewegen.
	 * 			|this.getEnergy() >= 500 || other.getEnergy() >= 500
	 * 
	 * @effect	Robots die op dezelfde plaats stonden zijn zo effici�nt mogelijk naast elkaar verplaatst rekening houdend met de beschikbare energie.
	 */
	private void moveRobotsOnSamePlace(Robot other){
		boolean moveThis = true;
		boolean moveOther = true;

		if(this.getEnergy() >= 500){
			if(this.getPosition().getX() == 0 && this.getDirection() == 3){
				moveThis = false;
			}
			if(this.getPosition().getY() == 0 && this.getDirection() == 0){
				moveThis = false;
			}
			if(this.getPosition().getX() == Long.MAX_VALUE && this.getDirection() == 1){
				moveThis = false;
			}
			if(this.getPosition().getY() == Long.MAX_VALUE && this.getDirection() == 2){
				moveThis = false;
			}
		}else{
			moveThis = false;
		}
		if(other.getEnergy() >= 500){
			if(other.getPosition().getX() == 0 && other.getDirection() == 3){
				moveOther = false;
			}
			if(other.getPosition().getY() == 0 && other.getDirection() == 0){
				moveOther = false;
			}
			if(other.getPosition().getX() == Long.MAX_VALUE && other.getDirection() == 1){
				moveOther = false;
			}
			if(other.getPosition().getY() == Long.MAX_VALUE && other.getDirection() == 2){
				moveOther = false;
			}
		}else{
			moveOther = false;
		}

		if(!moveThis && !moveOther){
			if(this.getEnergy() >= 600){
				if(this.getPosition().getX() == 0 && this.getPosition().getY() == 0){
					if(this.getDirection() == 0){
						this.turnClockWise();
					}else if(this.getDirection() == 3){
						this.turnCounterClockWise();
					}
				}else if(this.getPosition().getX() == Long.MAX_VALUE && this.getPosition().getY() == Long.MAX_VALUE){
					if(this.getDirection() == 1){
						this.turnCounterClockWise();
					}else if(this.getDirection() == 2){
						this.turnClockWise();
					}
				}else if(this.getPosition().getX() == 0 && this.getPosition().getY() == Long.MAX_VALUE){
					if(this.getDirection() == 2){
						this.turnCounterClockWise();
					}else if(this.getDirection() == 3){
						this.turnClockWise();
					}
				}else if(this.getPosition().getX() == Long.MAX_VALUE && this.getPosition().getY() == 0){
					if(this.getDirection() == 0){
						this.turnCounterClockWise();
					}else if(this.getDirection() == 1){
						this.turnClockWise();
					}
				}else{
					this.turnClockWise();
				}
				moveThis = true;
			}else if(other.getEnergy() >= 600){
				if(other.getPosition().getX() == 0 && other.getPosition().getY() == 0){
					if(other.getDirection() == 0){
						other.turnClockWise();
					}else if(other.getDirection() == 3){
						other.turnCounterClockWise();
					}
				}else if(other.getPosition().getX() == Long.MAX_VALUE && other.getPosition().getY() == Long.MAX_VALUE){
					if(other.getDirection() == 1){
						other.turnCounterClockWise();
					}else if(other.getDirection() == 2){
						other.turnClockWise();
					}
				}else if(other.getPosition().getX() == 0 && other.getPosition().getY() == Long.MAX_VALUE){
					if(other.getDirection() == 2){
						other.turnCounterClockWise();
					}else if(other.getDirection() == 3){
						other.turnClockWise();
					}
				}else if(other.getPosition().getX() == Long.MAX_VALUE && other.getPosition().getY() == 0){
					if(other.getDirection() == 0){
						other.turnCounterClockWise();
					}else if(other.getDirection() == 1){
						other.turnClockWise();
					}
				}else{
					other.turnClockWise();
				}
				moveOther = true;
			}
		}
		if(moveThis){
			this.move();
		}else if(moveOther){
			other.move();
		}
	}

	/**
	 * Deze methode geeft het energieverbruik terug om de robot te verplaatsen naar de plaats gegeven in de parameters (zie nota).
	 * 
	 * @param	xpos
	 * 			X-co�rdinaat van de te bereiken positie.
	 * 
	 * @param	ypos
	 * 			Y-co�rdinaat van de te bereiken positie.
	 * 
	 * @return	double
	 * 			De berekende energie op basis van het aantal bewegingen.
	 * 
	 * @note	Deze methode houdt enkel rekening met het aantal moves en turns maar negeert effici�ntere manieren om op een positie te geraken.
	 */
	private double getEnergyBasedOnTurnsAndMoves(long xpos, long ypos){
		return getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), this.getPosition().getY(), xpos, ypos, this.getDirection());
	}

	/**
	 * Deze methode geeft het energieverbruik terug om een robot van (xpos1, ypos1) te verplaatsen naar (xpos2, ypos2) (zie nota).
	 * 
	 * @param 	xpos1
	 * 			X-co�rdinaat van de beginpositie.
	 * 
	 * @param 	ypos1
	 * 			Y-co�rdinaat van de beginpositie.
	 * 
	 * @param 	xpos2
	 * 			X-co�rdinaat van de te bereiken positie.
	 * 
	 * @param 	ypos2
	 * 			Y-co�rdinaat van de te bereiken positie.
	 * 
	 * @param	direction
	 * 			De richting van de robot op de beginpositie.
	 * 
	 * @return	double
	 * 			De berekende energie op basis van het aantal bewegingen.
	 * 
	 * @note	Deze methode houdt enkel rekening met het aantal moves en turns maar negeert effici�ntere manieren om op een positie te geraken.
	 */
	private double getEnergyBasedOnTurnsAndMoves(long xpos1, long ypos1, long xpos2, long ypos2, int direction){
		if(xpos1 == xpos2 && ypos1 == ypos2)
			return 0;
		double moveConsumption = (double) (500 * calculateManhattanDistance(xpos1, ypos1, xpos2, ypos2));
		int[] turnCalculations = calculateTurnsToPosition(xpos1, ypos1, xpos2, ypos2, direction);
		double turnConsumption = turnCalculations[0] * 100;	
		return turnConsumption + moveConsumption;
	}

	/**
	 * Methode die de minimale energieconsumptie naar een gegeven positie (x,y) berekent.
	 * 
	 * @param 	xpos
	 * 			X-co�rdinaat van de gegeven te bereiken positie
	 * 
	 * @param 	ypos
	 * 			Y-co�rdinaat van de gegeven te bereiken positie
	 * 	
	 * @return 	double 
	 * 			Met de energie die de robot voor deze verplaatsing zal gebruiken
	 */
	public double getEnergyRequiredToReach(long xpos, long ypos){
		if(this.getPosition().getX() == xpos && this.getPosition().getY() == ypos)
			return 0;
		double thisNeededEnergy = this.getEnergyBasedOnTurnsAndMoves(xpos, ypos);
		double ManhattanAlternative1 = getEnergyBasedOnTurnsAndMoves(xpos, this.getPosition().getY()) + getEnergyBasedOnTurnsAndMoves(xpos, this.getPosition().getY(), this.getPosition().getX(), this.getPosition().getY(), this.getDirection());
		double ManhattanAlternative2 = getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), ypos) + getEnergyBasedOnTurnsAndMoves(this.getPosition().getX(), ypos, this.getPosition().getX(), this.getPosition().getY(), this.getDirection());
		if(ManhattanAlternative1 < thisNeededEnergy && ManhattanAlternative1 != 0){
			// Effici�nter om Manhattan alternatief 1 te gebruiken (target.getPosition().getX(), this.getPosition().getY()).
			return ManhattanAlternative1;
		}else if(ManhattanAlternative2 < thisNeededEnergy && ManhattanAlternative2 != 0){
			// Effici�nter om Manhattan alternatief 2 te gebruiken (this.getPosition().getX(), target.getPosition().getY()).
			return ManhattanAlternative2;
		}else{
			// Manhattan alternatieven zijn niet effici�nter
			return thisNeededEnergy;
		}
	}

	/**
	 * Deze methode berekent de verhouding tussen een gegeven hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @param 	energy
	 * 			Hoeveelheid energie om verhouding met maximum van te berekenen.
	 * 
	 * @return	double
	 * 			Met de verhouding tussen een gegeven hoeveelheid energie en de maximale hoeveelheid energie.
	 */
	public double calculateEnergyFraction(double energy){
		return energy/this.maxEnergy;
	}

	/**
	 * Deze methode berekent de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @return Double met de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 */
	public double calculateEnergyFraction(){
		return this.getEnergy()/this.maxEnergy;
	}

	/**
	 * Methode die het co�rdinaat zo dicht mogelijk naast het gegeven co�rdinaat teruggeeft.
	 * 
	 * @param 	xpos
	 * 		X-co�rdinaat waarnaar bewogen moet worden.
	 * 
	 * @param 	ypos
	 * 		Y-co�rdinaat waarnaar bewogen moet worden.
	 * 
	 * @return	long[]
	 * 		Een array met 2 longs waarvan de eerste de gezochte x-co�rdinaat is en de tweede de gezochte y-co�rdinaat.
	 * 
	 */
	private long[] getCloseTargetCo�rdinate(long xpos , long ypos){
		long[] result = new long[2];
		int[] turns = this.calculateTurnsToPosition(xpos, ypos);
		if (turns[0] == 0){
			if(this.getDirection() == 0 ){
				result[0] = this.getPosition().getX();
				result[1] = ypos + 1;
				return result;
			}
			else if (this.getDirection() == 1){
				result[0] = xpos - 1;
				result[1] = this.getPosition().getY();
				return result;
			}
			else if (this.getDirection() == 2){
				result[0] = this.getPosition().getX();
				result[1] = ypos - 1;
				return result;
			}
			result[0] = xpos + 1;
			result[1] = this.getPosition().getY();
			return result;
		}
		else if ((turns[0] == 1) && (turns[1] == 0)){
			if (this.getDirection() == 0){
				result[0] = xpos - 1;
				result[1] = ypos;
				return result;
			}
			else if (this.getDirection() == 1){
				result[0] = xpos;
				result[1] = ypos - 1;
				return result;
			}
			else if (this.getDirection() == 2){
				result[0] = xpos + 1;
				result[1] = ypos;
				return result;
			}
			if(xpos == this.getPosition().getX()){
				result[0] = this.getPosition().getX();
				result[1] = ypos + 1;
				return result;
			}
			result[0] = xpos;
			result[1] = ypos + 1;
			return result;
		}
		else if ((turns[0] == 1) && (turns[1] == 1)){
			if (this.getDirection() == 0){
				result[0] = xpos + 1;
				result[1] = ypos;
				return result;
			}
			else if (this.getDirection() == 1){
				result[0] = xpos;
				result[1] = ypos + 1;
				return result;
			}
			else if (this.getDirection() == 2){
				result[0] = xpos - 1;
				result[1] = ypos;
				return result;
			}
			if(xpos == this.getPosition().getX()){
				result[0] = this.getPosition().getX();
				result[1] = ypos - 1;
				return result;
			}
			result[0] = xpos;
			result[1] = ypos - 1;
			return result;
		}
		else if ((turns[0] == 2) && (turns[1] == 0)){
			if ((this.getPosition().getX() != xpos) && (this.getPosition().getY() != ypos)){
				if (this.getDirection() == 0){
					result[0] = xpos;
					result[1] = ypos - 1;
					return result;
				}
				else if (this.getDirection() == 1){
					result[0] = xpos + 1;
					result[1] = ypos;
					return result;
				}
				else if (this.getDirection() == 2){
					result[0] = xpos;
					result[1] = ypos + 1;
					return result;
				}
				result[0] = xpos - 1;
				result[1] = ypos;
				return result;
			}
			if (this.getDirection() == 0){
				result[0] = xpos;
				result[1] = ypos - 1;
				return result;
			}
			else if (this.getDirection() == 1){
				result[0] = xpos + 1;
				result[1] = ypos;
				return result;
			}
			else if (this.getDirection() == 2){
				result[0] = xpos;
				result[1] = ypos + 1;
				return result;
			}
			result[0] = xpos - 1;
			result[1] = ypos;
			return result;
		}
		else if ((turns[0] == 2) && (turns[1] == 1)){
			if ((this.getPosition().getX() != xpos) && (this.getPosition().getY() != ypos)){
				if (this.getDirection() == 0){
					result[0] = xpos;
					result[1] = ypos - 1;
					return result;
				}
				else if (this.getDirection() == 1){
					result[0] = xpos + 1;
					result[1] = ypos;
					return result;
				}
				else if (this.getDirection() == 2){
					result[0] = xpos;
					result[1] = ypos + 1;
					return result;
				}
				result[0] = xpos - 1;
				result[1] = ypos;
				return result;
			}
		}
		return null;
	}

	/**
	 * Methode die een robot 1 stap in de richting waarnaar hij kijkt beweegt.
	 * 
	 * @pre 	De robot moet voldoende energie hebben (500 of meer).
	 * 			|this.getEnergy() >= 500
	 * 
	 * @effect 	De robot staat 1 stap verder (in de richting waarnaar hij keek) en verliest 500Ws energie.
	 * 
	 * @post 	De robot staat 1 stap verder (in de richting waarnaar hij keek).
	 * 			|if (getDirection() == O)
	 * 			|				(new this).getY = this.getPosition().getY() - 1 
	 * 			|if (getDirection() == 1)
	 * 			|				(new this).getX = this.getPosition().getX() + 1
	 * 			|if (getDirection() == 2)
	 * 			|				(new this).getY = this.getPosition().getY() + 1
	 * 			|if (getDirection() == 3)
	 * 			|				(new this).getX = this.getPosition().getX() - 1
	 * 
	 * @post 	De robot verliest 500Ws energie.
	 * 			| (new this).getEnergy() = this.getEnergy() - 500
	 * 
	 * @throws	IllegalStateException
	 * 			De robot kan niet buiten het bord bewegen.
	 * 			|this.getDirection() == 0 && !Position.isValidPosition(getPosition().getX(), getPosition().getY() - 1)
	 * 			|this.getDirection() == 1 && !Position.isValidPosition(getPosition().getX() + 1, getPosition().getY())
	 * 			|this.getDirection() == 2 && !Position.isValidPosition(getPosition().getX(), getPosition().getY() + 1)
	 * 			|this.getDirection() == 3 && !Position.isValidPosition(getPosition().getX() - 1, getPosition().getY())
	 * 			
	 */
	public void move() throws IllegalStateException{
		assert isValidDirection(this.getDirection()) && isValidEnergy(this.getEnergy()) && this.getEnergy() >= 500;
		if(this.getEnergy() >= 500){
			Position destination = null;
			try{
				switch (this.getDirection()){
				case 0:
					destination = new Position(getPosition().getX(), getPosition().getY() - 1);
					break;
				case 1:
					destination = new Position(getPosition().getX() + 1, getPosition().getY());
					break;
				case 2:
					destination = new Position(getPosition().getX(), getPosition().getY() + 1);
					break;
				case 3:
					destination = new Position(getPosition().getX() - 1, getPosition().getY());
					break;
				}
			}
			catch (IllegalArgumentException e){
				throw new IllegalStateException("De robot kan niet buiten het bord bewegen.");
			}
			this.setPosition(destination);
			this.setEnergy(getEnergy() - 500);
		}
	}

	/**
	 * Methode die een robot zijn energie herlaadt met een expliciete waarde.
	 * 
	 * @pre		Charge moet zelf een geldige hoeveelheid energie zijn.
	 * 			|isValidEnergy(charge)
	 * 
	 * @param 	charge
	 * 			Integer die het aantal watt energie waarmee herladen wordt voorstelt.
	 * 
	 * @post 	De energie van de robot is verhoogd met charge of tot aan het maximum.
	 * 			|(new this).getEnergy() = this.getEnergy() + charge
	 * 
	 */
	public void recharge(double charge){
		assert isValidEnergy(charge);
		assert isValidEnergy(this.getEnergy());
		if(isValidEnergy(charge)){
			double afterCharge = this.getEnergy() + charge;
			if(afterCharge <= this.maxEnergy){
				this.setEnergy(afterCharge);
			}else{
				this.setEnergy(this.maxEnergy);
			}
		}
	}

	/**
	 * Checkt of de gegeven orientatie toegestaan is.
	 * 
	 * @param 	dir
	 * 		Integer die de ori�ntatie van een robot voorstelt.
	 * 
	 * @return boolean 
	 * 		Deze geeft true terug als een bepaalde ori�ntatie toegestaan is.
	 */
	public boolean isValidDirection(int dir){
		return ((dir >= 0) && (dir <= 3));
	}

	/**
	 * Checkt of de gegeven energy een toegestane waarde is.
	 * 
	 * @param  	energy
	 * 			De gegeven energy die nagekeken moet worden.
	 * 
	 * @return 	boolean 
	 * 			Deze geeft true terug wanneer energy een toegestane waarde is.
	 */
	public boolean isValidEnergy(double energy){
		return (energy >= 0) && (energy <= this.maxEnergy);
	}


	/**
	 * Methode die nagaat of 2 robots naast elkaar staan.
	 * 
	 * @param 	robot
	 * 			De andere robot wiens positie met de huidige wordt vergeleken. 
	 * 
	 * @return	boolean
	 * 			Deze geeft true terug als de gegeven robot naast de huidige staat.
	 */
	private boolean isNextTo(Robot robot){
		if ((this.getPosition().getX() == robot.getPosition().getX()) && ((this.getPosition().getY() + 1 == robot.getPosition().getY())||(this.getPosition().getY() == robot.getPosition().getY()+ 1)))
			return true;
		else if ((this.getPosition().getY() == robot.getPosition().getY()) && ((this.getPosition().getX() + 1 == robot.getPosition().getX())|| (this.getPosition().getX() == robot.getPosition().getX() + 1)))
			return true;
		return false; 
	}
}