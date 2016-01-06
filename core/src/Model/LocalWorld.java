package Model;







import java.util.ArrayList;
import Controller.EntListener;

import com.esotericsoftware.kryonet.Client;

public class LocalWorld {
	
	//Hero hero ;
	boolean online;
	
	public User user;
	public Hero hero;
	public HeroArr heroArr;
	Quadtree colTree;
	public int[][] collisionMapArr;

	public Collision colSystem;
	//public ArrayList<Bullet> bulletArr;
	
	public ArrayList<Entity> entityArr;
	public EntListener entLis;
	
	//remove that client once you have the legit network layer
	public LocalWorld(int[][] collisionMapArr , int tileWidth , Client client)
	{
		
		
		hero = new Hero(1,1);
		user = new User("test");
		heroArr = new HeroArr();
		this.collisionMapArr = collisionMapArr;
		colSystem = new Collision(collisionMapArr,tileWidth);
		  
		entityArr = new ArrayList<Entity>();
		
		int w = collisionMapArr.length;
		int h = collisionMapArr[0].length;
		
		colTree = new Quadtree( new Vector2(0,0) , new Vector2(w*tileWidth,h*tileWidth)  , 0 );
		
		entLis = new EntListener( entityArr ,client);
		
		
	}
	public void upate(float deltaTime)
	{
		
		
		
		
		Hero hero;
		for(int i = 0; i < heroArr.size; i++)
		{	
			
			
			if(heroArr.arr[i]!=null)
			{	
				
				hero =heroArr.arr[i];
				Vector2 movementVec = new Vector2(0,0);
				Vector2 sclVelocity = hero.velocity.scl(deltaTime);
			//update the hero based on the rules of the game
				//if hero lands on  the floor once jumped, he his downward velcotity should be 0
				if( hero.status == HStates.JUMP && hero.velocity.y < 0 && actualYMovementWithCollision (hero, sclVelocity) == 0 )
				{
					hero.velocity.y = 0;
					//friction stops the x velocity
					hero.velocity.x = 0;
					hero.status = HStates.STAND;
					
						
					
				}
				//Gravity - make that stuff happen when you off the ground
				//ok so gravity should only accelrate character when its off the floor
				if( !(hero.velocity.y < 0 && actualYMovementWithCollision(hero, hero.velocity)==0))	
				{	
				
					hero.velocity.y = hero.velocity.y - 100.0f*deltaTime;
					
					
				}
				
			
	
				
				//make the hero move what it was suppose to move
				

				Vector2[] v = {new Vector2(hero.position.x,hero.position.y), new Vector2(hero.position.x, hero.position.y+hero.height),new Vector2(hero.position.x+hero.width,hero.position.y+hero.height),new Vector2(hero.position.x+hero.width,hero.position.y)};
				//hero.position.x = hero.position.x + actualXMovementWithCollision(v,sclVelocity);

				hero.position.x = hero.position.x + actualXMovementWithCollision(hero,sclVelocity);
				
				hero.position.y = hero.position.y + actualYMovementWithCollision(hero, sclVelocity);
				
			//	hero.velocity.x = actualXMovementWithCollision(hero,sclVelocity);
		//		hero.velocity.y = actualYMovementWithCollision(hero, sclVelocity);
				
				
				//if hero hits his head against a wall while jumping, it should stop change velocity to 0 
				if(hero.velocity.y > 0 && actualYMovementWithCollision(hero,sclVelocity)==0 )
				{
					hero.velocity.y = 0;
				}
				
//				if(hero.status!=HStates.RUN&&hero.velocity.y<0&&actualYMovementWithCollision(hero, hero.velocity)==0)
//				 {
//					hero.status = HStates.STAND;
//					hero.velocity.x = 0; 
//					 
//				 }
				
			}
		}
		colTree.clear();
		for(int j = 0 ; j < entityArr.size(); j++)
		{
			
			entityArr.get(j).update(deltaTime);
			
			//insert objects into quadtree
			colTree.insert(entityArr.get(j));
			
			
		}
		for(int k = 0; k < heroArr.size; k++)
		{
			if(heroArr.arr[k]!=null)
			{	
				ArrayList<Entity> returnObjects = new ArrayList<Entity>() ;
				returnObjects = colTree.retrieve(returnObjects, heroArr.arr[k]);
		
				for(int i = 0 ; i < returnObjects.size(); i ++)
				{
					for(int j = 0; j < returnObjects.size(); j++)
					{
						if(i!=j)
						{
						if(Quadtree.isCollide(returnObjects.get(j), returnObjects.get(i)))
							returnObjects.get(j).collide(returnObjects.get(i));
						}
					}
				}
			}
		}
		for(int i = 0; i < entityArr.size(); i++)
		{
			if(entityArr.get(i).destroyed)
			{
				entityArr.remove(i);
				i--;
			}
		}
		
		
		//System.out.println(returnObjects.size());
		
		
		
		
	}
	
