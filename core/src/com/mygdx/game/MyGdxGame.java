package com.mygdx.game;

import java.util.ArrayList;

import Model.Controller;
import Model.Hero;
import Model.LocalWorld;
import Model.User;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	LocalWorld wrl;
	User user;
	Hero hero;
	Controller userController ;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		hero = new Hero(1,1);
		 user = new User("test");
		wrl = new LocalWorld( "127.0.0.1", 54555 , 54777,  user);
		userController = new Controller(Gdx.input, wrl);
		
		
		
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
	    
	            	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		for(int i = 0; i < wrl.heroArr.size; i++)	
		{	
			
			Hero hero;
			if( wrl.heroArr.arr[i]!= null)
			{
				hero =  wrl.heroArr.arr[i];
				batch.draw(img,hero.getX(),hero.getY());
				
			
			
			}	
		}
		batch.end();
		
		float hSpeed = hero.SPEEDPERSECOND*deltaTime;
		float vSpeed = hero.SPEEDPERSECOND*deltaTime;
		if(this.user.heroID != -1)
		{
			hero = wrl.heroArr.arr[user.heroID];
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		 {
			 hero.setX(hero.getX()-hSpeed); 
			 wrl.client.sendUDP(hero); 
		 }
		
		 if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		 {
			 hero.setX(hero.getX()+hSpeed); 
			 wrl.client.sendUDP(hero); 
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.UP))
		 {
			 hero.setY(hero.getY()+vSpeed);
			 wrl.client.sendUDP(hero); 
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		 {
			 hero.setY(hero.getY()-vSpeed);
			 wrl.client.sendUDP(hero); 
		 }
		
		}
		
	}
}
