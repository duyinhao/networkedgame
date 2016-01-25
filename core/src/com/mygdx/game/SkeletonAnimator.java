package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkeletonAnimator {
	public Skeleton skeleton;
	public PinGraphic[] graphics;
	public SkeletonAnimator(Skeleton skeleton)
	{
		this.skeleton = skeleton;
		graphics = new PinGraphic[skeleton.getBones().length];
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
	public void update(float deltaTime)
	{
		
	}
	public void register(int boneIndex , TextureRegion region, int xPinPos, int yPinPos)
	{
		graphics[boneIndex] = new PinGraphic(region, xPinPos, yPinPos);
	}
	
}
