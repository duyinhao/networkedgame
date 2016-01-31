package com.mygdx.game;

import Model.Bone;
import Model.DStates;
import Model.Hero;
import Model.Skeleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkeletonAnimator {
	//public Skeleton skeleton;
	public PinGraphic[] boneGraphics;
	
	//used to store all graphics
	public PinGraphic[] graphics;
	
	float counter;
	Hero hero;
	
	PinGraphic weaponGraphic;
	
	public SkeletonAnimator( Hero hero)
	{
		Skeleton skeleton = hero.heroSkeleton;
		//this.skeleton = hero.heroSkeleton;
		boneGraphics = new PinGraphic[skeleton.getBones().length];
		graphics = new PinGraphic[skeleton.getBones().length*2];
		counter = 0;
		this.hero = hero;
		
		
		
		
	
	}
	
	public void draw(SpriteBatch batch)
	{
		Skeleton skeleton = hero.heroSkeleton;
		if(hero.direction ==DStates.RIGHT )
		{
			for(int i = 0 ; i <= 9 ; i++)
			{
				if(graphics[i]!=null)
				{
					Bone currentBone = skeleton.getBones()[i];
					PinGraphic currentGraphic = graphics[i];
					batch.draw(currentGraphic.graphic,currentBone.position.x-currentGraphic.xPinPos ,currentBone.position.y-currentGraphic.yPinPos , currentGraphic.xPinPos, currentGraphic.yPinPos, currentGraphic.graphic.getRegionWidth(), currentGraphic.graphic.getRegionHeight(), 1f, 1f, currentBone.angle);
				}
			}
		}
		if(hero.direction ==DStates.LEFT )
	{
		System.out.println("left");
			for(int i = 10 ; i <= 19 ; i++)
			{
				if(graphics[i]!=null)
				{
					Bone currentBone = skeleton.getBones()[i-10];
					PinGraphic currentGraphic = graphics[i];
					batch.draw(currentGraphic.graphic,currentBone.position.x-currentGraphic.xPinPos ,currentBone.position.y-currentGraphic.yPinPos , currentGraphic.xPinPos, currentGraphic.yPinPos, currentGraphic.graphic.getRegionWidth(), currentGraphic.graphic.getRegionHeight(), 1f, 1f, currentBone.angle);
				}
			}
		}
		if(weaponGraphic!=null)
		{
			Bone currentBone = hero.weaponBone;
			PinGraphic currentGraphic = weaponGraphic;
		//	batch.draw(currentGraphic.graphic,currentBone.position.x-currentGraphic.xPinPos ,currentBone.position.y-currentGraphic.yPinPos , currentGraphic.xPinPos, currentGraphic.yPinPos, currentGraphic.graphic.getRegionWidth(), currentGraphic.graphic.getRegionHeight(), 1f, 1f, currentBone.angle);
		}
	}
	public void update(float deltaTime,Hero hero)
	{
		counter+=deltaTime;
		Skeleton skeleton = hero.heroSkeleton;
		//System.out.println(counter);
		Bone[] bones = skeleton.getBones();
		
		if(hero.getState() == HStateComp.RUNR)
		{
		if(counter < 0.2)
		{
			bones[1].setAngle(255);
			
			bones[2].setAngle(10);
			bones[3].setAngle(45);
		
			bones[4].setAngle(190);
			bones[5].setAngle(235);
		
			bones[6].setAngle(325);
			bones[7].setAngle(270);
		
			bones[8].setAngle(235);
			bones[9].setAngle(160);
		}
		else
		{
			bones[1].setAngle(255);
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
		else if(hero.getState() == HStateComp.RUNL)
		{
			
			if(counter < 0.2)
			{
				
				
				
			
			
				bones[1].setAngle(-75);
				
				bones[2].setAngle(170);
				bones[3].setAngle(135);
			
				bones[4].setAngle(-10);
				bones[5].setAngle(-45);
			
				bones[6].setAngle(-145);
				bones[7].setAngle(-90);
			
				bones[8].setAngle(-55);
				bones[9].setAngle(20);
			}
			else
			{
				
				bones[1].setAngle(-75);
				bones[2].setAngle(-90);
				bones[3].setAngle(-120);
			
				bones[4].setAngle(-85);
				bones[5].setAngle(-110);
			
				bones[6].setAngle(-80);
				bones[7].setAngle(-75);
			
				bones[8].setAngle(-70);
				bones[9].setAngle(-65);
				
				

			}
		
			
			
			
			
		}
		else if(hero.getState() == HStateComp.JUMPL)
		{
			bones[1].setAngle(-75);
			bones[2].setAngle(170);
			bones[3].setAngle(135);
		
			bones[4].setAngle(-10);
			bones[5].setAngle(-55);
		
			bones[6].setAngle(-90);
			bones[7].setAngle(0);
		
			bones[8].setAngle(-55);
			bones[9].setAngle(-55);
		}
		System.out.println(this.getHeight());
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
	private void registerBoneGrahics(int boneIndex,int graphicIndex )
	{
		boneGraphics[boneIndex] = graphics[graphicIndex] ;
	}
	
	public void registerWeaponGrahic(TextureRegion region, int xPinPos, int yPinPos)
	{
		weaponGraphic = new PinGraphic(region, xPinPos, yPinPos); 
	}
	public float getHeight()
	{
		Skeleton skeleton = hero.heroSkeleton;
		Bone[] bones = skeleton.getBones();
		return bones[0].position.y - Math.min(bones[9].tailPointPosition.y,bones[7].tailPointPosition.y);
	}
	
}
