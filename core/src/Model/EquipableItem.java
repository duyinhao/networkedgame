package Model;

public abstract class EquipableItem<stateType> extends Entity<stateType>{
	
	public EquipableItem( Vector2 position, int width, int height)
	{
		super(  position, width, height);
	}
	
	public  abstract  void equip(Hero hero);

}
