package Controller;

import Model.LocalWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryonet.Client;

public class inputController implements InputProcessor{

	OrthographicCamera camera;
	LocalWorld wrl;
	Client client;
	public inputController(OrthographicCamera camera , LocalWorld localWorld,Client client)
	{
		this.camera = camera;
		this.wrl = localWorld;
		this.client = client;
		
		
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(button == Buttons.LEFT)
		{
		
			
			//we want to get the xy in the model not screen to use.
			Vector3 modelVector3= camera.unproject(new Vector3( screenX,screenY,0));
			
			wrl.hero.basicAtt1(0, (int)modelVector3.x, (int)modelVector3.y, true, wrl);
			
			
			//System.out.println("x: "+modelVector3.x+"\ny: "+modelVector3.y);
			
			
		}
		if(button == Buttons.RIGHT)
		{
			Vector3 modelVector3= camera.unproject(new Vector3( screenX,screenY,0));
			
			wrl.hero.basicAtt2(0, (int)modelVector3.x, (int)modelVector3.y, true, wrl);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
