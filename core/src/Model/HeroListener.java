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
			
			System.out.println("received hero with ID:"+hero.id);
			
			
			
							
				this.heroArr.arr[hero.id] = hero;
				
			
			if(hero.id >= this.heroArr.size)
			{
				this.heroArr.size = hero.id+1;
			}



		}
		
	}

}
