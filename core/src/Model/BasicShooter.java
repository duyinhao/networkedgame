package Model;

public class BasicShooter implements Equipable {
	
	public BasicShooter()
	{

	}
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, boolean justPressed, LocalWorld wrl)
	{
		//fix this, make so that it starts at the weapon point
		Hero hero = wrl.hero;
		Vector2 bulletVelocity = new Vector2(mouseX - hero.position.x, mouseY - hero.position.y);
		bulletVelocity = bulletVelocity.scl(100/bulletVelocity.magnitude());
		
		wrl.entityArr.add(new Bullet( (int)(hero.position.x ),(int)(hero.position.y),54,54,bulletVelocity));
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY, boolean justPressed,LocalWorld wrl)
	{
		
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
