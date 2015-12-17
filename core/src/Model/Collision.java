package Model;

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
		
		
		
	}
	public int getDistToWall(int x, int y ,DStates dir )
	{	
		
		int tileX;
		int tileY;
		tileX = x/tileWidth ;
		tileY = y/tileWidth ;
		//System.out.println(tileX+" "+tileY);
		
		
		switch(dir)
		{
			case UP:
				return upColMap[tileX][tileY]*tileWidth +(tileWidth- (y%tileWidth));
				
				
			case DOWN:
				
				return downColMap[tileX][tileY]*tileWidth +(y%tileWidth);
				
			
			case LEFT:
				
				return leftColMap[tileX][tileY]*tileWidth +(x%tileWidth);
			case RIGHT:
				
				return rightColMap[tileX][tileY]*tileWidth + tileWidth - (x%tileWidth); 
				
			default:
				return 0;
		}
		
		
	}
}
