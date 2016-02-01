package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Model.BasicShooter;
import Model.CloudShooter;
import Model.Equipable;
import Model.Hero;

public class HeroEquip {
	static Texture bazookaSprite =  new Texture(Gdx.files.internal("bazookaGunScale.png")); 
	static Texture cloudGunSprite = new Texture(Gdx.files.internal("cloudGun.png"));

	Texture foreArmSprite  = new Texture(Gdx.files.internal("foreArm.png") );
	Texture armSprite= new Texture(Gdx.files.internal("arm.png") );
	Texture bodySprite= new Texture(Gdx.files.internal("body.png") );
	Texture legSprite= new Texture(Gdx.files.internal("leg.png") );
	Texture bootSprite= new Texture(Gdx.files.internal("boot.png") );
	Texture headSprite= new Texture(Gdx.files.internal("head.png") );
	
	
	Texture heroHead1= new Texture(Gdx.files.internal("heroHead1.png"));
	Texture heroHead2= new Texture(Gdx.files.internal("heroHead2.png")); 
	Texture heroTrunk= new Texture(Gdx.files.internal("heroTrunk.png"));
	Texture heroBody= new Texture(Gdx.files.internal("heroBody.png"));
	Texture heroLeg1= new Texture(Gdx.files.internal("heroLeg1.png"));
	Texture heroLeg2= new Texture(Gdx.files.internal("heroLeg2.png"));
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
	
		
	}

}
