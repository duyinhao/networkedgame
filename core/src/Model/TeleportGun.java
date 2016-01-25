package Model;

public class TeleportGun extends BasicShooter {
	
	@Override
	public void basicAtt1(float deltaTime,int mouseX, int mouseY, boolean justPressed, LocalWorld wrl)
	{
		
		Hero hero = wrl.hero;
		Vector2 bulletVelocity = new Vector2(mouseX - hero.position.x, mouseY - hero.position.y);
		bulletVelocity = bulletVelocity.scl(1/bulletVelocity.magnitude());
		
		wrl.entityArr.add(new CloudBullet( (int)(hero.position.x ),(int)(hero.position.y),54,54,bulletVelocity));
	}
}
