package Model;

import com.mygdx.game.HeroDanceSkeleton;

public class GiantArmor extends BasicArmor {
	public void equip(Hero hero)
	{
		hero.armor = this;
		
	new HeroDanceSkeleton(800, 500 ,220, 200,220, 190, 200, 170 );
		

		
		//body
		hero.heroSkeleton.bones[1].length =220;
		
		hero.heroSkeleton.bones[1].updateTailPoint();
		
		
		//bicep
		hero.heroSkeleton.bones[2].length = 190;
		hero.heroSkeleton.bones[4].length = 190;
		hero.heroSkeleton.bones[2].updateTailPoint();
		hero.heroSkeleton.bones[4].updateTailPoint();
		//forearm
		hero.heroSkeleton.bones[3].length = 200;
		hero.heroSkeleton.bones[5].length = 200;
		hero.heroSkeleton.bones[3].updateTailPoint();
		hero.heroSkeleton.bones[5].updateTailPoint();
		
		//head
		hero.heroSkeleton.bones[0].length = 170;
		hero.heroSkeleton.bones[0].updateTailPoint();
		
		
		
	
		
	}

}
