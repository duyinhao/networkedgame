package Controller;

import java.util.ArrayList;

import Model.AEPaintBullet;
import Model.Entity;
import Model.Hero;
import Model.HeroArr;

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
				this.heroArr.arr[hero.id] = hero;
				this.entityArr.add(this.heroArr.arr[hero.id]);

			}
			
			//this.heroArr.arr[hero.id] = hero;
			this.heroArr.arr[hero.id].copy(hero); 
			
			if(hero.id >= this.heroArr.size)
			{
				this.heroArr.size = hero.id+1;
			}



		}
		else if(object instanceof Entity)
		{
			if(object instanceof AEPaintBullet)
				entityArr.add(0,(Entity) object);
			else
				entityArr.add((Entity)object);
			System.out.println("received bullets");
		}
		
		
	}

}
