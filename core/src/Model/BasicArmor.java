package Model;

public class BasicArmor implements Equipable{

	public BasicArmor()
	{
		
	}
	public void basicAtt1(float deltaTime,int mouseX, int mouseY,  boolean justPressed,LocalWorld wrl)
	{
		
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY,  boolean justPressed,LocalWorld wrl)
	{
		
	}
	public void jump(float deltaTime, boolean justPressed,LocalWorld wrl)
	{
		
	}
	public void movement(float deltaTime,DStates direction,  boolean justPressed, LocalWorld wrl)
	{
		
	}
	public void damage(float deltaTime, int damage, boolean justPressed ,LocalWorld wrl)
	{
		
	}
	public void equip(Hero hero)
	{
		hero.armor = this;
	}
	
}
