package roborally.program;

import java.util.HashSet;
import roborally.model.Entity;
import roborally.model.Robot;
import roborally.model.Wall;
import roborally.property.Energy;
import roborally.property.Position;
import roborally.util.AStarPath;
/**
 * Een klasse condities voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 1.0
 */
public class Condition extends Command {

	/**
	 * De enum die deze condition omschrijft.
	 */
	private ConditionEnum conditie; 
	
	/**
	 * De hoeveelheid energy die bij deze conditie hoort.
	 * (enkel van toepassing bij energy-at-least)
	 */
	private Energy energyContained;

	/**
	 * Constructor om een nieuwe condition aan te maken.
	 * 
	 * @param 	cnd
	 * 			De conditionEnum die het gedrag van de condition bepaalt.
	 * 
	 * @post	De conditie die bijgehouden wordt is de gegeven conditie.
	 */
	public Condition(ConditionEnum cnd) {
		setConditie(cnd);
	}
	
	/**
	 * Er wordt een nieuwe conditie aangemaakt als energy-at-least met als minimale hoeveelheid energie amountEnergy.
	 * 
	 * @param	energyAtLeast
	 * 			De conditie die aangemaakt wordt is energy-at-least.
	 * 
	 * @param	amountEnergy
	 * 			De minimale hoeveelheid energie bij deze conditie.
	 * 
	 * @post	De conditie in dit object is energy-at-least.
	 * 
	 * @post	De minimale energie in dit object is amountEnergy.
	 */
	public Condition(ConditionEnum energyAtLeast, double amountEnergy) {
		setConditie(energyAtLeast);
		setEnergyContained(new Energy(amountEnergy));
	}
	
	/**
	 * Deze methode geeft de enum terug die de conditie in dit object voorstelt.
	 * 
	 * @return	De enum die de conditie in dit object voorstelt.
	 */
	public ConditionEnum getConditie() {
		return this.conditie;
	}
	
	/**
	 * Deze methode stelt de enumeratie in die de conditie in dit object voorstelt.
	 * 
	 * @param	conditie
	 * 			De enumeratie die de conditie voorstelt die moet ingesteld worden.
	 * 
	 * @post	De conditie voor dit object is ingesteld op de gegeven conditie.
	 */
	public void setConditie(ConditionEnum conditie) {
		this.conditie = conditie;
	}
	
	/**
	 * Deze methode geeft de energie terug die bijgehouden wordt voor energy-at-least.
	 * 
	 * @return	De energie die bijgehouden wordt voor energy-at-least.
	 */
	public Energy getEnergyContained() {
		return this.energyContained;
	}
	
	/**
	 * Deze methode stelt de energie in die bijgehouden wordt voor energy-at-least.
	 * 
	 * @param	energyContained
	 * 			De energie die moet ingesteld worden.
	 * 
	 * @post	De energie in dit object is gelijk aan de gegeven energie.
	 */
	public void setEnergyContained(Energy energyContained) {
		this.energyContained = energyContained;
	}
	
	/**
	 * Deze methode evalueert de conditie in het object voor een gegeven robot.
	 * 
	 * @param	robot
	 * 			De robot waarvoor de conditie moet nagekeken worden.
	 * 
	 * @return	True indien aan de conditie voldoen wordt voor de gegeven robot, false indien niet.
	 */
	public boolean evaluate(Robot robot){
		if (getConditie() == ConditionEnum.AT_ITEM){
			if (robot.getBoard().getEntityOnPosition(robot.getPosition()).size() > 2 ){
				return true;
			}
			return false;
		}else if (getConditie() == ConditionEnum.CAN_HIT_ROBOT){
			Position beginpos = robot.getPosition();
			while(robot.getBoard().isValidPosition(AStarPath.getNextPosition(beginpos, robot.getOrientation()))){
				beginpos = AStarPath.getNextPosition(beginpos, robot.getOrientation());
				HashSet<Entity> content = robot.getBoard().getEntityOnPosition(beginpos);
				if(content != null){
					for (Entity ent : content){
						if (ent instanceof Robot){
							return true;
						}
					}
				}
			}
			return false;
		}else if (getConditie() == ConditionEnum.ENERGY_AT_LEAST){
			if (robot.getEnergy().getEnergy() >= this.getEnergyContained().getEnergy()){
				return true;
			}
			return false;
		}else if (getConditie() == ConditionEnum.WALL){
			Position pos = AStarPath.getNextPosition(robot.getPosition(),robot.getOrientation().getClockwiseOrientation());
			HashSet<Entity> content = robot.getBoard().getEntityOnPosition(pos);
			if(content != null){
				for (Entity ent : content){
					if (ent instanceof Wall){
						return true;
					}
				}
			}
			return false;
		}else
			return true;
	}
	
	/**
	 * Deze methode geeft een String terug die de conditie in dit object voorstelt.
	 * 
	 * @return	Een string die de conditie voorstelt die dit object bijhoudt.
	 */
	@Override
	public String toString() {
		if (this.energyContained == null)
			return "("+ this.conditie.toString()+")";
		return "("+ this.conditie.toString()+ " " + this.energyContained.getEnergy()+")";
	}
}
