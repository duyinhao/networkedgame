package com.mygdx.game;

import java.util.ArrayList;

import Model.HStates;

public class HeroSkeleton implements Skeleton {
	public Bone[] bones = new Bone[10];
	HStates state;
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
	
	public HeroSkeleton(int headXPos, int headYPos ,int thighLength, int shinLength, int bodyLength, int bicepLength, int forearmLength, int headLength )
	{
		bones[0] = new  Bone(headXPos , headYPos  , headLength, 270);
		bones[1]  = new  Bone(bones[0], bodyLength, 270);
		
		bones[2]  = new  Bone(bones[0], bicepLength, 225);
		bones[3]  = new  Bone(bones[2], forearmLength, 270);
		
		bones[4]  = new  Bone(bones[0], bicepLength, 315);
		bones[5] = new Bone(bones[4], forearmLength, 270);
		
		bones[6] = new Bone(bones[1], thighLength, 315);
		bones[7] = new Bone(bones[6], shinLength, 270);
		
		bones[8] = new Bone(bones[1], thighLength, 225);
		bones[9] = new Bone(bones[8], shinLength, 270);
		counter = 0;
	}
	
	
	
	
	public void update(float deltaTime)
	{
		
		counter+=deltaTime;
		System.out.println(counter);
		if(counter < 0.2)
		{
		bones[2].setAngle(10);
		bones[3].setAngle(45);
		
		bones[4].setAngle(190);
		bones[5].setAngle(235);
		
		bones[6].setAngle(0);
		bones[7].setAngle(270);
		
		bones[8].setAngle(235);
		bones[9].setAngle(145);
		}
		else
		{
		
		bones[2].setAngle(270);
		bones[3].setAngle(300);
		
		bones[4].setAngle(265);
		bones[5].setAngle(290);
		
		bones[6].setAngle(260);
		bones[7].setAngle(255);
		
		bones[8].setAngle(250);
		bones[9].setAngle(245);
		}
		if(counter> 0.4)
		{
			counter=0f;
		}
		bones[0].position.x +=0.4f;
		for(int i = 0 ; i < bones.length; i++)
		{
			bones[i].updateTailPoint();
		}

		
		
	}
	public void setState(HStates state)
	{
		this.state = state;
	}
	
	
	
}
