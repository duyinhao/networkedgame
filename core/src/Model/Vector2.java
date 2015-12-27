package Model;



public class Vector2 {
	public float x;
	public float y;
	public Vector2()
	{
		this.x = 0;
		this.y = 0;
	}
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		
	}
	public void add( Vector2 vec)
	{
		this.x = this.x + vec.x;
		this.y = this.y + vec.y;
		
	}
	public float magnitude( )
	{
		return (float)Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	public Vector2 scl(float scalar)
	{
		Vector2 vect = new Vector2(this.x*scalar, this.y*scalar);
		
		return vect;
	}
}
