package Model;

public class BasicShoes implements Equipable{
	
	
	public BasicShoes()
	{
		
	}
	
	
	public void basicAtt1(float deltaTime,int mouseX, int mouseY,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void jump(float deltaTime,boolean justPressed, LocalWorld wrl)
	{
		if (wrl.hero.status!=HStates.JUMP&&justPressed)
		{
			wrl.hero.velocity.y = 100;
			wrl.hero.status = HStates.JUMP;
		}
		
	}
	public void movement(float deltaTime,DStates direction,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void equip(Hero hero)
	{
		hero.shoes = this;
	}

}
