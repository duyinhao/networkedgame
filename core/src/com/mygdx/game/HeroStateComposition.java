package com.mygdx.game;


import Model.DStates;
import Model.HStates;

public class HeroStateComposition {
	public static HStateComp get(DStates direction ,HStates state)
	{
		if(direction == DStates.LEFT)
		{
			switch(state)
			{
				case RUN:
					return HStateComp.RUNL;
				case CROUCH:
					return HStateComp.CROUCHL;
				
				case FLY:
					return HStateComp.FLYL;
				case JUMP:
					return HStateComp.JUMPL;
				default:
				
					return HStateComp.STANDL;
					
			}
			
		}
		else 
		{
			switch(state)
			{
				case RUN:
					return HStateComp.RUNR;
				case CROUCH:
					return HStateComp.CROUCHR;
				case STAND:
					return HStateComp.STANDR;
				case FLY:
					return HStateComp.FLYR;
				case JUMP:
					return HStateComp.JUMPR;
				default:
					
					return HStateComp.STANDR;
					
			}
		}
	}
}
