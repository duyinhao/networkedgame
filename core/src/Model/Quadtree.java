package Model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Quadtree {
	private int MAXITEMS = 3;
	private int MAXLEVEL = 100;
	Quadtree[] children; 
	Vector2 midPoint;
	Vector2 blCorner;
	Vector2 trCorner;
	private ArrayList<Entity> objects;
	int level;
	public Quadtree(Vector2 blCorner,  Vector2 trCorner, int level )
	{
		children = new Quadtree[4]; 
		midPoint = new Vector2( blCorner.x +(trCorner.x - blCorner.x)/2 , blCorner.y  +(trCorner.y - blCorner.y)/2 );
		this.trCorner = trCorner;
		this.blCorner = blCorner;
		objects = new ArrayList<Entity>() ;
		this.level = level;
		//System.out.println(midPoint.x+" "+midPoint.y);
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
		children[1] = new Quadtree(new Vector2(midPoint.x, blCorner.y), new Vector2(trCorner.x,midPoint.y) , level+1);
		children[2] = new Quadtree(blCorner,midPoint , level+1);
		children[3] = new Quadtree(new Vector2(blCorner.x, midPoint.y), new Vector2(midPoint.x, trCorner.y ) , level+1);
		
	}
	public int getIndex(Entity ent)
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
		//System.out.println(id);
		return id;
		
		
		
	}
	public void insert(Entity ent)
	{
		if(children[0] != null)
		{
			int index = getIndex(ent);
			
			if(index != -1)
			{
				children[index].insert(ent);
				return;
			}
		}
		
		objects.add(ent);
		
		if(objects.size() > MAXITEMS && level < MAXLEVEL){
			if(children[0]==null)
			{
				split();
				//System.out.println("SPLIT");
			}
			for(int i = 0 ; i < objects.size(); i++)
			{
				int index = getIndex(objects.get(i));
				if(index != -1)
				{
					children[index].insert(objects.remove(i));
					i--;
				}
			}
			
			
			
		}
	}
	public ArrayList<Entity> retrieve(ArrayList<Entity> returnObjects, Entity collideEnt)
	{
		int index = getIndex(collideEnt);
		if(index != -1 && children[0]!=null)
		{
			children[index].retrieve(returnObjects, collideEnt);
		}
		
		returnObjects.addAll(objects);
		
		return returnObjects;
	}
	public static boolean isCollide(Entity ent1, Entity ent2)
	{
		if(ent1.position.x > ent2.position.x + ent2.width )
		{
			return false;
		}
		if(ent2.position.x > ent1.position.x + ent1.width )
		{
			return false;
		}
		if(ent1.position.y > ent2.position.y + ent2.height )
		{
			return false;
		}
		if(ent2.position.y > ent1.position.y + ent1.height )
		{
			return false;
		}
		return true;
	}
	

}
