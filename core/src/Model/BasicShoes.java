package Model;

public class BasicShoes implements Equipable{
	
	
	public BasicShoes()
	{
		
	}
	
	
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, LocalWorld wrl)
	{
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY, LocalWorld wrl)
	{
		
	}
	public void jump(float deltaTime, LocalWorld wrl)
	{
		if (wrl.hero.status!=HStates.JUMP)
		{
			wrl.hero.velocity.y = 100;
			wrl.hero.status = HStates.JUMP;
		}
		
	}
	public void movement(float deltaTime,DStates direction, LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage, LocalWorld wrl)
	{
		
	}

}
