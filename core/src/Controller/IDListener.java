package Controller;

import Model.IDResponse;
import Model.LocalWorld;
import Model.User;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class IDListener  extends Listener {
	
	LocalWorld wrl;

	public IDListener(LocalWorld wrl)
	{
		this.wrl = wrl;
		
	}
	public void received (Connection connection, Object object)
	{
		if(object instanceof IDResponse)
		{
			IDResponse response = ((IDResponse)object);
			this.wrl.user.heroID = response.id;
			wrl.hero.position = response.hero.position;
			wrl.hero.id = response.id;
			wrl.heroArr.arr[response.id] = wrl.hero;
			
			if(wrl.heroArr.size < response.id)
			{
				wrl.heroArr.size = response.id + 1;
			}
			
			this.wrl.entityArr.add(wrl.hero);
			
			System.out.println("received ID:"+ response.id);
			
			
			
			
			
		}
		
	
	}
	
	
}
