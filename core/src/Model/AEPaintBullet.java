package Model;

public class AEPaintBullet extends Bullet {
	

		public Vector2 velocity;
		public AEPaintBullet()
		{
			super( );
			this.velocity = new Vector2(0,0);
		}
		public AEPaintBullet( int x, int y, int height, int width, Vector2 velocityVec )
		{
			super( x,y,height,width, velocityVec);
			
			
	;
			this.velocity = velocityVec;
			
			
			
			
		}
		@Override
		public void collide(Collidable object)
		{
			//if (object instanceof Hero)
			//{	
				//Hero hero = (Hero)object;
				//hero.health = hero.health - 10;
				//hero.velocity.y = 100;
				
				//hero.health = hero.health -1;
				//System.out.println("hit");
			//}
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
