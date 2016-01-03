package Model;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HeroListener extends Listener {
	HeroArr heroArr;
	ArrayList<Entity> entityArr;
	public HeroListener(HeroArr heroArr, ArrayList<Entity> entityArr)
	{
		super();
		this.heroArr = heroArr;
		this.entityArr = entityArr;
		
		
	}
	
	public void received (Connection connection, Object object)
	{
		
		if(object instanceof Hero)
		{
			
			Hero hero = ((Hero)object);
			
			System.out.println("received hero with ID:"+hero.id);
			
			
			
			if(this.heroArr.arr[hero.id]==null)
			{
				entityArr.add(hero);

			}
			this.heroArr.arr[hero.id] = hero;
				
			
			if(hero.id >= this.heroArr.size)
			{
				this.heroArr.size = hero.id+1;
			}



		}
		
	}

}
