package Model;

public class DoubleJumpShoes extends BasicShoes{
	
	int jumpCount;
	public DoubleJumpShoes()
	{
		jumpCount = 0;
	}
	
	
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, LocalWorld wrl)
	{
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY, LocalWorld wrl)
	{
		
	}
	@Override
	public void jump(float deltaTime, LocalWorld wrl)
	{
		if (wrl.hero.status!=HStates.JUMP)
		{
			this.jumpCount= 0;
			wrl.hero.velocity.y = 100;
			wrl.hero.status = HStates.JUMP;
		}
		else if(jumpCount < 1)
		{
			wrl.hero.velocity.y = 100;
			wrl.hero.status = HStates.JUMP;
			this.jumpCount++;
		}
		
	}
	public void movement(float deltaTime,DStates direction, LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage, LocalWorld wrl)
	{
		
	}

}
