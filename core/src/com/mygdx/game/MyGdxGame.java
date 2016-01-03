package com.mygdx.game;








import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Model.BasicArmor;
import Model.BasicCape;
import Model.BasicShoes;
import Model.BasicShooter;
import Model.Bullet;
import Model.BulletState;
import Model.Collidable;
import Model.Collision;
import Model.Controller;
import Model.DStates;
import Model.DashCape;
import Model.DoubleJumpShoes;
import Model.Entity;
import Model.Equipable;
import Model.HStates;
import Model.Hero;
import Model.HeroArr;
import Model.HeroListener;
import Model.IDListener;
import Model.IDResponse;
import Model.LocalWorld;
import Model.ServerController;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	ScreenViewport viewPort ;
	
	LocalWorld wrl;
	
	
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
	Texture spriteSheet;
	Texture bulletSprite;
	TextureRegion[]	walkFrames;
	TextureRegion currentFrame;
	Animation currentAnimation;
	float stateTime;
	
	Controller userController ;
	ServerController serverController;
	@Override
	public void create () {
		
		animationBinder = new HashMap<Class<?>, AnimationBinding> ();
		
		batch = new SpriteBatch();
		
		
		paintBrushSprite = new Texture(Gdx.files.internal("paintbrush.png"));
		
		spriteSheet = new Texture(Gdx.files.internal("megamansoccerEdit.png"));
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 40, 41);
		walkFrames = new TextureRegion[4];
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		tiledMap = new TmxMapLoader().load("test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		
		AnimationBinding<BulletState> bulletBinding = new AnimationBinding<BulletState>();
		bulletSprite = new Texture(Gdx.files.internal("bullet.png"));

		TextureRegion bulletRegion = new TextureRegion(bulletSprite);
		
		
		Animation bulletAnimation = new Animation(0.1f, bulletRegion);
		bulletBinding.register(BulletState.NONE, bulletAnimation);
		
		
		animationBinder.put(Bullet.class, bulletBinding );
		
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
		//String ipAddress = "197.89.20.143";

		//String ipAddress = "10.0.0.123";
		
		//String ipAddress = "52.34.163.224";
		//this is the server ip
		//String ipAddress = "52.27.107.160";
		int udpPort = 54555;
		int tcpPort = 54777;
		
		
		 Client client = new Client();
		 Kryo kryo = client.getKryo();
		 
			kryo.register(BasicArmor.class);
			kryo.register(BasicCape.class);
			kryo.register(BasicShoes.class);
			kryo.register(BasicShooter.class);
			kryo.register(Bullet.class);
			kryo.register(Collidable.class);
			kryo.register(DashCape.class);
			kryo.register(DoubleJumpShoes.class);
			
			kryo.register(Equipable.class);
			kryo.register(DStates.class);
			kryo.register(Entity.class);
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
		
		wrl = new LocalWorld(collisionMapArr,(int) layer.getTileWidth() );
		
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
			
			batch.draw(currentFrame, currentAnimationBinding.xOffset + entity.position.x, currentAnimationBinding.yOffset +entity.position.y);
			
			
			
			
			//batch.draw(bulletSprite, entity.position.x, entity.position.y);
		}
		//batch.draw(paintBrushSprite, wrl.hero.position.x+(wrl.hero.width/2)-paintBrushSprite.getWidth()/2, wrl.hero.position.y, 0f, 0f, (float)paintBrushSprite.getWidth(),  (float)paintBrushSprite.getHeight(), 1f, 1f, 0f, 0, 0,  paintBrushSprite.getWidth(), paintBrushSprite.getHeight(), false, false);
		
		batch.end();
		
		userController.update(deltaTime);
		wrl.upate(deltaTime);
		//wrl.update(deltaTime);
		
		//System.out.println(stateTime);
		
		
		
	}
}
