package Model;


public  class Entity<stateType> implements Collidable{
	public Vector2 position;
	public int width;
	public int height;
	boolean destroyed;
	public Entity ( Vector2 position, int width, int height)
	{
		this.position = position;
		this.width = width;
		this.height = height;
		this.destroyed = false;
	}
	public void update(float deltaTime)
	{
		
	}
	public void collide(Collidable collide)
	{
		
	}
	public stateType  getState()
	{
		return null;
	}
	
	
	
	
}

