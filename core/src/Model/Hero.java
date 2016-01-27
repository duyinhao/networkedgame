package Model;

import com.mygdx.game.HStateComp;
import com.mygdx.game.HeroStateComposition;






public class Hero extends Entity <HStateComp>{

public int id;
public DStates direction;
public HStates status;
public static float SPEEDPERSECOND = 70;
public Vector2 velocity;
public BasicShooter weapon;
public BasicShoes shoes;
public BasicArmor armor;
public BasicCape cape;
public int health;

	public Hero()
	{
		super(new Vector2(0,0), 0,0);
		
		this.health = 100;
		this.id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
		this.weapon = new BasicShooter();
		this.shoes = new DoubleJumpShoes();
		this.armor = new BasicArmor();
		this.cape = new BasicCape();
		this.health = 100;
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
		this.health = 100;
	}
	
	public Hero(int x , int y, int width, int height)
	{
		super(new Vector2(x,y), width,height);
		id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
		
		this.weapon = new BasicShooter();
		//this.shoes = new BasicShoes();
		this.shoes = new DoubleJumpShoes();
		this.armor = new BasicArmor();
		this.cape = new BasicCape();
		//this.cape = new DashCape();
		this.health = 100;
	}
	
	public void setX(float x)
	{
		super.position.x=x;
		
	}
	
	public void setY(float y)
	{
		super.position.y=y;
		
	}
	
	public void basicAtt1(float deltaTime,int mouseX, int mouseY,boolean justPressed ,LocalWorld wrl)
	{
		weapon.basicAtt1(deltaTime, mouseX, mouseY,justPressed,wrl);
		cape.basicAtt1(deltaTime, mouseX, mouseY,justPressed,wrl);
		shoes.basicAtt1(deltaTime, mouseX, mouseY,justPressed,wrl);
		armor.basicAtt1(deltaTime, mouseX, mouseY,justPressed,wrl);
	}
	public void basicAtt2(float deltaTime,int mouseX, int mouseY,boolean justPressed,LocalWorld wrl)
	{
		weapon.basicAtt2(deltaTime, mouseX, mouseY,justPressed,wrl);
		cape.basicAtt2(deltaTime, mouseX, mouseY,justPressed,wrl);
		shoes.basicAtt2(deltaTime, mouseX, mouseY,justPressed,wrl);
		armor.basicAtt2(deltaTime, mouseX, mouseY,justPressed,wrl);
	}
	public void jump(float deltaTime,boolean justPressed,LocalWorld wrl)
	{
		if(wrl.hero.status != HStates.FLY)
		{
		weapon.jump(deltaTime,justPressed,wrl);
		cape.jump(deltaTime,justPressed,wrl);
		shoes.jump(deltaTime,justPressed,wrl);
		
		armor.jump(deltaTime,justPressed,wrl);
		if(wrl.hero.status==HStates.FLY)
		{
			System.out.println("sdfsdfsfsf");
		}
		}
	}
	public void movement(float deltaTime,DStates direction,boolean justPressed,LocalWorld wrl)
	{
		weapon.movement(deltaTime,direction,justPressed,wrl);
		cape.movement(deltaTime,direction,justPressed,wrl);
		shoes.movement(deltaTime,direction,justPressed,wrl);
		armor.movement(deltaTime,direction,justPressed,wrl);
	}
	public void damage(float deltaTime,int damage, boolean justPressed,LocalWorld wrl)
	{
		weapon.damage(deltaTime, damage,justPressed,wrl);
		cape.damage(deltaTime, damage,justPressed,wrl);
		shoes.damage(deltaTime, damage,justPressed,wrl);
		armor.damage(deltaTime, damage,justPressed,wrl);
	}
	@Override
	public void update(float deltaTime)
	{
		//this.position.add(this.velocity.scl(deltaTime));
		//this.position.add(this.velocity.scl(1f));
	}
	@Override
	public HStateComp getState()
	{
		return HeroStateComposition.get(this.direction, this.status);
	}
	public void copy(Hero hero)
	{
		this.armor = hero.armor;
		this.cape = hero.cape;
		this.direction = hero.direction;
		this.health = hero.health;
		this.height = hero.height;
		this.id = hero.id;
		this.position = hero.position;
		this.shoes = hero.shoes;
		this.status = hero.status;
		this.velocity = hero.velocity;
		this.weapon = hero.weapon;
		this.width = hero.width;
	}
	
	
	
}
