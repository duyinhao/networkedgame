package Controller;


import Model.DStates;
import Model.HStates;
import Model.Hero;
import Model.LocalWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.MyGdxGame;



public class Controller {
	Input input;
	LocalWorld wrl;
	Client client;
	OrthographicCamera camera;
	public Controller(OrthographicCamera camera ,Input input, LocalWorld localWorld,Client client)
	{
		this.input = input;
		this.wrl = localWorld;
		this.client = client;
		this.camera = camera;
		
	}
	public void update(float deltaTime)
	{
		
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			int screenX = Gdx.input.getX();
			int screenY = Gdx.input.getY();
			Vector3 modelVector3= camera.unproject(new Vector3( screenX,screenY,0));
		
			wrl.hero.basicAtt1(0, (int)modelVector3.x, (int)modelVector3.y, false, wrl);
		
		}
		
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			int screenX = Gdx.input.getX();
			int screenY = Gdx.input.getY();
			
			Vector3 modelVector3= camera.unproject(new Vector3( screenX,screenY,0));
			
			wrl.hero.basicAtt2(deltaTime, (int)modelVector3.x, (int)modelVector3.y, false, wrl);

		}
		
		//horrible idea fix it
		float hSpeed = Hero.SPEEDPERSECOND;
		float vSpeed = Hero.SPEEDPERSECOND;
		//System.out.println(wrl);
		if(wrl.user.heroID != -1)
		{
			//wrl.hero = wrl.heroArr.arr[wrl.user.heroID];
		 if(Gdx.input.isKeyPressed(Input.Keys.A))
		 {
			 if(wrl.hero.status == HStates.STAND)
				 wrl.hero.status = HStates.RUN;
			 if(wrl.hero.status==HStates.RUN)
				 wrl.hero.velocity.x = -1*hSpeed; 
			 wrl.hero.direction = DStates.LEFT;
			
			 client.sendUDP(wrl.hero); 
		 }
		 else if(Gdx.input.isKeyPressed(Input.Keys.D))
		 {
			 if(wrl.hero.status == HStates.STAND)
				 wrl.hero.status = HStates.RUN;
			 if(wrl.hero.status==HStates.RUN)
				 wrl.hero.velocity.x = hSpeed;  
			 wrl.hero.direction = DStates.RIGHT;
			 
			 client.sendUDP(wrl.hero); 
		 }
		 else 
		 {
		
			 if(wrl.hero.velocity.y<0&&wrl.actualYMovementWithCollision(wrl.hero, wrl.hero.velocity)==0)
			 {
				 wrl.hero.status = HStates.STAND;
				 wrl.hero.velocity.x = 0; 
				 client.sendUDP(wrl.hero); 
			 }
		 }
		 
		 if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		 //if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		 {
			 wrl.hero.jump(deltaTime,Gdx.input.isKeyJustPressed(Input.Keys.SPACE),wrl);
			 
			 
			 
			 client.sendUDP(wrl.hero); 
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		 {
			 wrl.hero.velocity.y = -1*vSpeed;
			 //wrl.hero.status = HStates.;
			 client.sendUDP(wrl.hero); 
		 }
		
		}
		
		
	}
}
