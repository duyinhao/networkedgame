package Controller;

import java.util.ArrayList;

import Model.AEPaintBullet;
import Model.Bone;
import Model.Entity;
import Model.Hero;
import Model.HeroArr;
import Model.HeroSkeleton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.HeroAnimationArr;
import com.mygdx.game.SkeletonAnimator;

public class HeroListener extends Listener {
	HeroArr heroArr;
	ArrayList<Entity> entityArr;
	HeroAnimationArr heroAnim;
	TextureRegion heroHead1;
	TextureRegion heroHead2;
	TextureRegion heroTrunk;
	TextureRegion heroBody;
	TextureRegion heroLeg1;
	TextureRegion heroLeg2;
	TextureRegion paintBrushSprite;
	public HeroListener(HeroArr heroArr, ArrayList<Entity> entityArr, HeroAnimationArr heroAnim)
	{
		super();
		this.heroArr = heroArr;
		this.entityArr = entityArr;
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
		
		if(object instanceof Hero)
		{
			
			Hero hero = ((Hero)object);
			
			System.out.println("received hero with ID:"+hero.id);
			
			
			
			if(this.heroArr.arr[hero.id]==null)
			{
				entityArr.add(hero);
				
				this.heroArr.arr[hero.id] = hero;
				//this.entityArr.add(this.heroArr.arr[hero.id]);
				
				
				this.heroAnim.arr[hero.id] = new SkeletonAnimator(hero);
				
				
////				this.heroArr.arr[hero.id] = hero;
				this.heroArr.arr[hero.id].heroSkeleton = new  HeroSkeleton(100, 1500 ,19, 18,19, 18, 18, 20 );
				 
				this.heroArr.arr[hero.id].weaponBone= new Bone(this.heroArr.arr[hero.id].heroSkeleton.getBones()[0],100, 0);
				this.heroArr.arr[hero.id].copy(hero);
				SkeletonAnimator currentAnimator = heroAnim.arr[hero.id];
				
				if(hero.id >= this.heroArr.size)
				{
					this.heroArr.size = hero.id+1;
					this.heroAnim.size = hero.id+1;
				}
				
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
				
				
			

			}
			
		
			this.heroArr.arr[hero.id].copy(hero); 
			
			


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
