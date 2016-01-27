package com.mygdx.game;








import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Controller.Controller;
import Controller.EntListener;
import Controller.HeroListener;
import Controller.IDListener;
import Controller.ServerController;
import Controller.inputController;
import Model.AEPaintBullet;
import Model.BasicArmor;
import Model.BasicCape;
import Model.BasicShoes;
import Model.BasicShooter;
import Model.Bullet;
import Model.CloudBullet;
import Model.BulletState;
import Model.CloudShooter;
import Model.Collidable;
import Model.Collision;
import Model.DStates;
import Model.DashCape;
import Model.DoubleJumpShoes;
import Model.Entity;
import Model.EquipType;
import Model.Equipable;
import Model.HStates;
import Model.Hero;
import Model.HeroArr;
import Model.IDResponse;
import Model.LocalWorld;
import Model.Quadtree;
import Model.User;
import Model.Vector2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class MyGdxGame extends ApplicationAdapter{
	SpriteBatch batch;
	
	ScreenViewport viewPort ;
	
	LocalWorld wrl;
	
	EntListener entLis;
	HashMap<Class<?>, AnimationBinding> animationBinder;
	
	Animation walkAnimationR;
	Animation walkAnimationL;
	Animation crouchAnimationL;
	Animation crouchAnimationR;
	Animation standAnimationL;
	Animation standAnimationR;
	
	Animation jumpAnimationR;
	Animation jumpAnimationL;
	
	Animation flyR;
	Animation flyL;
	
	OrthographicCamera camera;
	TiledMap  tiledMap;
	TiledMapRenderer tiledMapRenderer;
	
	
	Texture paintBrushSprite;
	Texture healthBar;
	Texture spriteSheet;
	Texture pbulletSprite;
	Texture cBulletSprite;
	Texture bulletSprite;
	Texture boneSprite;
	Texture foreArmSprite;
	Texture armSprite;
	Texture bodySprite;
	Texture legSprite;
	Texture bootSprite;
	Texture headSprite;
	Texture entitySprite;
	
	
	
	
	
	Bone testBone;
	Bone testBone1;
	
	TextureRegion[]	walkFrames;
	TextureRegion currentFrame;
	Animation currentAnimation;
	float stateTime;
	
	Controller userController ;
	ServerController serverController;
	
	SkeletonAnimator heroSkeletonAnimator; 
	HeroSkeleton heroSkeleton;
	HeroDanceSkeleton heroDanceSkeleton;
	@Override
	public void create () {
		
		animationBinder = new HashMap<Class<?>, AnimationBinding> ();
		entitySprite = new Texture(Gdx.files.internal("megamansoccer1.png"));
		batch = new SpriteBatch();
		
		
		paintBrushSprite = new Texture(Gdx.files.internal("cloudGun.png"));
		
		spriteSheet = new Texture(Gdx.files.internal("megamansoccerEdit.png"));
		healthBar = new Texture(Gdx.files.internal("healthBar.png"));
		boneSprite = new Texture(Gdx.files.internal("bone.png"));
		
		testBone = new Bone(400f, 1500f  , boneSprite.getWidth(), 0);
		testBone1 = new Bone(testBone,boneSprite.getWidth(),0);
		
		foreArmSprite = new Texture(Gdx.files.internal("foreArm.png") );
		armSprite= new Texture(Gdx.files.internal("arm.png") );
		bodySprite= new Texture(Gdx.files.internal("body.png") );
		legSprite= new Texture(Gdx.files.internal("leg.png") );
		bootSprite= new Texture(Gdx.files.internal("boot.png") );
		headSprite = new Texture(Gdx.files.internal("head.png") );
		
		
		testBone.rotateLigamentAntiClockWise(30);
		
		

		
		
		
		
		
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 40, 41);
		walkFrames = new TextureRegion[4];
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		tiledMap = new TmxMapLoader().load("test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		
		AnimationBinding<BulletState> cbulletBinding = new AnimationBinding<BulletState>();
		cBulletSprite = new Texture(Gdx.files.internal("cloudBullet.png"));
		
		TextureRegion cbulletRegion = new TextureRegion(cBulletSprite);
		
		
		Animation cbulletAnimation = new Animation(0.1f, cbulletRegion);
		cbulletBinding.register(BulletState.NONE, cbulletAnimation);
		
		
		
		animationBinder.put(CloudBullet.class, cbulletBinding );
		
		
		AnimationBinding<BulletState> bulletBinding = new AnimationBinding<BulletState>();
		bulletSprite = new Texture(Gdx.files.internal("bullet400.png"));
		
		TextureRegion bulletRegion = new TextureRegion(bulletSprite);
		
		
		Animation bulletAnimation = new Animation(0.1f, bulletRegion);
		bulletBinding.register(BulletState.NONE, bulletAnimation);
		
		
		
		animationBinder.put(Bullet.class, bulletBinding );
		
		
		AnimationBinding<BulletState> aEBulletBinding = new AnimationBinding<BulletState>();
		pbulletSprite = new Texture(Gdx.files.internal("bullet1.png"));

		TextureRegion aEbulletRegion = new TextureRegion(pbulletSprite);
		
		
		Animation aEbulletAnimation = new Animation(0.1f, aEbulletRegion);
		aEBulletBinding.register(BulletState.NONE, aEbulletAnimation);
		
		
		animationBinder.put(AEPaintBullet.class, aEBulletBinding );
		
		AnimationBinding<HStateComp> heroBinding = new AnimationBinding<HStateComp>(-5,-1);
		
		
		
		walkFrames[0] = tmp[2][0];
		walkFrames[1] = tmp[3][0];
		walkFrames[2] = tmp[4][0];
		walkFrames[3] = tmp[3][0];
		
		
		walkAnimationL = new Animation(0.1f, walkFrames);
		walkAnimationL.setPlayMode(PlayMode.LOOP);
		
		heroBinding.register(HStateComp.RUNL, walkAnimationL);
		
		
		walkFrames = new TextureRegion[4];
		walkFrames[0] = tmp[2][1];
		walkFrames[1] = tmp[3][1];
		walkFrames[2] = tmp[4][1];
		walkFrames[3] = tmp[3][1];
		
		
		walkAnimationR = new Animation(0.1f, walkFrames);
		walkAnimationR.setPlayMode(PlayMode.LOOP);
		heroBinding.register(HStateComp.RUNR, walkAnimationR);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[1][1];
		standAnimationR = new Animation(0.1f, walkFrames);
		heroBinding.register(HStateComp.STANDR, standAnimationR);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[1][0];
		standAnimationL = new Animation(0.1f, walkFrames);
		heroBinding.register(HStateComp.STANDL, standAnimationL);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[3][5];
		//walkFrames[1] = tmp[2][5];
		
		
		jumpAnimationR = new Animation(2f, walkFrames);
		jumpAnimationR.setPlayMode(PlayMode.NORMAL);
		heroBinding.register(HStateComp.JUMPR, jumpAnimationR);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[3][4];
		animationBinder.put(Hero.class, heroBinding );
		//walkFrames[1] = tmp[2][4];
		
		jumpAnimationL = new Animation(2f, walkFrames);
		jumpAnimationL.setPlayMode(PlayMode.NORMAL);
		heroBinding.register(HStateComp.JUMPL, jumpAnimationL);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[5][5];
		flyR = new Animation(1f,walkFrames);
		heroBinding.register(HStateComp.FLYR, flyR);
		
		walkFrames = new TextureRegion[1]; 
		walkFrames[0] = tmp[5][4];
		flyL = new Animation(1f,walkFrames);
		heroBinding.register(HStateComp.FLYL, flyL);
		
		
		
		stateTime = 0f;
		//prepare the client for connection
		String ipAddress = "127.0.0.1";
		//String ipAddress = "10.0.0.5";
		
		//String ipAddress = "52.35.85.68";
	
		int udpPort = 54555;
		int tcpPort = 54777;
		
		
		 Client client = new Client();
		 Kryo kryo = client.getKryo();
		 	
		 	kryo.register(AEPaintBullet.class);
			kryo.register(BasicArmor.class);
			kryo.register(BasicCape.class);
			kryo.register(BasicShoes.class);
			kryo.register(BasicShooter.class);
			kryo.register(Bullet.class);
			kryo.register(BulletState.class);
			kryo.register(CloudBullet.class);
			kryo.register(CloudShooter.class);
			kryo.register(Collision.class);
			kryo.register(Collidable.class);
			kryo.register(DashCape.class);
			kryo.register(DoubleJumpShoes.class);
			kryo.register(DStates.class);
			kryo.register(Entity.class);
			kryo.register(Equipable.class);
			kryo.register(EquipType.class);
			
			
			kryo.register(Hero.class);
			kryo.register(HStates.class);
			kryo.register(IDResponse.class);
			kryo.register(Vector2.class);
		
		client.start();
		try {
			client.connect(100000, ipAddress,udpPort , tcpPort );
			//client.connect(5000, "127.0.0.1",54555 , 54777 );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		
		 
		
		 int[][] collisionMapArr= new int[layer.getWidth()][layer.getHeight()];
		
		
		
		
		
		TiledMapTile tmpTile;
		for(int x = 0; x < layer.getWidth(); x++)
		{
			for(int y = 0; y < layer.getHeight(); y++)
			{
				//System.out.println(y+" "+x);
				tmpTile = layer.getCell(x, y).getTile();
				//System.out.println(tmpTile.getId());
				
				if(!tmpTile.getProperties().containsKey("Collision")  )
				{
					collisionMapArr[x][y] = 0;
				}
				else
				{
					collisionMapArr[x][y] = Integer.parseInt(tmpTile.getProperties().get("Collision",String.class)) ;
					//System.out.println("has property");
				}
			}
			
		}
		if(client == null)
		{
			System.out.println("fudge");
		}
		wrl = new LocalWorld(collisionMapArr,(int) layer.getTileWidth() ,client );
		
		heroSkeleton = new  HeroSkeleton(100, 1500 ,220, 200,220, 190, 200, 170 );
		heroDanceSkeleton = new HeroDanceSkeleton(800, 500 ,220, 200,220, 190, 200, 170 );
		//heroSkeleton = new  HeroSkeleton(100, 1500 ,5, 10,4, 4, 4, 4 );
		heroSkeletonAnimator = new SkeletonAnimator(heroSkeleton,wrl.hero );
		heroSkeletonAnimator.register(0, new TextureRegion(headSprite), 130, 120);
		
		heroSkeletonAnimator.register(1, new TextureRegion(bodySprite), 90, 160);
		
		heroSkeletonAnimator.register(2, new TextureRegion(armSprite), 60, 150);
		
		heroSkeletonAnimator.register(3, new TextureRegion(foreArmSprite), 70, 130);
		
		heroSkeletonAnimator.register(4, new TextureRegion(armSprite), 60, 150);
		
		heroSkeletonAnimator.register(5, new TextureRegion(foreArmSprite), 70, 130);
		
		heroSkeletonAnimator.register(6, new TextureRegion(legSprite), 95, 130);
		
		heroSkeletonAnimator.register(7, new TextureRegion(bootSprite), 100, 125);
		
		heroSkeletonAnimator.register(8, new TextureRegion(legSprite), 95, 130);

		heroSkeletonAnimator.register(9, new TextureRegion(bootSprite), 100, 125);
		
		//always add the listener first before the requests otherwise wont register response
		//pehpaps a different structure is need to avoid this annoying bug
		client.addListener(new IDListener(wrl.user));
		client.addListener(new HeroListener(wrl.heroArr, wrl.entityArr));
		
		//change this to a proper request
		client.sendTCP(new Hero(100,1600,20,35));
		System.out.println("First hero packet sent from game");
		
		
		
	
		
		
		
		
		
		int width = Integer.parseInt(tiledMap.getProperties().get("width").toString());
		int height = Integer.parseInt(tiledMap.getProperties().get("height").toString());
		
		

		
	
		
		
		
		String temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.collisionMapArr[x][y] ; 
			}
			//System.out.println(temp);
		}
		serverController = new ServerController(client, wrl);
		userController = new Controller(camera,Gdx.input, wrl, client);
		
		Gdx.input.setInputProcessor(new inputController(camera, wrl, client));
		entLis = new EntListener(wrl.entityArr  , client);
//		wrl.loadColMap();
//		System.out.println("left");
//		temp="";
//		for(int y = height-1; y >= 0; y--)
//		{
//			temp="";
//			for(int x = 0; x < width; x++)
//			{
//				temp = temp + wrl.leftColMap[x][y] ; 
//			}
//			System.out.println(temp);
//		}
//		
//		System.out.println("right");
//		temp="";
//		for(int y = height-1; y >= 0; y--)
//		{
//			temp="";
//			for(int x = 0; x < width; x++)
//			{
//				temp = temp + wrl.rightColMap[x][y] ; 
//			}
//			System.out.println(temp);
//		}
//		
		System.out.println("down");
		temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.colSystem.downColMap[x][y] ; 
			}
			System.out.println(temp);
		}
		
