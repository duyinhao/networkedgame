package Model;

public class GiantShoes extends BasicShoes {

	public void equip(Hero hero)
	{
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
