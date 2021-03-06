package zainsgaming.rpg.characters;

import zainsgaming.rpg.ZGObject;
import zainsgaming.rpg.items.Weapon;
import zainsgaming.rpg.util.AttackTypes;
import zainsgaming.rpg.util.DiceUtil;

public class ZGCharacter extends ZGObject{

	// Person's stats
	private int str, dex, con, intl, wis, cha ;
	private int maxHP, currentHP;
	private boolean isFriendly;
	private boolean isDefending;

	//Equipment
	private Weapon equippedWeapon;

	/**
	 * Basic constructor. Sets stats to default values.
	 * @param name The name to set.
	 */
	public ZGCharacter(String name){
		this(name, ZGCharacterUtil.DEFAULT_STATS, false, false);
	}

	/**
	 * Constructor. Sets instance variables to provided values.
	 * @param name The name to set.
	 * @param stats The stats. Order: Max HP, STR, DEX, CON, INTL, WIS, CHA
	 * @param isFriendly - true if the character is friendly to the player, else false.
	 */
	public ZGCharacter(String name, int[] stats, boolean isDefending, boolean isFriendly){
		super(name);
		this.isFriendly = isFriendly;
		this.isDefending = isDefending;


		//If the stats are not the right size, then use fault stats.
		if (stats.length != 7){
			System.out.println("Illegal stats.");
			stats = ZGCharacterUtil.DEFAULT_STATS;
		}

		//Set stats
		this.maxHP = stats[0];
		this.currentHP = stats[0];	//CurrentHP = MaxHP
		this.str = stats[1];
		this.dex = stats[2];
		this.con = stats[3];
		this.intl = stats[4];
		this.wis = stats[5];
		this.cha = stats[6];
	}


	// Dice Rolls

	/**
	 * @return Roll initiative: 1D20 + Dex Mod 
	 */
	public int rollInitiative(){
		return DiceUtil.d20() + getDexMod();
	}

	/**
	 * @return Roll to hit: 1D20 + modifier
	 */
	public int rollHit(){
		return DiceUtil.d20() + getStrMod();
	}

	/**
	 * @return Roll the attack: Weapon dmg + mod
	 */
	public int rollAttack(){
		return getEquippedWeapon().rollDmg() + getStrMod();
	}

	//Actions

	/**
	 * Takes the given dmg
	 * @param dmg The dmg value to reduce the current HP.
	 * @return The new remaining dmg.
	 */
	public int takeHit(int dmg){
		this.currentHP -= dmg;

		return this.currentHP;
	}

	/**
	 * @return The possible attack types for character
	 */
	public AttackTypes getAttackTypes(){

		return AttackTypes.ONE_TARGET;
	}


	/**
	 * Updates the status effects for the new turn
	 */
	public void newTurn(){
		isDefending = false;
	}

	/**
	 * Attack the target.
	 * @param target The target to attack
	 */
	public void attackTarget(ZGCharacter target){
		//roll to hit
		int hitRoll = rollHit();

		//If target is defending, the roll with disadvantage
		if (target.isDefending){
			int hitRollB = rollHit();

			if (hitRollB < hitRoll){
				hitRoll = hitRollB;
			}
		}

		if (hitRoll >= target.getAC()){
			//If hit was successful, then roll attack
			int attackVal = rollAttack();
			target.takeHit(attackVal);
			System.out.println("Hit for: " + attackVal + ".");
			System.out.println(target.getName() + "\'s current HP: " + target.getCurrentHP() + ".");		
		} else {
			//Attack missed
			System.out.println("Attack miss.");
		}
	}

	//Getters for stats and their modifiers

	/**
	 * @return the str
	 */
	public int getStr() {
		return str;
	}

	/**
	 * @return the dex
	 */
	public int getDex() {
		return dex;
	}

	/**
	 * @return the con
	 */
	public int getCon() {
		return con;
	}

	/**
	 * @return the intl
	 */
	public int getIntl() {
		return intl;
	}

	/**
	 * @return the wis
	 */
	public int getWis() {
		return wis;
	}

	/**
	 * @return the cha
	 */
	public int getCha() {
		return cha;
	}

	/**
	 * @return the strength modifier
	 */
	public int getStrMod() {
		return ZGCharacterUtil.calcMod(getStr());
	}

	/**
	 * @return the dexterity modifier
	 */
	public int getDexMod() {
		return ZGCharacterUtil.calcMod(getDex());
	}

	/**
	 * @return the constitution modifier
	 */
	public int getConMod() {
		return ZGCharacterUtil.calcMod(getCon());
	}

	/**
	 * @return the intellect modifier
	 */
	public int getIntlMod() {
		return ZGCharacterUtil.calcMod(getIntl());
	}

	/**
	 * @return the wisdom modifier
	 */
	public int getWisMod() {
		return ZGCharacterUtil.calcMod(getWis());
	}

	/**
	 * @return the charisma modifier
	 */
	public int getChaMod() {
		return ZGCharacterUtil.calcMod(getCha());
	}

	/**
	 * @return Armor class: 10 + Dex Mod
	 */
	public int getAC(){
		return 10 + getDexMod();
	}


	// Other getters and setters

	/**
	 * @return the isFriendly
	 */
	public boolean getIsFriendly(){
		return isFriendly;
	}

	/**
	 * @return the isDefending
	 */
	public boolean getIsDefending(){
		return isDefending;
	}

	/**
	 * @return the maxHP
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * @return the currentHP
	 */
	public int getCurrentHP() {
		return currentHP;
	}

	/**
	 * @return the equippedWeapon
	 */
	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	/**
	 * Sets the equipped weapon
	 * @param equippedWeapon The weapon to set
	 */
	public void setEquippedWeapon(Weapon equippedWeapon){
		this.equippedWeapon = equippedWeapon;
	}

	/**
	 * Sets the isFriendly flag
	 * @param isFriendly The flag value to set
	 */
	public void setIsFriendly(boolean isFriendly){
		this.isFriendly = isFriendly;
	}

	/**
	 * Sets the isDefending flag
	 * @param isDefending The flag value to set
	 */
	public void setIsDefending(boolean isDefending){
		this.isDefending = isDefending;
	}

}
