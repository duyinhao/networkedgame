package Model;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class IDListener  extends Listener {
	
	User user;

	public IDListener(User user)
	{
		this.user = user;
		
	}
	public void received (Connection connection, Object object)
	{
		if(object instanceof IDResponse)
		{
			this.user.heroID = ((IDResponse)object).id;
			
			
		}
		
	
	}
	
	
}
