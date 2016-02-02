package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Model.BasicShoes;
import Model.BasicShooter;
import Model.CloudShooter;
import Model.Equipable;
import Model.GiantShoes;
import Model.Hero;

public class HeroEquip {
	static Texture bazookaSprite =  new Texture(Gdx.files.internal("bazookaGunScale.png")); 
	static Texture cloudGunSprite = new Texture(Gdx.files.internal("cloudGun.png"));

	Texture foreArmSprite  = new Texture(Gdx.files.internal("foreArm.png") );
	Texture armSprite= new Texture(Gdx.files.internal("arm.png") );
	Texture bodySprite= new Texture(Gdx.files.internal("body.png") );
	static Texture legSprite= new Texture(Gdx.files.internal("leg.png") );
	static Texture bootSprite= new Texture(Gdx.files.internal("boot.png") );
	Texture headSprite= new Texture(Gdx.files.internal("head.png") );
	
	
	Texture heroHead1= new Texture(Gdx.files.internal("heroHead1.png"));
	Texture heroHead2= new Texture(Gdx.files.internal("heroHead2.png")); 
	static Texture heroTrunk= new Texture(Gdx.files.internal("heroTrunk.png"));
	Texture heroBody= new Texture(Gdx.files.internal("heroBody.png"));
	static Texture heroLeg1= new Texture(Gdx.files.internal("heroLeg1.png"));
	static Texture heroLeg2= new Texture(Gdx.files.internal("heroLeg2.png"));
	public static void equipRegister( SkeletonAnimator heroAnim)
	{
		
		if(heroAnim.hero.weapon.getClass() == BasicShooter.class)
		{
			heroAnim.registerWeaponGrahic(new TextureRegion(bazookaSprite),57 ,48 );
		}
		else if(heroAnim.hero.weapon.getClass() == CloudShooter.class)
		{
			heroAnim.registerWeaponGrahic(new TextureRegion(cloudGunSprite),57 ,48 );
		}
		
		
		if(heroAnim.hero.shoes.getClass() == BasicShoes.class)
		{
			
			SkeletonAnimator currentAnimator = heroAnim ;
			
			
			currentAnimator.register(6, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(7, new TextureRegion(heroLeg2), 12, 15);
			
			currentAnimator.register(8, new TextureRegion(heroTrunk),9, 15);

			currentAnimator.register(9, new TextureRegion(heroLeg2), 12, 15);
			
			currentAnimator.register(16, new TextureRegion(heroTrunk), 9, 15);
			
			currentAnimator.register(17, new TextureRegion(heroLeg1), 12, 25);
			
			currentAnimator.register(18, new TextureRegion(heroTrunk),9, 15);

			currentAnimator.register(19, new TextureRegion(heroLeg1), 12, 25);
			
			
			
			
		}
		else if(heroAnim.hero.shoes.getClass() == GiantShoes.class)
		{
			SkeletonAnimator currentAnimator = heroAnim ;
			
			currentAnimator.register(6, new TextureRegion(legSprite), 95, 130);
			
			currentAnimator.register(7, new TextureRegion(bootSprite), 100, 125);
			
			currentAnimator.register(8, new TextureRegion(legSprite),95, 130);

			currentAnimator.register(9, new TextureRegion(bootSprite), 100, 125);
			
			currentAnimator.register(16, new TextureRegion(legSprite), 95, 130);
			
			currentAnimator.register(17, new TextureRegion(bootSprite), 100, 125);
			
			currentAnimator.register(18, new TextureRegion(legSprite),95, 130);

			currentAnimator.register(19, new TextureRegion(bootSprite), 100, 125);
			
		
		}
		
	}

}
