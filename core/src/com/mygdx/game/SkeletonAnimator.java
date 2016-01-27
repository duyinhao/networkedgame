package com.mygdx.game;

import Model.Hero;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkeletonAnimator {
	public Skeleton skeleton;
	public PinGraphic[] graphics;
	float counter;
	Hero hero;
	public SkeletonAnimator(Skeleton skeleton, Hero hero)
	{
		this.skeleton = skeleton;
		graphics = new PinGraphic[skeleton.getBones().length];
		counter = 0;
		this.hero = hero;
	}
	
	public void draw(SpriteBatch batch)
	{
		for(int i = 0 ; i < graphics.length ; i++)
		{
			if(graphics[i]!=null)
			{
				Bone currentBone = skeleton.getBones()[i];
				PinGraphic currentGraphic = graphics[i];
				batch.draw(graphics[i].graphic,currentBone.position.x-currentGraphic.xPinPos ,currentBone.position.y-currentGraphic.yPinPos , currentGraphic.xPinPos, currentGraphic.yPinPos, graphics[i].graphic.getRegionWidth(), graphics[i].graphic.getRegionHeight(), 1f, 1f, currentBone.angle);
			}
		}
	}
	public void update(float deltaTime,Hero hero)
	{
		counter+=deltaTime;
		//System.out.println(counter);
		Bone[] bones = skeleton.getBones();
		
		
		if(hero.getState() == HStateComp.RUNR)
		{
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
		

		}
		else if(hero.getState() == HStateComp.RUNL)
		{
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
			
			
			
			
			
		}
		else if(hero.getState() == HStateComp.JUMPR)
		{
			bones[1].setAngle(255);
			bones[2].setAngle(10);
			bones[3].setAngle(45);
		
			bones[4].setAngle(190);
			bones[5].setAngle(235);
		
			bones[6].setAngle(270);
			bones[7].setAngle(180);
		
			bones[8].setAngle(235);
			bones[9].setAngle(235);
		}
		else if(hero.getState() == HStateComp.STANDR)
		{
			bones[1].setAngle(270);
			bones[2].setAngle(270);
			bones[3].setAngle(270);
		
			bones[4].setAngle(270);
			bones[5].setAngle(270);
		
			bones[6].setAngle(270);
			bones[7].setAngle(270);
		
			bones[8].setAngle(270);
			bones[9].setAngle(270);
			
		}
		bones[0].position.x =hero.position.x+hero.width/2;
		bones[0].position.y = hero.position.y+this.getHeight();

	//	System.out.println(this.getHeight());
		//hero.height = (int)this.getHeight();
		if(counter> 0.4)
		{
			counter=0f;
		}
		for(int i = 0 ; i < bones.length; i++)
		{
			bones[i].updateTailPoint();
		}
		//System.out.println(hero.velocity.x);
		
	}
	public void register(int boneIndex , TextureRegion region, int xPinPos, int yPinPos)
	{
		graphics[boneIndex] = new PinGraphic(region, xPinPos, yPinPos);
	}
	public float getHeight()
	{
		Bone[] bones = skeleton.getBones();
		return bones[0].position.y - Math.min(bones[9].tailPointPosition.y,bones[7].tailPointPosition.y);
	}
	
}
