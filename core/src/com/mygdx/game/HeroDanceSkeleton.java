package com.mygdx.game;

import java.util.ArrayList;

import Model.Bone;
import Model.HStates;
import Model.Skeleton;

public class HeroDanceSkeleton implements Skeleton {
	public Bone[] bones = new Bone[10];
	HStates state;
	boolean clockwise;
	//index to bone
	//0 -- head
	//1 -- body
	//2 -- bice1
	//3 -- forearm1
	//4 -- bicep2
	//5 -- forearm2
	//6 --thight1
	//7 -- shin1
	//8 -- thigh1
	// 9 -- shin1
	float counter;
	
	

	
	public HeroDanceSkeleton(int headXPos, int headYPos ,int thighLength, int shinLength, int bodyLength, int bicepLength, int forearmLength, int headLength )
	{
		bones[0] = new  Bone(headXPos , headYPos  , headLength, 270);
		bones[1]  = new  Bone(bones[0], bodyLength, 270);
		
		bones[2]  = new  Bone(bones[0], bicepLength, 0);
		bones[3]  = new  Bone(bones[2], forearmLength, 90);
		
		bones[4]  = new  Bone(bones[0], bicepLength, 180);
		bones[5] = new Bone(bones[4], forearmLength, 90);
		
		bones[6] = new Bone(bones[1], thighLength, 315);
		bones[7] = new Bone(bones[6], shinLength, 270);
		
		bones[8] = new Bone(bones[1], thighLength, 225);
		bones[9] = new Bone(bones[8], shinLength, 270);
		counter = 0;
		clockwise = false;
	}
	
	public Bone[] getBones()
	{
		return bones;
	}
	
	
	public void update(float deltaTime)
	{
		if(clockwise)
		{
		bones[3].rotateAntiClockWise(30*deltaTime);
		bones[5].rotateAntiClockWise(30*deltaTime);
		}
		else
		{
			bones[3].rotateAntiClockWise(-30*deltaTime);
			bones[5].rotateAntiClockWise(-30*deltaTime);
			
		}
		
		if (bones[3].angle <30)
		{
			clockwise = true;
		}
		if(bones[3].angle > 150)
		{
			clockwise = false;
		}

		
		
	}
	public void setState(HStates state)
	{
		this.state = state;
	}
	
	
	
}
