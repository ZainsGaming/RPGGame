package zainsgaming.rpg.characters;

public class Race {
	
	private Race_Type raceType;
	private int speed;
	
	public Race(Race_Type raceType){
		this.raceType = raceType;
		this.speed = CharacterUtil.raceSpeed(raceType);
	}
}
