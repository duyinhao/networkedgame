package Model;







import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LocalWorld {
	
	//Hero hero ;
	boolean online;
	
	public User user;
	public Hero hero;
	public HeroArr heroArr;

	public int[][] collisionMapArr;

	public Collision colSystem;
	
	TiledMapTileLayer layer;
	public LocalWorld(TiledMapTileLayer layer  )
	{
		this.layer = layer;
		
		hero = new Hero(1,1);
		user = new User("test");
		heroArr = new HeroArr();
		collisionMapArr= new int[layer.getWidth()][layer.getHeight()];
		
		
		
		
		
		TiledMapTile tmpTile;
		for(int x = 0; x < layer.getWidth(); x++)
		{
			for(int y = 0; y < layer.getHeight(); y++)
			{
				//System.out.println(y+" "+x);
				tmpTile = layer.getCell(x, y).getTile();
				//System.out.println(tmpTile.getId());
				
				if(!tmpTile.getProperties().containsKey("Collision")  )
				{
					collisionMapArr[x][y] = 0;
				}
				else
				{
					collisionMapArr[x][y] = Integer.parseInt(tmpTile.getProperties().get("Collision",String.class)) ;
					//System.out.println("has property");
				}
			}
			
		}
		
		colSystem = new Collision(collisionMapArr,(int)layer.getTileHeight());
		  
		 
		
		
		
		
	}
	public void upate(float deltaTime)
	{
		Hero hero;
		for(int i = 0; i < heroArr.size; i++)
		{	
			
			hero =heroArr.arr[i];
			
			if(heroArr.arr[i]!=null)
			{	
				
				Vector2 movementVec = new Vector2(0,0);
				Vector2 sclVelocity = hero.velocity.scl(deltaTime);
				
				
				
				
				
				Vector2[] v = {new Vector2(hero.position.x,hero.position.y), new Vector2(hero.position.x, hero.position.y+hero.height)};
				hero.position.x = hero.position.x + actualXMovementWithCollision(v,sclVelocity);
				hero.position.y = hero.position.y + actualYMovementWithCollision(v, sclVelocity);
				
				
				
				
				
				
			}
		}
		
	}
	//entity calculation
	
	
	
	//point(s) calculation
	private float actualXMovementWithCollision(Vector2[] positionPoints, Vector2 sclVelocityVec)
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
	private float actualYMovementWithCollision(Vector2[] positionPoints, Vector2 sclVelocityVec)
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
	private float actualXMovementWithCollision( Vector2 positionVec,Vector2 sclVelocityVec)
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
	private float actualYMovementWithCollision(Vector2 positionVec, Vector2 sclVelocity)
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