//		System.out.println("up");
//		temp="";
//		for(int y = height-1; y >= 0; y--)
//		{
//			temp="";
//			for(int x = 0; x < width; x++)
//			{
//				temp = temp + wrl.upColMap[x][y] ; 
//			}
//			System.out.println(temp);
//		}
//		
	
		
	}

	@Override
	
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
	    stateTime = stateTime+  deltaTime;
	    
	    
	    heroDanceSkeleton.update(deltaTime);
	    heroSkeletonAnimator.update(deltaTime, wrl.hero);
	   camera.position.x = wrl.hero.position.x;
	   camera.position.y = wrl.hero.position.y;
	   
	    
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
	   
		tiledMapRenderer.setView(camera);
	    tiledMapRenderer.render();
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
//		for(int i = 0; i < wrl.heroArr.size; i++)	
//		{	
//			
//			Hero currentHero = wrl.heroArr.arr[i];
//			
//			
//			
//			if( wrl.heroArr.arr[i]!= null)
//			{
//				
//				AnimationBinding currentAnimationBinding = animationBinder.get(currentHero.getClass());
//				currentAnimation = currentAnimationBinding.returnAnimation(currentHero.getState());
//				currentFrame = currentAnimation.getKeyFrame(stateTime);	
//				
//				batch.draw(currentFrame, currentAnimationBinding.xOffset + currentHero.position.x, currentAnimationBinding.yOffset +currentHero.position.y);
//				
//				
//				
//				
//			}	
//		}
		for(int j = 0 ; j < wrl.entityArr.size(); j++)
		{
			Entity entity = wrl.entityArr.get(j);
			AnimationBinding currentAnimationBinding = animationBinder.get(entity.getClass());
			
			
			
			currentAnimation = currentAnimationBinding.returnAnimation(entity.getState());
			
			currentFrame = currentAnimation.getKeyFrame(stateTime);	
			if(!(entity instanceof Bullet))
				batch.draw(currentFrame, currentAnimationBinding.xOffset + entity.position.x, currentAnimationBinding.yOffset +entity.position.y);
			else
			{
				Bullet temp = (Bullet)entity;
				//batch.draw(currentFrame, currentAnimationBinding.xOffset + entity.position.x, currentAnimationBinding.yOffset +entity.position.y);
				float xPos = entity.position.x + currentAnimationBinding.xOffset ;
				float yPos = currentAnimationBinding.yOffset +entity.position.y;
				float angle = (float)Math.toDegrees(Math.atan(temp.velocity.y/temp.velocity.x));
				
				if(temp.velocity.x < 0)
				{
					angle = angle + 180;
				}
				
				batch.draw(currentFrame,xPos , yPos,  currentFrame.getRegionWidth()/2 ,currentFrame.getRegionHeight()/2 , currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1f, 1f, angle);
				//batch.draw(currentFrame, (float)(currentAnimationBinding.xOffset + entity.position.x-currentFrame.getRegionWidth()/2 ), (float)(currentAnimationBinding.yOffset +entity.position.y -currentFrame.getRegionHeight()/2) , (float)currentFrame.getRegionWidth()/2, (float) currentFrame.getRegionHeight()/2,  currentFrame.getRegionHeight(),currentFrame.getRegionWidth(), 1f, 1f,0f , true);
				
				
				
			//	batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation, clockwise);
				
				//batch.draw(currentFrame, x, y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
				//batch.draw(new TextureRegion(currentFrame), currentAnimationBinding.xOffset + entity.position.x, currentAnimationBinding.yOffset +entity.position.y  , 0f,  0f, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1f, 1f,(float) Math.toDegrees(Math.tanh(temp.velocity.y/temp.velocity.x)) , false);

				
				//batch.draw(new TextureRegion(currentFrame), , y, originX, originY, width, height, scaleX, scaleY, rotation);
			}
			
			
			
			
			if(entity instanceof Hero)
			{
				Hero curHero = (Hero)entity;
				batch.draw(healthBar, curHero.position.x -30, curHero.position.y + curHero.height+10, 100f*(curHero.health/100f) , 20f);
				
			
			}
			batch.draw(entitySprite , entity.position.x, entity.position.y, entity.width, entity.height);

			//batch.draw(bulletSprite, entity.position.x, entity.position.y);
		}
		
		//batch.draw(, wrl.hero.position.x+(wrl.hero.width/2)-paintBrushSprite.getWidth()/2, wrl.hero.position.y, 0f, 0f, (float)paintBrushSprite.getWidth(),  (float)paintBrushSprite.getHeight(), 1f, 1f, 0f, 0, 0,  paintBrushSprite.getWidth(), paintBrushSprite.getHeight(), false, false);
		//batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		
		int screenX = Gdx.input.getX();
		int screenY = Gdx.input.getY();
		//Vector3 modelVector3= camera.unproject(new Vector3( screenX,screenY,0));
		Vector3 heroScreenVector3 = camera.project(new Vector3((float)wrl.hero.position.x+wrl.hero.width/2,(float) wrl.hero.position.y-wrl.hero.height/2,0));
		
		double angle = Math.atan2( heroScreenVector3.y-screenY -10, screenX - heroScreenVector3.x);
		//angle = angle*Math.PI/180;
		angle = Math.toDegrees(angle);
		//System.out.println(Math.toDegrees(angle));
		batch.draw(new TextureRegion(paintBrushSprite), (float)wrl.hero.position.x+wrl.hero.width/2, (float) wrl.hero.position.y+wrl.hero.height/2,0f, 10f, 118f, 52f, 1f, 1f,(float)angle);
		
		
		//remember the width and length determine shape of bone, not the image
	//	batch.draw(new TextureRegion(boneSprite), (float)testBone.position.x,(float) testBone.position.y, 0f , 0f, boneSprite.getWidth(),boneSprite.getHeight(), 1f, 1f, testBone.angle);
	//	batch.draw(new TextureRegion(boneSprite), (float)testBone1.position.x,(float) testBone1.position.y, 0f , 0f, boneSprite.getWidth(), boneSprite.getHeight(), 1f, 1f, testBone1.angle);
	//	batch.draw(new TextureRegion(boneSprite), (float)testBone1.position.x,(float) testBone1.position.y, 0f , 0f, boneSprite.getWidth(), boneSprite.getHeight(), 1f, 1f, testBone1.angle);

		
		//batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		//testBone.rotateAntiClockWise(1f);
		//testBone1.rotateAntiClockWise(-1f);
		for(int i = 0; i < heroSkeleton.bones.length; i++)
		{
			Bone currentBone = heroSkeleton.bones[i];
			batch.draw(new TextureRegion(boneSprite), currentBone.position.x, currentBone.position.y, 0f, 0f, currentBone.length , 2f, 1, 1, currentBone.angle);
			//System.out.println(currentBone.tailPointPosition.y);
			batch.draw(new TextureRegion(paintBrushSprite), (float)wrl.hero.position.x+wrl.hero.width/2, (float) wrl.hero.position.y+wrl.hero.height/2,0f, 10f, 118f, 52f, 1f, 1f,(float)angle);

		}
	
		//batch.draw(new TextureRegion(boneSprite), 1500, 400, 0f, 0f, 100f , 10f, 1, 1, 0);
		//heroSkeleton.bones[1].angle +=1f;
		
		heroSkeletonAnimator.draw(batch);
		
		
		//0 -- head
		//1 -- body
		//2 -- bice1
		//3 -- forearm1
		//4 -- bicep2
		//5 -- forearm2
		//6 --thight1
		//7 -- shin1
		//8 -- thigh1
		// 9 -- shin1
		
		
		for(int i = 0; i < heroDanceSkeleton.bones.length; i++)
		{
			Bone currentBone = heroDanceSkeleton.bones[i];
			batch.draw(new TextureRegion(boneSprite), currentBone.position.x, currentBone.position.y, 0f, 0f, currentBone.length , 2f, 1, 1, currentBone.angle);
			//System.out.println(currentBone.tailPointPosition.y);
		}
		
		batch.draw(new TextureRegion(headSprite), heroDanceSkeleton.bones[0].position.x-130, heroDanceSkeleton.bones[0].position.y-120, 130, 120, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[0].angle );
		batch.draw(new TextureRegion(bodySprite), heroDanceSkeleton.bones[1].position.x-90, heroDanceSkeleton.bones[1].position.y-160,90, 160, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[1].angle);
		batch.draw(new TextureRegion(armSprite), heroDanceSkeleton.bones[2].position.x-60, heroDanceSkeleton.bones[2].position.y-150, 60, 150, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[2].angle );
		batch.draw(new TextureRegion(foreArmSprite), heroDanceSkeleton.bones[3].position.x-70, heroDanceSkeleton.bones[3].position.y-130, 70, 130, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[3].angle );
		batch.draw(new TextureRegion(armSprite), heroDanceSkeleton.bones[4].position.x-60, heroDanceSkeleton.bones[4].position.y-150, 60, 150, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[4].angle );
		batch.draw(new TextureRegion(foreArmSprite), heroDanceSkeleton.bones[5].position.x-70, heroDanceSkeleton.bones[5].position.y-130, 70, 130, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[5].angle );
		batch.draw(new TextureRegion(legSprite), heroDanceSkeleton.bones[6].position.x-95, heroDanceSkeleton.bones[6].position.y-130, 95, 130, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[6].angle );
		batch.draw(new TextureRegion(bootSprite), heroDanceSkeleton.bones[7].position.x-100, heroDanceSkeleton.bones[7].position.y-125, 100, 125, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[7].angle );
		batch.draw(new TextureRegion(legSprite), heroDanceSkeleton.bones[8].position.x-95, heroDanceSkeleton.bones[8].position.y-130, 95, 130, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[8].angle );
		batch.draw(new TextureRegion(bootSprite), heroDanceSkeleton.bones[9].position.x-100, heroDanceSkeleton.bones[9].position.y-125, 100, 125, foreArmSprite.getWidth(), foreArmSprite.getHeight(), 1f, 1f,heroDanceSkeleton.bones[9].angle );

		
		
		Texture foreArmSprite;
		Texture armSprite;
		Texture bodySprite;
		Texture legSprite;
		Texture bootSprite;
		
		batch.end();
		
		userController.update(deltaTime);
		wrl.upate(deltaTime);
		//wrl.update(deltaTime);
		
		//System.out.println(stateTime);
		
		
		
	}
}
