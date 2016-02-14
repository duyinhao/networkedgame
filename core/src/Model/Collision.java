package Model;

import java.util.ArrayList;

public class Collision {
	public int[][] collisionMapArr;
	public int[][] rightColMap;
	public int[][] leftColMap;
	public int[][] upColMap;
	public int[][] downColMap;
	int tileWidth;
	
	public Collision(int[][] map, int tileWidth)
	{
		
		collisionMapArr = map;
		
		int w =collisionMapArr.length;
		int h = collisionMapArr[0].length;
		
		
		
		  rightColMap = new int[w][h];
		  leftColMap = new int[w][h];
		  upColMap = new int[w][h];
		 downColMap = new int[w][h];
		this.tileWidth = tileWidth;
		for(int y = 0;  y < collisionMapArr[0].length; y++ )
		{
			//left
			for(int x = 0; x < collisionMapArr.length ; x++)
			{
				
				if(collisionMapArr[x][y] == 1 )
				{
					leftColMap[x][y] = -1;
					
				}
				else if(x==0    )
				{
					leftColMap[x][y] = 0;
				}
				else
				{
					leftColMap[x][y] = leftColMap[x-1][y] + 1;
					
				}
				
				
				
				
				
			}
			//right
			for(int x = collisionMapArr.length -1; x >= 0  ; x--)
			{
				
				if(collisionMapArr[x][y] == 1 )
				{
					rightColMap[x][y] = -1;
					
				}
				else if(x == collisionMapArr.length-1)
				{
					rightColMap[x][y] = 0;
				}
				else
				{
					rightColMap[x][y] = rightColMap[x+1][y]+1;
				}
				
				
			}
			
		}
		for(int x = 0;  x < collisionMapArr.length; x++ )
		{
			for(int y = 0 ; y < collisionMapArr[0].length; y++)
			{
				if(collisionMapArr[x][y] == 1 )
				{
					downColMap[x][y] = -1;
					
				}
				else if(y==0)
				{
					downColMap[x][y] = 0;
				}
				else
				{
					downColMap[x][y] = downColMap[x][y-1] + 1;
				}
			}
			for(int y = collisionMapArr[0].length -1; y >=0; y--)
			{
				if(collisionMapArr[x][y] == 1 )
				{
					upColMap[x][y] = -1;
					
				}
				else if(y == collisionMapArr[0].length-1)
				{
					upColMap[x][y] = 0;
				}
				else
				{
					upColMap[x][y] = upColMap[x][y+1] + 1;
				}
				
			}
			
			
		
		}
		
		//replace all -1 with 0
		for(int x = 0;  x < collisionMapArr.length; x++ )
		{
			for(int y = collisionMapArr[0].length -1; y >=0; y--)
			{
				if(collisionMapArr[x][y]==1 )
				{
					upColMap[x][y] = 0;
					leftColMap[x][y] = 0;
					rightColMap[x][y] = 0;
					downColMap[x][y] = 0;
				}
			}
			
		}
		
		
		
	}
	public int getDistToWall(int x, int y ,DStates dir )
	{	
		
		int tileX;
		int tileY;
		tileX = x/tileWidth ;
		tileY = y/tileWidth ;
		//System.out.println(tileX+" "+tileY);
		
		//this basically means that we consider areas outside of the the map to be a wall technically
		if(tileX<0|| tileX>=upColMap.length||tileY<0 || tileY>=upColMap[0].length)
		{
			return 0;
			
		}
		//if the point is already in a wall, then of course the distance to the wall is 0
		
		if(collisionMapArr[tileX][tileY] == 1)
		{
			return 0;
		}
		switch(dir)
		{
			case UP:
		
					return upColMap[tileX][tileY]*tileWidth +(tileWidth- (y%tileWidth));
				
				
			case DOWN:
				
				return downColMap[tileX][tileY]*tileWidth +(y%tileWidth);
				
			
			case LEFT:
				
				return leftColMap[tileX][tileY]*tileWidth +(x%tileWidth);
			case RIGHT:
				//System.out.println(rightColMap[tileX][tileY]*tileWidth + tileWidth - (x%tileWidth)); 
				return rightColMap[tileX][tileY]*tileWidth + tileWidth - (x%tileWidth); 
				
			default:
				return 0;
		}
		
		
	}
	
	public static boolean entCollide(Entity ent1, Entity ent2 )
	{
		if(ent1.position.x > ent2.position.x + ent2.width|| ent2.position.x > ent1.position.x + ent1.width||ent1.position.y > ent2.position.y + ent2.height||ent2.position.y > ent1.position.y + ent1.height)
		{
			return false;
		}
		else
		{
			return true;
		}
			
	}
}
