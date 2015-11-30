package Model;


public class Hero {
float x;
float y;
public int id;
public static float SPEEDPERSECOND = 500;
	public Hero()
	{
	this.x = 0;
	this.y = 0;
	id = -1;
	}
	public Hero(int x , int y)
	{
		this.x = x;
		this.y = y;
		id = -1;
	}
	public void setX(float x)
	{
		this.x=x;
		
	}
	
	public void setY(float y)
	{
		this.y=y;
		
	}
	public float getY()
	{
		return y;
	}
	public float getX()
	{
		
		return x;
	}
	
	
}
