package Model;

public  class EquipableItem extends Entity<BulletState>{
	
	Equipable equipment;
	
	public EquipableItem( Vector2 position, int width, int height, Equipable equipment)
	{
		super(  position, width, height);
		this.equipment= equipment;
	}
	
	
	
	@Override
	public void collide(Collidable collide)
	{
		if(collide.getClass() == Hero.class)
		{
			Hero hero =  (Hero)collide;
			
			this.equipment.equip(hero);
			
			this.destroyed = true;
			
		}
	}
	public BulletState  getState()
	{
		return BulletState.NONE;
	}

}
