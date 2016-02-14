package Model;

import java.util.ArrayList;

public class HeroSkeleton implements Skeleton {
	public Bone[] bones = new Bone[11];
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
	//10 weapon
	float counter;
	public HeroSkeleton()
	{
		bones[0] = new  Bone(0 ,0  , 0, 270);
		bones[1]  = new  Bone(bones[0], 0, 270);
		
		bones[2]  = new  Bone(bones[0], 0, 225);
		bones[3]  = new  Bone(bones[2], 0, 270);
		
		bones[4]  = new  Bone(bones[0], 0, 315);
		bones[5] = new Bone(bones[4], 0, 270);
		
		bones[6] = new Bone(bones[1], 0, 315);
		bones[7] = new Bone(bones[6], 0, 270);
		
		bones[8] = new Bone(bones[1], 0, 225);
		bones[9] = new Bone(bones[8], 0, 270);
		//
		bones[10] = new Bone(bones[0], 0 , 0);
		counter = 0;
	}
	
	
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
		//
		bones[10] = new Bone(bones[0], 0 , 0);
		counter = 0;
	}
	
	
	public Bone[] getBones()
	{
		return bones;
	}
	
	public void update()
	{
		for(int i = 0 ; i < bones.length; i++)
		{
			bones[i].updateTailPoint();
		}
	}
	public float getStandingHeight()
	{
		return bones[0].length+bones[1].length+bones[8].length+bones[9].length;
	}
	

	
	
	
	
}
