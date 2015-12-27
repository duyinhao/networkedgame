package Model;

public class Bullet implements Collidable {
	
	public int width;
	public int height;
	public Vector2 position;
	public Vector2 velocity;
	public Bullet( int x, int y, int height, int width, Vector2 velocityVec )
	{
		this.width = width;
		this.height = height;
		this.position = new Vector2(x, y) ;
		this.velocity = velocityVec;
		
	}
	public void collide(Object object)
	{
		if (object instanceof Hero)
		{	
			Hero hero = (Hero)object;
			hero.health = hero.health - 10;
		}
	}
	public void update(int deltaTime)
	{
		this.position.add(this.velocity.scl(deltaTime));
	}

}
