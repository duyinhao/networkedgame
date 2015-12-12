package Model;







import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
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
	
	public void moveHero(Vector2 velocity, float deltaTime, int heroID)
	{
		//heroArr.arr[heroID]
				
		
		
	}
	
	
	
	
	
	
	
}