	//might be a good idea to create another module for the below methods for the collision
//	//entity calculation
	// i assume tiles are squares not rectangles
	public float actualXMovementWithCollision(Entity entity, Vector2 sclVelocityVec)
	{
		int steps = entity.height/colSystem.tileWidth;
		int x;
		Vector2[] points = new Vector2[steps+2];
		
		if (sclVelocityVec.x>0)
		{
			x = (int)entity.position.x + entity.width;
		}
		else
		{
			x = (int)entity.position.x ;
		}
		
		points[0] = new Vector2(x,entity.position.y);
		points[steps+1] = new Vector2(x, entity.position.y+entity.height);
		for(int i = 1; i < steps+1; i++ )
		{
			points[i] = new Vector2(x,entity.position.y + i*colSystem.tileWidth );
			
		}		
		return actualXMovementWithCollision(points, sclVelocityVec);
	}
	public float actualYMovementWithCollision(Entity entity, Vector2 sclVelocityVec)
	{
		int steps = entity.width/colSystem.tileWidth;
		int y;
		Vector2[] points = new Vector2[steps+2];
		
		if (sclVelocityVec.y>0)
		{
			y = (int)entity.position.y + entity.height;
		}
		else
		{
			y = (int)entity.position.y ;
		}
		
		points[0] = new Vector2(entity.position.x,y);
		points[steps+1] = new Vector2(entity.position.x + entity.width,y );
		for(int i = 1; i < steps+1; i++ )
		{
			points[i] = new Vector2(entity.position.x + i*colSystem.tileWidth,y );
			
		}		
		return actualYMovementWithCollision(points, sclVelocityVec);
	}
	
	
	//point(s) calculation
	public float actualXMovementWithCollision(Vector2[] positionPoints, Vector2 sclVelocityVec)
	{
		float actX = actualXMovementWithCollision(positionPoints[0],sclVelocityVec);
		float temp;
		for(Vector2 point: positionPoints)
		{
			temp = actualXMovementWithCollision(point,sclVelocityVec);
			
			if(Math.abs(temp )< Math.abs(actX))
			{
				actX = temp;
			}
			
		}
		return actX;
	}
	public float actualYMovementWithCollision(Vector2[] positionPoints, Vector2 sclVelocityVec)
	{
		float actY = actualYMovementWithCollision(positionPoints[0],sclVelocityVec);
		float temp;
		for(Vector2 point: positionPoints)
		{
			temp = actualYMovementWithCollision(point,sclVelocityVec);
			
			if(Math.abs(temp )< Math.abs(actY))
			{
				actY = temp;
			}
			
		}
		return actY;
	}
	//point calcultation
	public float actualXMovementWithCollision( Vector2 positionVec,Vector2 sclVelocityVec)
	{
		//the distance to the wall for RIGHT and UP is subtracted by 1 so that if the character were to move by (distance - 1) they would still be on the same tile
		//this is because by convention when a point is on a border it is considered part of the top block or the right block
		
		if(sclVelocityVec.x >0)
		{
			return Math.min( sclVelocityVec.x , colSystem.getDistToWall((int)positionVec.x, (int)positionVec.y, DStates.RIGHT)-1 );
		}
		else if(sclVelocityVec.x <0)
		{
			return Math.max( sclVelocityVec.x , -1*colSystem.getDistToWall((int)positionVec.x, (int)positionVec.y, DStates.LEFT) );
		}
		else
		{
			return 0;
		}
	}
	public float actualYMovementWithCollision(Vector2 positionVec, Vector2 sclVelocity)
	{

		//the distance to the wall for RIGHT and UP is subtracted by 1 so that if the character were to move by (distance - 1) they would still be on the same tile
		//this is because by convention when a point is on a border it is considered part of the top block(if border is horizontal) or the right block(if border is vertical)
		
		//hero.position.x = hero.position.x + actualXMovementWithCollision(positionVec,sclVelocity);
		if(sclVelocity.y>0)
		{
			return Math.min(sclVelocity.y, colSystem.getDistToWall((int)positionVec.x, (int)positionVec.y, DStates.UP)-1) ;
		}
		else if(sclVelocity.y<0)
		{
			return   Math.max(sclVelocity.y, -1*colSystem.getDistToWall((int)positionVec.x, (int)positionVec.y, DStates.DOWN)) ;
		}
		else
		{
			return 0;
		}
	}
	
	
	
	
	
}
