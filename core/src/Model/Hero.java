package Model;






public class Hero extends Entity {

public int id;
public DStates direction;
public HStates status;
public static float SPEEDPERSECOND = 5000;
public Vector2 velocity;
	public Hero()
	{
		super(new Vector2(0,0), 0,0);
		
		
		this.id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
	}
	public Hero(int x , int y)
	{
		super(new Vector2(x,y), 0,0);
		id = -1;
		this.direction = DStates.RIGHT;
		this.status = HStates.STAND;
		this.velocity = new Vector2(0,0);
	}
	
	public void setX(float x)
	{
		super.position.x=x;
		
	}
	
	public void setY(float y)
	{
		super.position.y=y;
		
	}
	
	
	
}
