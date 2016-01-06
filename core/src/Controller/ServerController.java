package Controller;

import java.io.IOException;

import Model.LocalWorld;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class ServerController {
	
	public LocalWorld wrl;
	public Client client;
	public ServerController(Client client ,LocalWorld  wrl)
	{		
		this.wrl = wrl;
		this.client = client;
	}
}
