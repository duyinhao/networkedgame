package com.mygdx.game;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.Animation;

import Model.Entity;
//this class basically maps animation to certain enums which represent object state

public class AnimationBinding <enumType>{
	
	
	Animation[] animArray;
	enumType st;
	HashMap<enumType,Animation > stateAnimationMap;
	public int xOffset;
	public int yOffset;
	public AnimationBinding( )
	{
		
		
		stateAnimationMap= new HashMap<enumType, Animation> ();
		
		xOffset =0;
		yOffset= 0;
	}
	public AnimationBinding( int xOffset, int yOffset)
	{
		stateAnimationMap= new HashMap<enumType, Animation> ();
	
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
	
	
	
}
