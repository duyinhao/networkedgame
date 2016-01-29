package Model;

import java.util.ArrayList;

public class Bone {
	public Vector2 position;
	public Vector2 tailPointPosition;
	public float angle;
	public float length;
	ArrayList<Bone> attachments;
	public Bone()
	{
		attachments = new ArrayList<Bone>();
		position = new Vector2();
		tailPointPosition = new Vector2();
		angle = 0;
		length = 0;
		attachments = new ArrayList<Bone>();
		this.updateTailPoint();
		
	}
	
	public Bone(float  x, float y  , float length, float angle)
	{
		attachments = new ArrayList<Bone>();
		this.position = new Vector2(x,y);
		tailPointPosition = new Vector2();
		this.length = length;
		this.angle = angle;
		this.updateTailPoint();
		
		
	}
	public Bone(Bone attachToBone, float length, float angle)
	{
		attachments = new ArrayList<Bone>();
		this.position = new Vector2();
		tailPointPosition = new Vector2();
		this.angle = angle;
		this.length = length;
		
		attachToBone.attachToTail(this);
		updateTailPoint();
		
		
	}
	public  void attachToTail(Bone bone)
	{
		this.updateTailPoint();
		attachments.add(bone);
		bone.setPositionVector(this.tailPointPosition);
	}
	public void setPositionVector(Vector2 vec)
	{
		position = vec;
	}
	
	public void rotateLigamentAntiClockWise(float  angle)
	{
		
		this.angle = this.angle+angle;
		updateTailPoint();
		
		for(int i = 0; i<attachments.size(); i++)
		{
			attachments.get(i).rotateAntiClockWise(angle);
		}
	}
	public void rotateAntiClockWise(float  angle)
	{
		
		this.angle = this.angle+angle;
		updateTailPoint();
		
		
	}
	public void setAngle(float angle)
	{
		this.angle = angle;
		updateTailPoint();
	}
	public void updateTailPoint()
	{
		this.tailPointPosition.x = this.position.x+ (float) Math.cos(Math.toRadians(angle))*length ;
		this.tailPointPosition.y =this.position.y + (float) Math.sin(Math.toRadians(angle))*length ;
	}

}
