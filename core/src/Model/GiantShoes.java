package Model;

import com.mygdx.game.HeroDanceSkeleton;

public class GiantShoes extends BasicShoes {

	public void equip(Hero hero)
	{
		
		new HeroDanceSkeleton(800, 500 ,220, 200,220, 190, 200, 170 );
		
		hero.shoes = this;
		hero.heroSkeleton.bones[9].length = 220;
		hero.heroSkeleton.bones[7].length = 220;
		
		hero.heroSkeleton.bones[9].updateTailPoint();
		hero.heroSkeleton.bones[7].updateTailPoint();
		
		hero.heroSkeleton.bones[8].length = 200;
		hero.heroSkeleton.bones[6].length = 200;
		
		hero.heroSkeleton.bones[8].updateTailPoint();
		hero.heroSkeleton.bones[6].updateTailPoint();
		
	}
	
	
	public void jump(float deltaTime,boolean justPressed, LocalWorld wrl)
	{
		if (wrl.hero.status!=HStates.JUMP&&justPressed)
		{
			wrl.hero.velocity.y = 300;
			wrl.hero.status = HStates.JUMP;
		}
		
	}
}
