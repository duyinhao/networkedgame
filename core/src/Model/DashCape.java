package Model;

public class DashCape extends BasicCape{
	int count;
	public DashCape()
	{
		count =0;
	}
	public void basicAtt1(float deltaTime,int mouseX, int mouseY,  boolean justPressed,LocalWorld wrl)
	{
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void jump(float deltaTime, boolean justPressed,LocalWorld wrl)
	{
		if(wrl.hero.status != HStates.JUMP)
				count=0;
		if(wrl.hero.status == HStates.JUMP&&justPressed&&count < 1)
		{
			count++;
			if(wrl.hero.direction == DStates.RIGHT)
				wrl.hero.velocity.x = wrl.hero.velocity.x + 200;
			else
				wrl.hero.velocity.x = wrl.hero.velocity.x - 200;
			
			wrl.hero.status = HStates.FLY;
		}
		
	}
	public void movement(float deltaTime,DStates direction, boolean justPressed,LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void equip(Hero hero)
	{
		hero.cape = this;
	}

}
