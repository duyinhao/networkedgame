package Model;

public class BasicShooter implements Equipable {
	
	public BasicShooter()
	{

	}
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, boolean justPressed, LocalWorld wrl)
	{
		//fix this, make so that it starts at the weapon point
		
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
		wrl.entLis.add(new Bullet( (int)(charMidpoint.x )-27,(int)(charMidpoint.y)-27,54,54,bulletVelocity));
		}
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY, boolean justPressed,LocalWorld wrl)
	{
		Hero hero = wrl.hero;
		Vector2 bulletVelocity = new Vector2(mouseX - hero.position.x, mouseY - hero.position.y);
		bulletVelocity = bulletVelocity.scl(100/bulletVelocity.magnitude());
		
		bulletVelocity = new Vector2(0,0);
		
		wrl.entLis.add(new AEPaintBullet( (int)(mouseX -27 ),(int)(mouseY-27),54,54,bulletVelocity));
		
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
	
	

}
