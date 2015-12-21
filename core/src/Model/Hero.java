package Model;






public class Hero extends Entity {

public int id;
public DStates direction;
public HStates status;
public static float SPEEDPERSECOND = 5000;
public Vector2 velocity;
public BasicShooter weapon;
public BasicShoes shoes;
public BasicArmor armor;
public BasicCape cape;


	public Hero()
	{
		super(new Vector2(0,0), 0,0);
		
		
		this.id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
		this.weapon = new BasicShooter();
		this.shoes = new DoubleJumpShoes();
		this.armor = new BasicArmor();
		this.cape = new BasicCape();
	}
	public Hero(int x , int y)
	{
		super(new Vector2(x,y), 0,0);
		id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
		this.weapon = new BasicShooter();
		this.shoes = new DoubleJumpShoes( );
		this.armor = new BasicArmor();
		this.cape = new BasicCape();
	}
	
	public Hero(int x , int y, int width, int height)
	{
		super(new Vector2(x,y), width,height);
		id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
		
		this.weapon = new BasicShooter();
		this.shoes = new DoubleJumpShoes();
		this.armor = new BasicArmor();
		this.cape = new BasicCape();
	}
	
	public void setX(float x)
	{
		super.position.x=x;
		
	}
	
	public void setY(float y)
	{
		super.position.y=y;
		
	}
	
	public void basicAtt1(float deltaTime,int mouseX, int mouseY,LocalWorld wrl)
	{
		weapon.basicAtt1(deltaTime, mouseX, mouseY,wrl);
		cape.basicAtt1(deltaTime, mouseX, mouseY,wrl);
		shoes.basicAtt1(deltaTime, mouseX, mouseY,wrl);
		armor.basicAtt1(deltaTime, mouseX, mouseY,wrl);
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY,LocalWorld wrl)
	{
		weapon.basicAtt2(deltaTime, mouseX, mouseY,wrl);
		cape.basicAtt2(deltaTime, mouseX, mouseY,wrl);
		shoes.basicAtt2(deltaTime, mouseX, mouseY,wrl);
		armor.basicAtt2(deltaTime, mouseX, mouseY,wrl);
	}
	public void jump(float deltaTime,LocalWorld wrl)
	{
		
		weapon.jump(deltaTime,wrl);
		cape.jump(deltaTime,wrl);
		shoes.jump(deltaTime,wrl);
		armor.jump(deltaTime,wrl);
	}
	public void movement(float deltaTime,DStates direction,LocalWorld wrl)
	{
		weapon.movement(deltaTime,direction,wrl);
		cape.movement(deltaTime,direction,wrl);
		shoes.movement(deltaTime,direction,wrl);
		armor.movement(deltaTime,direction,wrl);
	}
	public void damage(float deltaTime,int damage,LocalWorld wrl)
	{
		weapon.damage(deltaTime, damage,wrl);
		cape.damage(deltaTime, damage,wrl);
		shoes.damage(deltaTime, damage,wrl);
		armor.damage(deltaTime, damage,wrl);
	}
	
	
	
}
