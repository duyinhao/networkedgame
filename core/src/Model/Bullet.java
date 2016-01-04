package Model;

public class Bullet extends Entity<BulletState>  {
	

	public Vector2 velocity;
	public Bullet()
	{
		super(  new Vector2(0,0), 10, 10);
		this.velocity = new Vector2(0,0);
	}
	public Bullet( int x, int y, int height, int width, Vector2 velocityVec )
	{
		super(  new Vector2(x,y), width, height);
		
		
;
		this.velocity = velocityVec;
		
		
		
		
	}
	@Override
	public void collide(Collidable object)
	{
		if (object instanceof Hero)
		{	
			Hero hero = (Hero)object;
			//hero.health = hero.health - 10;
			hero.velocity.y = 100;
			//System.out.println("hit");
		}
	}
	@Override
	public void update(float deltaTime)
	{
		this.position.add(this.velocity.scl(deltaTime));
	}
	@Override
	public BulletState getState()
	{
		return BulletState.NONE;
	}
}
