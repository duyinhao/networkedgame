package Controller;

import Model.Bone;
import Model.IDResponse;
import Model.LocalWorld;
import Model.User;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.HeroAnimationArr;
import com.mygdx.game.SkeletonAnimator;

public class IDListener  extends Listener {
	
	LocalWorld wrl;
	HeroAnimationArr heroAnim;
	TextureRegion heroHead1;
	TextureRegion heroHead2;
	TextureRegion heroTrunk;
	TextureRegion heroBody;
	TextureRegion heroLeg1;
	TextureRegion heroLeg2;
	TextureRegion paintBrushSprite;
	public IDListener(LocalWorld wrl,HeroAnimationArr heroAnim)
	{
		this.wrl = wrl;
		this.heroAnim = heroAnim;
		
		 heroHead1= new TextureRegion (new Texture(Gdx.files.internal("heroHead1.png")));
		 heroHead2 =  new TextureRegion (new Texture(Gdx.files.internal("heroHead2.png"))); 
	 heroTrunk=  new TextureRegion (new Texture(Gdx.files.internal("heroTrunk.png")));
		heroBody=  new TextureRegion (new Texture(Gdx.files.internal("heroBody.png")));
		 heroLeg1=  new TextureRegion (new Texture(Gdx.files.internal("heroLeg1.png")));
		 heroLeg2=  new TextureRegion (new Texture(Gdx.files.internal("heroLeg2.png")));
		 paintBrushSprite =  new TextureRegion (new Texture(Gdx.files.internal("bazookaGunScale.png")));
	
		
		
	}
	public void received (Connection connection, Object object)
	{
		
		if(object instanceof IDResponse)
		{
			IDResponse response = ((IDResponse)object);
			this.wrl.user.heroID = response.id;
			this.wrl.hero.weaponBone= new Bone(this.wrl.hero.heroSkeleton.getBones()[0],100, 0);
			
			wrl.hero.copy(response.hero);
			
			
			
			wrl.heroArr.arr[response.id] = wrl.hero;
			
			if(wrl.heroArr.size <= response.id)
			{
				wrl.heroArr.size = response.id+1 ;
				this.heroAnim.size = response.id+1;
			}
			
			
			
			this.wrl.entityArr.add(wrl.hero);
			
			this.heroAnim.arr[wrl.hero.id] = new SkeletonAnimator(wrl.hero);
			
			
			SkeletonAnimator currentAnimator = heroAnim.arr[wrl.hero.id];
			currentAnimator.register(0, new TextureRegion(heroHead1), 22, 21);
			
			currentAnimator.register(1, new TextureRegion(heroBody), 10, 20);
			
			currentAnimator.register(2, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(3, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(4, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(5, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(6, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(7, new TextureRegion(heroLeg2), 12, 15);
			
			currentAnimator.register(8, new TextureRegion(heroTrunk),9, 15);

			currentAnimator.register(9, new TextureRegion(heroLeg2), 12, 15);
			
			
			currentAnimator.register(10, new TextureRegion(heroHead2), 22, 21);
			
			currentAnimator.register(11, new TextureRegion(heroBody), 10, 20);
			
			currentAnimator.register(12, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(13, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(14, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(15, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(16, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(17, new TextureRegion(heroLeg1), 12, 25);
			
			currentAnimator.register(18, new TextureRegion(heroTrunk),9, 15);

			currentAnimator.register(19, new TextureRegion(heroLeg1), 12, 25);
			
			currentAnimator.registerWeaponGrahic(new TextureRegion(paintBrushSprite), 57, 48);
			
			System.out.println(wrl.hero.height);
			System.out.println("received ID:"+ response.id);
			
			
			
			
			
			
			
			
			
		}
		
	
	}
	
	
}
