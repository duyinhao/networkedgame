package Model;







import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LocalWorld {
	
	//Hero hero ;
	boolean online;
	

	public HeroArr heroArr;
	
	
	String ipAddress;
	int udpPort;
	int tcpPort;
	public Client client;
	
	
	
	public LocalWorld( String ipAddress, int udpPort, int tcpPort, User user)
	{
		
		
		
		this.ipAddress = ipAddress;
		this.udpPort = udpPort;
		this.tcpPort = tcpPort;
		heroArr = new HeroArr();
		
		
		
        client = new Client();
		 Kryo kryo = client.getKryo();
		  
		  
		 
		  kryo.register(Hero.class);
		  kryo.register(IDResponse.class);
		 //kryo.register(SomeRequest.class);
		 //kryo.register(SomeResponse.class);
		
		
		client.start();
		try {
			client.connect(100000, ipAddress,udpPort , tcpPort );
			//client.connect(5000, "127.0.0.1",54555 , 54777 );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//change this to a proper request
		client.sendUDP(new Hero(1,1));
		System.out.println("First hero packet sent from game");
	

		client.addListener(new IDListener(user));
		client.addListener(new HeroListener(heroArr));
		
		
	}
}
