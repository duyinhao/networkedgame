package Model;

import java.util.ArrayList;

public class Quadtree {
	private int MAXITEMS = 10;
	private int MAXLEVEL = 5;
	Quadtree[] children; 
	Vector2 midPoint;
	Vector2 blCorner;
	Vector2 trCorner;
	private ArrayList<Entity> objects;
	int level;
	public Quadtree(Vector2 blCorner,  Vector2 trCorner, int level )
	{
		Quadtree[] children = new Quadtree[4]; 
		midPoint = new Vector2( blCorner.x +(trCorner.x - blCorner.x)/2 , blCorner.y  +(trCorner.y - blCorner.y)/2 );
		this.trCorner = trCorner;
		this.blCorner = blCorner;
		objects = new ArrayList<Entity>() ;
		this.level = level;
	}
	public void clear()
	{
		objects.clear();
		for(int i = 0; i < children.length; i++)
		{
			if(children[i]!=null)
			{
				children[i].clear();
				children[i] = null;
			}
		}
	}
	public void split()
	{
		children[0] = new Quadtree(midPoint, trCorner  , level+1);
		children[1] = new Quadtree(new Vector2(), new Vector2() , level+1);sdfsdf
		children[2] = new Quadtree(new Vector2(), new Vector2() , level+1);
		children[3] = new Quadtree(new Vector2(), new Vector2() , level+1);
		
	}
	public int getID(Entity ent)
	{
		int id = -1;
		if(ent.position.y > this.midPoint.y)
		{
			if( ent.position.x+ent.width < this.midPoint.x)
			{
				id = 3;
			}
			else if(ent.position.x > this.midPoint.x)
			{
				id = 0;
			}
			
		}
		else if(ent.position.y + ent.height < this.midPoint.y)
		{
			if( ent.position.x+ent.width < this.midPoint.x)
			{
				id = 2;
			}
			else if(ent.position.x > this.midPoint.x)
			{
				id = 1;
			}
		}
		return id;
		
		
		
	}
	
	

}
