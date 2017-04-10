package zainsgaming.rpg.interaction.text;

import java.util.List;
import java.util.Random;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.CombatEvent;
import zainsgaming.rpg.util.RandomUtil;

public class CombatInteractionAI extends CombatInteraction {

	/**
	 * Constructor. Set instance variables.
	 * @param character The current character for this interaction.
	 * @param event The current event.
	 */
	public CombatInteractionAI(ZGCharacter character, CombatEvent event){
		super(character, event);
	}
	
	@Override
	public void combatMenu() {
		System.out.println(getCharacter().getName() + "\'s Turn.");
		
		//Get the targets
		List<ZGCharacter> team = getEvent().getTeam();
		
		//Pick a target to attack.
		int idx =  RandomUtil.getRandom().nextInt(team.size());
		ZGCharacter target = team.get(idx);
		
		//Attack target
		attackTarget(target);
		
		System.out.println("----------");
	}
	
	private void attackTarget(ZGCharacter target){
		
		System.out.println("Attacking " + target.getName());
		
		//roll to hit
		ZGCharacter character = getCharacter();
		if (character.rollHit() >= target.getAC()){
			//If hit was successful, then roll attack
			int attackVal = character.rollAttack();
			target.takeHit(attackVal);
			System.out.println("Hit for: " + attackVal + ".");
			System.out.println(target.getName() + "\'s current HP: " + target.getCurrentHP() + ".");		
		} else {
			System.out.println("Attack miss.");
		}
	}

}
