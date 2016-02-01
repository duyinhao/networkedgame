package Model;



public class CloudShooter extends BasicShooter {
	int length;
	public CloudShooter()
	{
		length = 100;
	}
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, boolean justPressed, LocalWorld wrl)
	{
	
		
	}
	@Override
	public void basicAtt2(float deltaTime,int mouseX, int mouseY, boolean justPressed,LocalWorld wrl)
	{

		if(justPressed)
		{
		Hero hero = wrl.hero;
		Vector2 bulletVelocity = new Vector2(mouseX - hero.position.x, mouseY - hero.position.y);
		
		
		bulletVelocity = bulletVelocity.scl(100/bulletVelocity.magnitude());
		
		
		
		Vector2 relativeStartingPoint = bulletVelocity;
		Vector2 charMidpoint = new Vector2(hero.position.x + hero.width/2,hero.position.y+hero.height/2);
		
		//this code is shit because of void add, rename
		charMidpoint.add(relativeStartingPoint.scl(0.7f));
		
		bulletVelocity = new Vector2(0,0);
		wrl.entLis.add(new CloudBullet( (int)(charMidpoint.x )-27,(int)(charMidpoint.y)-27,54,54,bulletVelocity));
		}
	}
	public void jump(float deltaTime,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void movement(float deltaTime,DStates direction,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage,boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void equip(Hero hero)
	{
		hero.weapon = this;
	}
	

}
