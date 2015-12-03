package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.esotericsoftware.kryonet.Client;



public class Controller {
	Input input;
	LocalWorld wrl;
	Client client;
	public Controller(Input input, LocalWorld localWorld,Client client)
	{
		this.input = input;
		this.wrl = localWorld;
		this.client = client;
		
	}
	public void update(float deltaTime)
	{
		float hSpeed = Hero.SPEEDPERSECOND*deltaTime;
		float vSpeed = Hero.SPEEDPERSECOND*deltaTime;
		if(wrl.user.heroID != -1)
		{
			wrl.hero = wrl.heroArr.arr[wrl.user.heroID];
		 if(Gdx.input.isKeyPressed(Input.Keys.A))
		 {
			 wrl.hero.setX(wrl.hero.getX()-hSpeed); 
			 wrl.hero.direction = DStates.LEFT;
			 wrl.hero.status = HStates.RUN;
			 client.sendUDP(wrl.hero); 
		 }
		 else if(Gdx.input.isKeyPressed(Input.Keys.D))
		 {
			 wrl.hero.setX(wrl.hero.getX()+hSpeed); 
			 wrl.hero.direction = DStates.RIGHT;
			 wrl.hero.status = HStates.RUN;
			 client.sendUDP(wrl.hero); 
		 }
		 else 
		 {
			 if(wrl.hero.status != HStates.STAND)
			 {
				 wrl.hero.status = HStates.STAND;
				 client.sendUDP(wrl.hero); 
			 }
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		 {
			 wrl.hero.setY(wrl.hero.getY()+vSpeed);
			 client.sendUDP(wrl.hero); 
		 }
		 if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		 {
			 wrl.hero.setY(wrl.hero.getY()-vSpeed);
			 //wrl.hero.status = HStates.;
			 client.sendUDP(wrl.hero); 
		 }
		
		}
		
		
	}
}