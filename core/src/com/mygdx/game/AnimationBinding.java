package com.mygdx.game;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.Animation;

import Model.Entity;

public class AnimationBinding <enumType>{
	Entity entity;
	Animation[] animArray;
	enumType st;
	HashMap<enumType,Animation > stateAnimationMap;
	int xOffset;
	int yOffset;
	public AnimationBinding(Entity entity)
	{
		this.entity = entity;
		xOffset =0;
		yOffset= 0;
	}
	public AnimationBinding(Entity entity, int xOffset, int yOffset)
	{
		this.entity = entity;
		this.xOffset = xOffset;
		this.yOffset= yOffset;
	
	}
	
	
	public void register( enumType state,Animation animation)
	{
		this.stateAnimationMap.put(state, animation);
	}
	public Animation returnAnimation(enumType state)
	{
		return stateAnimationMap.get(state);
	}
	
	public int getX()
	{
		return (int)entity.position.x + xOffset;
	}
	public int getY()
	{
		return (int)entity.position.y + yOffset;
	}
	
}
