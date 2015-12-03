package com.mygdx.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Model.Controller;
import Model.DStates;
import Model.HStates;
import Model.Hero;
import Model.HeroListener;
import Model.IDListener;
import Model.IDResponse;
import Model.LocalWorld;
import Model.ServerController;
import Model.User;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	LocalWorld wrl;
	
	Animation walkAnimationR;
	Animation walkAnimationL;
	Animation crouchAnimationL;
	Animation crouchAnimationR;
	Animation standAnimationL;
	Animation standAnimationR;
	
	OrthographicCamera camera;
	TiledMap  tiledMap;
	TiledMapRenderer tiledMapRenderer;
	
	
	
	Texture spriteSheet;
	TextureRegion[]	walkFrames;
	TextureRegion currentFrame;
	Animation currentAnimation;
	float stateTime;
	
	Controller userController ;
	ServerController serverController;
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		
		wrl = new LocalWorld();
		
		spriteSheet = new Texture(Gdx.files.internal("megamansoccer.png"));
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 40, 42);
		walkFrames = new TextureRegion[4];
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		tiledMap = new TmxMapLoader().load("test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		walkFrames[0] = tmp[2][0];
		walkFrames[1] = tmp[3][0];
		walkFrames[2] = tmp[4][0];
		walkFrames[3] = tmp[3][0];
		
		
		walkAnimationL = new Animation(0.1f, walkFrames);
		
		
		walkFrames = new TextureRegion[4];
		
		
		
		
		walkFrames[0] = tmp[2][1];
		walkFrames[1] = tmp[3][1];
		walkFrames[2] = tmp[4][1];
		walkFrames[3] = tmp[3][1];
		
		
		walkAnimationR = new Animation(0.1f, walkFrames);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[1][1];
		standAnimationR = new Animation(0.1f, walkFrames);
		
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[1][0];
		standAnimationL = new Animation(0.1f, walkFrames);
		
		
		stateTime = 0f;
		//prepare the client for connection
		String ipAddress = "127.0.0.1";
		//String ipAddress = "192.168.1.5";
		int udpPort = 54555;
		int tcpPort = 54777;
		
		
		 Client client = new Client();
		 Kryo kryo = client.getKryo();
		 
		 kryo.register(Hero.class);
		  kryo.register(IDResponse.class);
		 kryo.register(DStates.class);
		 kryo.register(HStates.class);
		
		
		client.start();
		try {
			client.connect(100000, ipAddress,udpPort , tcpPort );
			//client.connect(5000, "127.0.0.1",54555 , 54777 );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//change this to a proper request
		client.sendTCP(new Hero(1,1));
		System.out.println("First hero packet sent from game");
	

		client.addListener(new IDListener(wrl.user));
		client.addListener(new HeroListener(wrl.heroArr));
		
		
		
		
		
		serverController = new ServerController(client, wrl);
		userController = new Controller(Gdx.input, wrl, client);
		
		
		
		int width = Integer.parseInt(tiledMap.getProperties().get("width").toString());
		int height = Integer.parseInt(tiledMap.getProperties().get("height").toString());
	
		
		wrl.collisionMapArr = new int[width][height];
		
	
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		TiledMapTile tmpTile;
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				System.out.println(y+" "+x);
				tmpTile = layer.getCell(x, y).getTile();
				System.out.println(tmpTile.getId());
				
				if(!tmpTile.getProperties().containsKey("Collision")  )
				{
					wrl.collisionMapArr[x][y] = 0;
				}
				else
				{
					wrl.collisionMapArr[x][y] = Integer.parseInt(tmpTile.getProperties().get("Collision",String.class)) ;
					System.out.println("has property");
				}
			}
			
		}
		
		
		
		String temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.collisionMapArr[x][y] ; 
			}
			System.out.println(temp);
		}
		
		
		wrl.loadColMap();
		System.out.println("left");
		temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.leftColMap[x][y] ; 
			}
			System.out.println(temp);
		}
		
		System.out.println("right");
		temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.rightColMap[x][y] ; 
			}
			System.out.println(temp);
		}
		
		System.out.println("down");
		temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.downColMap[x][y] ; 
			}
			System.out.println(temp);
		}
		
		System.out.println("up");
		temp="";
		for(int y = height-1; y >= 0; y--)
		{
			temp="";
			for(int x = 0; x < width; x++)
			{
				temp = temp + wrl.upColMap[x][y] ; 
			}
			System.out.println(temp);
		}
		
		
		
		
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
	    stateTime = stateTime+  deltaTime;
	    
	    
	    
	    
	    
	   
	    
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
	    
		tiledMapRenderer.setView(camera);
	    tiledMapRenderer.render();
		
		batch.begin();
		
		
		for(int i = 0; i < wrl.heroArr.size; i++)	
		{	
			
			Hero currentHero = wrl.heroArr.arr[i];
			if( wrl.heroArr.arr[i]!= null)
			{
				
				if(currentHero.direction == DStates.LEFT)
				{
					if(currentHero.status == HStates.RUN)
					{
						currentAnimation = walkAnimationL;
						
					}
					else if(currentHero.status == HStates.STAND)
					{
						
						currentAnimation = standAnimationL;
					}
										
				}
				else if(currentHero.direction == DStates.RIGHT)
				{
					if(currentHero.status == HStates.RUN)
					{
						currentAnimation = walkAnimationR;
						
					}
					else if(currentHero.status == HStates.STAND)
					{
						
						currentAnimation = standAnimationR;
					}
					
				}
				
				currentFrame = currentAnimation.getKeyFrame(stateTime, true);
				
				//batch.draw(img,hero.getX(),hero.getY());
				
			   
				batch.draw(currentFrame, currentHero.getX(), currentHero.getY());
			
			}	
		}
		
		batch.end();
		userController.update(deltaTime);
		System.out.println(stateTime);
		
		
	}
}