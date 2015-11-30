package Model;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HeroListener extends Listener {
	HeroArr heroArr;
	
	public HeroListener(HeroArr heroArr)
	{
		super();
		this.heroArr = heroArr;
		
		
	}
	
	public void received (Connection connection, Object object)
	{
		
		if(object instanceof Hero)
		{
			Hero hero = ((Hero)object);
			
			
			if(heroArr.arr[hero.id] == null)
			{
				heroArr.arr[hero.id] = hero;
			}
			else
			
			{				
				this.heroArr.arr[hero.id].x = hero.x;
				this.heroArr.arr[hero.id].y = hero.y;
			}
			if(hero.id >= this.heroArr.size)
			{
				this.heroArr.size = hero.id+1;
			}



		}
		
	}

}
